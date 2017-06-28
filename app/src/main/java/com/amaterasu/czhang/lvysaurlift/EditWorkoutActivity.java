package com.amaterasu.czhang.lvysaurlift;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class EditWorkoutActivity extends AppCompatActivity {
    private WorkoutData workout;
    private int position;
    public TextView date1;
    public Spinner spinner;

    public TextView name1;
    public TextView sets1;
    public EditText weight1;
    public TextView name2;
    public TextView sets2;
    public EditText weight2;
    public TextView name3;
    public TextView sets3;
    public EditText weight3;
    public TextView name4;
    public TextView sets4;
    public EditText weight4;
    KeyListener weight4Listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        position = getIntent().getIntExtra(WorkoutDataAdapter.ViewHolder.EXTRA_INDEX, -1);
        if(position != -1) {
            setTitle("Edit Entry");
            workout = MainActivity.workouts.get(position);
        }

        else {
            setTitle("Create Entry");
            workout = new WorkoutData(WorkoutData.currentType);
        }

        name1 = (TextView) findViewById(R.id.name1);
        sets1 = (TextView) findViewById(R.id.sets1);
        weight1 = (EditText) findViewById(R.id.weight1);

        name2 = (TextView) findViewById(R.id.name2);
        sets2 = (TextView) findViewById(R.id.sets2);
        weight2 = (EditText) findViewById(R.id.weight2);

        name3 = (TextView) findViewById(R.id.name3);
        sets3 = (TextView) findViewById(R.id.sets3);
        weight3 = (EditText) findViewById(R.id.weight3);

        name4 = (TextView) findViewById(R.id.name4);
        sets4 = (TextView) findViewById(R.id.sets4);
        weight4 = (EditText) findViewById(R.id.weight4);
        weight4Listener = weight4.getKeyListener();

        date1 = (TextView) findViewById(R.id.dateView);

        loadDataView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_spinner, menu);

        MenuItem item = menu.findItem(R.id.typeSpinner);
        spinner = (Spinner) MenuItemCompat.getActionView(item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.workout_types, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        int spinnerDefaultPosition;
        switch(workout.getWorkoutType()) {
            case A_1:
                spinnerDefaultPosition = 0;
                break;
            case A_2:
                spinnerDefaultPosition = 1;
                break;
            case A_3:
                spinnerDefaultPosition = 2;
                break;
            case B_1:
                spinnerDefaultPosition = 3;
                break;
            case B_2:
                spinnerDefaultPosition = 4;
                break;
            case B_3:
                spinnerDefaultPosition = 5;
                break;
            default:
                spinnerDefaultPosition = 1;
                break;
        }
        spinner.setAdapter(adapter);
        spinner.setSelection(spinnerDefaultPosition);
        spinner.setOnItemSelectedListener(new SpinnerListener());
        return true;
    }

    public void loadDataView() {
        ArrayList<Exercise> exercises = workout.getExercises();
        Exercise exercise1 = exercises.get(0);
        Exercise exercise2 = exercises.get(1);
        Exercise exercise3 = exercises.get(2);
        Exercise exercise4 = exercises.get(3);

        name1.setText(exercise1.getFullName());
        sets1.setText(String.format(Locale.US, "%dx%d", exercise1.getSets(), exercise1.getReps()));
        weight1.setText(String.format(Locale.US, "%d", exercise1.getWeightLB()));

        name2.setText(exercise2.getFullName());
        sets2.setText(String.format(Locale.US, "%dx%d", exercise2.getSets(), exercise2.getReps()));
        weight2.setText(String.format(Locale.US, "%d", exercise2.getWeightLB()));

        name3.setText(exercise3.getFullName());
        sets3.setText(String.format(Locale.US, "%dx%d", exercise3.getSets(), exercise3.getReps()));
        weight3.setText(String.format(Locale.US, "%d", exercise3.getWeightLB()));

        name4.setText(exercise4.getFullName());
        sets4.setText(String.format(Locale.US, "%dx%d", exercise4.getSets(), exercise4.getReps()));
        weight4.setText(String.format(Locale.US, "%d", exercise4.getWeightLB()));
        if(exercise4.getWeightLB() == Exercise.CHIN_UP_WEIGHT) {
            weight4.setKeyListener(null);
        }
        else {
            weight4.setKeyListener(weight4Listener);
        }

        date1.setText(String.format(Locale.US, "Date: " + workout.getDate()));
    }

    public void saveEntry(View view) {
        ArrayList<Exercise> exercises = workout.getExercises();
        Exercise exercise1 = exercises.get(0);
        Exercise exercise2 = exercises.get(1);
        Exercise exercise3 = exercises.get(2);
        Exercise exercise4 = exercises.get(3);

        exercise1.setWeightLB(Integer.valueOf(weight1.getText().toString()));
        exercise2.setWeightLB(Integer.valueOf(weight2.getText().toString()));
        exercise3.setWeightLB(Integer.valueOf(weight3.getText().toString()));
        exercise4.setWeightLB(Integer.valueOf(weight4.getText().toString()));

        if (position == -1) {
            MainActivity.workouts.add(0, workout);
        }
        if(position == -1 || position == 0) {
            WorkoutData.updateProgressionWeight();
            WorkoutData.updateWorkoutType();
        }
        finish();
    }

    public void showDatePickerDialog(View view) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void discardEntry(View view) {
        finish();
    }

    public WorkoutData getWorkout() {
        return workout;
    }

    public void setWorkout(WorkoutData workout) {
        this.workout = workout;
    }
}
