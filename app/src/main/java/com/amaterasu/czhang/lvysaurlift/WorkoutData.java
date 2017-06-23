package com.amaterasu.czhang.lvysaurlift;

import android.support.v7.widget.CardView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by czhang on 6/13/2017.
 */

public class WorkoutData {
    public enum WorkoutType {
        A_1, A_2, A_3, B_1, B_2, B_3
    }

    private ArrayList<Exercise> exercises;
    private int year;
    private int month;
    private int day;

    public WorkoutData(WorkoutType type) {
        exercises = new ArrayList<>(4);

        //implement the different types in a future iteration
        switch (type) {
            case A_1:
                exercises.add(new Exercise("Bench Press", "BP", 40, 4, 4));
                exercises.add(new Exercise("Squat", "SQ", 35, 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", 35, 4, 8));
                exercises.add(new Exercise("Chinups", "CHUP", -1, 4, 4));
                break;
            case A_2:
                exercises.add(new Exercise("Bench Press", "BP", 35, 4, 8));
                exercises.add(new Exercise("Deadlift", "DL", 40, 4, 4));
                exercises.add(new Exercise("Overhead Press", "OHP", 40, 4, 4));
                exercises.add(new Exercise("Barbell Rows", "ROW", 40, 4, 4));
                break;
            case A_3:
                exercises.add(new Exercise("Bench Press", "BP", 40, 3, 4));
                exercises.add(new Exercise("Squat", "SQ", 40, 3, 4));
                exercises.add(new Exercise("Overhead Press", "OHP", 40, 4, 8));
                exercises.add(new Exercise("Chinups", "CHUP", -1, 4, 4));
                break;
            case B_1:
                exercises.add(new Exercise("Bench Press", "BP", 35, 4, 8));
                exercises.add(new Exercise("Deadlift", "DL", 35, 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", 40, 4, 4));
                exercises.add(new Exercise("Barbell Rows", "ROW", 40, 4, 4));
                break;
            case B_2:
                exercises.add(new Exercise("Bench Press", "BP", 40, 4, 4));
                exercises.add(new Exercise("Squat", "SQ", 35, 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", 35, 4, 8));
                exercises.add(new Exercise("Chinups", "CHUP", -1, 4, 8));
                break;
            case B_3:
                exercises.add(new Exercise("Bench Press", "BP", 35, 4, 8));
                exercises.add(new Exercise("Deadlift", "DL", 40, 3, 4));
                exercises.add(new Exercise("Overhead Press", "OHP", 40, 4, 4));
                exercises.add(new Exercise("Barbell Rows", "ROW", 35, 4, 8));
                break;
        }
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day = c.get(Calendar.DAY_OF_MONTH);
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }


    public static ArrayList<WorkoutData> createWorkoutList(int numWorkouts) {
        ArrayList<WorkoutData> workouts = new ArrayList<>();
        for (int i = 0; i < numWorkouts; i++) {
            if(i%6 == 1){
                workouts.add(new WorkoutData(WorkoutData.WorkoutType.A_1));
            }
            else if(i%6 == 2){
                workouts.add(new WorkoutData(WorkoutData.WorkoutType.A_2));
            }
            else if(i%6 == 3){
                workouts.add(new WorkoutData(WorkoutData.WorkoutType.A_3));
            }
            else if(i%6 == 4){
                workouts.add(new WorkoutData(WorkoutData.WorkoutType.B_1));
            }
            else if(i%6 == 5){
                workouts.add(new WorkoutData(WorkoutData.WorkoutType.B_2));
            }
            else {
                workouts.add(new WorkoutData(WorkoutData.WorkoutType.B_3));
            }
        }
        return workouts;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDate() {
        String temp;
        temp = String.format(Locale.US, "%d-%d-%d", year, month, day);
        return temp;
    }
}
