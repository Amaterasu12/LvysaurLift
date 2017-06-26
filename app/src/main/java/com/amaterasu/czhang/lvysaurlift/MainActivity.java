package com.amaterasu.czhang.lvysaurlift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<WorkoutData> workouts;
    private WorkoutDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Workout History");

        RecyclerView rvWorkouts = (RecyclerView) findViewById(R.id.rvWorkouts);

        workouts = WorkoutData.createWorkoutList(0);

        adapter = new WorkoutDataAdapter(this, workouts);
        rvWorkouts.setAdapter(adapter);
        rvWorkouts.setLayoutManager(new LinearLayoutManager(this));
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

}
