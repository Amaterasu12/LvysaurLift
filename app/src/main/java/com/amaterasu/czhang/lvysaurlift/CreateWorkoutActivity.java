package com.amaterasu.czhang.lvysaurlift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class CreateWorkoutActivity extends AppCompatActivity {
    private WorkoutData workout;
    private TextView date1;

    private TextView name1;
    private TextView sets1;
    private EditText weight1;
    private TextView name2;
    private TextView sets2;
    private EditText weight2;
    private TextView name3;
    private TextView sets3;
    private EditText weight3;
    private TextView name4;
    private TextView sets4;
    private EditText weight4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);
        setTitle("Create New Entry");

        workout = new WorkoutData(WorkoutData.WorkoutType.A_1);
        ArrayList<Exercise> exercises = workout.getExercises();
        Exercise exercise1 = exercises.get(0);
        Exercise exercise2 = exercises.get(1);
        Exercise exercise3 = exercises.get(2);
        Exercise exercise4 = exercises.get(3);

        name1 = (TextView) findViewById(R.id.name1);
        name1.setText(exercise1.getFullName());
        sets1 = (TextView) findViewById(R.id.sets1);
        sets1.setText(String.format(Locale.US, "%dx%d", exercise1.getSets(), exercise1.getReps()));
        weight1 = (EditText) findViewById(R.id.weight1);
        weight1.setText(String.format(Locale.US, "%d", exercise1.getWeightLB()));

        name2 = (TextView) findViewById(R.id.name2);
        name2.setText(exercise2.getFullName());
        sets2 = (TextView) findViewById(R.id.sets2);
        sets2.setText(String.format(Locale.US, "%dx%d", exercise2.getSets(), exercise2.getReps()));
        weight2 = (EditText) findViewById(R.id.weight2);
        weight2.setText(String.format(Locale.US, "%d", exercise2.getWeightLB()));

        name3 = (TextView) findViewById(R.id.name3);
        name3.setText(exercise3.getFullName());
        sets3 = (TextView) findViewById(R.id.sets3);
        sets3.setText(String.format(Locale.US, "%dx%d", exercise3.getSets(), exercise3.getReps()));
        weight3 = (EditText) findViewById(R.id.weight3);
        weight3.setText(String.format(Locale.US, "%d", exercise3.getWeightLB()));

        name4 = (TextView) findViewById(R.id.name4);
        name4.setText(exercise4.getFullName());
        sets4 = (TextView) findViewById(R.id.sets4);
        sets4.setText(String.format(Locale.US, "%dx%d", exercise4.getSets(), exercise4.getReps()));
        weight4 = (EditText) findViewById(R.id.weight4);
        weight4.setText(String.format(Locale.US, "%d", exercise4.getWeightLB()));

        date1 = (TextView) findViewById(R.id.dateView);
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
        MainActivity.workouts.add(0, this.workout);

        finish();
    }

    public void discardEntry(View view) {
        finish();
    }
}
