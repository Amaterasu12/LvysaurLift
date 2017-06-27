package com.amaterasu.czhang.lvysaurlift;

import android.support.v7.widget.CardView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by czhang on 6/13/2017.
 */

public class WorkoutData {
    public static int BP = 50;
    public static int SQ = 50;
    public static int OHP = 50;
    public static int DL = 50;
    public static int ROW = 50;

    public enum WorkoutType {
        A_1, A_2, A_3, B_1, B_2, B_3, NONE
    }

    private ArrayList<Exercise> exercises;
    private int year;
    private int month;
    private int day;
    private WorkoutType workoutType;
    public static WorkoutType currentType = WorkoutType.A_1;

    public WorkoutData(WorkoutType type) {
        exercises = new ArrayList<>(4);
        WorkoutType mtype = (type == WorkoutType.NONE) ? currentType : type;
        //implement the different types in a future iteration
        switch (mtype) {
            case A_1:
                BP+=5;
                SQ+=5;
                OHP+=5;
                exercises.add(new Exercise("Bench Press", "BP", BP, 4, 4));
                exercises.add(new Exercise("Squat", "SQ", (int)(SQ*0.9), 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", (int)(OHP*0.9), 4, 8));
                exercises.add(new Exercise("Chinups", "CHUP", -1, 4, 4));
                break;
            case A_2:
                DL+=5;
                ROW+=5;
                exercises.add(new Exercise("Bench Press", "BP", (int)(BP*0.9), 4, 8));
                exercises.add(new Exercise("Deadlift", "DL", DL, 4, 4));
                exercises.add(new Exercise("Overhead Press", "OHP", OHP, 4, 4));
                exercises.add(new Exercise("Barbell Rows", "ROW", ROW, 4, 4));
                break;
            case A_3:
                BP+=5;
                SQ+=5;
                exercises.add(new Exercise("Bench Press", "BP", BP, 3, 4));
                exercises.add(new Exercise("Squat", "SQ", SQ, 3, 4));
                exercises.add(new Exercise("Overhead Press", "OHP", (int)(OHP*0.9), 4, 8));
                exercises.add(new Exercise("Chinups", "CHUP", -1, 4, 4));
                break;
            case B_1:
                DL+=5;
                OHP+=5;
                ROW+=5;
                exercises.add(new Exercise("Bench Press", "BP", (int)(BP*0.9), 4, 8));
                exercises.add(new Exercise("Deadlift", "DL", (int)(DL*0.9), 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", OHP, 4, 4));
                exercises.add(new Exercise("Barbell Rows", "ROW", ROW, 4, 4));
                break;
            case B_2:
                BP+=5;
                SQ+=5;
                exercises.add(new Exercise("Bench Press", "BP", BP, 4, 4));
                exercises.add(new Exercise("Squat", "SQ", (int)(SQ*0.9), 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", (int)(OHP*0.9), 4, 8));
                exercises.add(new Exercise("Chinups", "CHUP", -1, 4, 8));
                break;
            case B_3:
                DL+=5;
                ROW+=5;
                exercises.add(new Exercise("Bench Press", "BP", (int)(BP*0.9), 4, 8));
                exercises.add(new Exercise("Deadlift", "DL", DL, 3, 4));
                exercises.add(new Exercise("Overhead Press", "OHP", OHP, 4, 4));
                exercises.add(new Exercise("Barbell Rows", "ROW", (int)(ROW*0.9), 4, 8));
                break;
        }
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        workoutType = mtype;
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

    public static void updateProgressionWeight (WorkoutData workout) {
        ArrayList<Exercise> exercises = workout.getExercises();
        int newBP = 0;
        int newSQ = 0;
        int newOHP = 0;
        int newDL = 0;
        int newROW = 0;

        switch (workout.getWorkoutType()) {
            case A_1:
                newBP = exercises.get(0).getWeightLB();
                newSQ = exercises.get(1).getWeightLB();
                newOHP = exercises.get(2).getWeightLB();
                break;
            case A_2:
                break;
            case A_3:
                break;

        }
        if(newBP > BP) { BP = newBP; }
        if(newSQ > SQ) { SQ = newSQ; }
        if(newOHP > OHP) { OHP = newOHP; }
        if(newDL > DL) { DL = newDL; }
        if(newROW > ROW) { ROW = newROW; }
    }

    public static void updateWorkoutType (WorkoutType type) {
        switch (type) {
            case A_1:
                currentType = WorkoutType.A_2;
                break;
            case A_2:
                currentType = WorkoutType.A_3;
                break;
            case A_3:
                currentType = WorkoutType.B_1;
                break;
            case B_1:
                currentType = WorkoutType.B_2;
                break;
            case B_2:
                currentType = WorkoutType.B_3;
                break;
            case B_3:
                currentType = WorkoutType.A_1;
                break;
        }
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

    public void setWorkoutType(WorkoutType workoutType) {
        this.workoutType = workoutType;
    }

    public String getDate() {
        String temp;
        temp = String.format(Locale.US, "%d-%d-%d", year, month+1, day);
        return temp;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public WorkoutType getWorkoutType() {
        return workoutType;
    }
}
