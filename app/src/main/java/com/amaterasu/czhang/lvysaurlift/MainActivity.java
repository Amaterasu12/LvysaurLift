package com.amaterasu.czhang.lvysaurlift;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private String WORKOUT_DATA = "workouts_data.txt";
    private String METADATA = "metadata.txt";
    public static ArrayList<WorkoutData> workouts;
    private WorkoutDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Workout Log");
        RecyclerView rvWorkouts = (RecyclerView) findViewById(R.id.rvWorkouts);

        try {
            FileInputStream fis = openFileInput(WORKOUT_DATA);
            ObjectInputStream ois = new ObjectInputStream(fis);
            workouts = (ArrayList) ois.readObject();
            ois.close();

            fis = openFileInput(METADATA);
            ois = new ObjectInputStream(fis);
            StaticMetadataHelper metadata = (StaticMetadataHelper) ois.readObject();
            ois.close();
            metadata.restoreMetadata();
        }   catch (IOException e) {
            workouts = WorkoutData.createWorkoutList(0);
        }   catch (ClassNotFoundException c) {
            workouts = WorkoutData.createWorkoutList(0);
        }

        adapter = new WorkoutDataAdapter(this, workouts);
        rvWorkouts.setAdapter(adapter);
        rvWorkouts.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.clear_all_button) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm Deletion");
            builder.setMessage("Clear the workout log?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    WorkoutData.clearWorkouts();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            if(!workouts.isEmpty()) {
                AlertDialog alert = builder.create();
                alert.show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void addCard(View view) {
        Intent intent = new Intent(this, EditWorkoutActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();

        try {
            FileOutputStream fos = openFileOutput(WORKOUT_DATA, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(workouts);
            oos.close();

            fos = openFileOutput(METADATA, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            StaticMetadataHelper metadata = new StaticMetadataHelper();
            oos.writeObject(metadata);
            oos.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }

}
