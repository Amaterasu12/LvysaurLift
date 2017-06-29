package com.amaterasu.czhang.lvysaurlift;

import android.support.v7.widget.CardView;
import android.widget.Switch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static com.amaterasu.czhang.lvysaurlift.WorkoutData.WorkoutType.A_1;

/**
 * Created by czhang on 6/13/2017.
 */

public class WorkoutData implements Serializable{
    public static int BP = 45;
    public static int SQ = 45;
    public static int OHP = 45;
    public static int DL = 45;
    public static int ROW = 45;
    public static WorkoutType currentType = A_1;

    public enum WorkoutType {
        A_1, A_2, A_3, B_1, B_2, B_3
    }

    private ArrayList<Exercise> exercises;
    private int year;
    private int month;
    private int day;
    private int dayOfWeek;
    private WorkoutType workoutType;

    public WorkoutData(WorkoutType type) {
        exercises = new ArrayList<>(4);
        WorkoutType mtype = type;
        //implement the different types in a future iteration
        switch (mtype) {
            case A_1:
                exercises.add(new Exercise("Bench Press", "BP", BP+5, 4, 4));
                exercises.add(new Exercise("Squat", "SQ", SQ+5, 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", OHP+5, 4, 8));
                exercises.add(new Exercise("Chinups", "CHUP", -1, 4, 8));
                break;
            case A_2:
                exercises.add(new Exercise("Bench Press", "BP", BP, 4, 8));
                exercises.add(new Exercise("Deadlift", "DL", DL+5, 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", OHP, 4, 4));
                exercises.add(new Exercise("Barbell Rows", "ROW", ROW+5, 4, 8));
                break;
            case A_3:
                exercises.add(new Exercise("Bench Press", "BP", BP+5, 4, 4));
                exercises.add(new Exercise("Squat", "SQ", SQ+5, 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", OHP, 4, 8));
                exercises.add(new Exercise("Chinups", "CHUP", -1, 4, 8));
                break;
            case B_1:
                exercises.add(new Exercise("Bench Press", "BP", BP, 4, 8));
                exercises.add(new Exercise("Deadlift", "DL", DL+5, 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", OHP+5, 4, 4));
                exercises.add(new Exercise("Barbell Rows", "ROW", ROW+5, 4, 8));
                break;
            case B_2:
                exercises.add(new Exercise("Bench Press", "BP", BP+5, 4, 4));
                exercises.add(new Exercise("Squat", "SQ", SQ+5, 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", OHP, 4, 8));
                exercises.add(new Exercise("Chinups", "CHUP", -1, 4, 8));
                break;
            case B_3:
                exercises.add(new Exercise("Bench Press", "BP", BP, 4, 8));
                exercises.add(new Exercise("Deadlift", "DL", DL+5, 4, 8));
                exercises.add(new Exercise("Overhead Press", "OHP", OHP, 4, 4));
                exercises.add(new Exercise("Barbell Rows", "ROW", ROW+5, 4, 8));
                break;
        }
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        workoutType = mtype;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }


    public static ArrayList<WorkoutData> createWorkoutList(int numWorkouts) {
        ArrayList<WorkoutData> workouts = new ArrayList<>();
        for (int i = 0; i < numWorkouts; i++) {
            if(i%6 == 1){
                workouts.add(new WorkoutData(A_1));
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


    public static void updateProgressionWeight () {
        if(MainActivity.workouts.isEmpty()) {
            BP = 45;
            SQ = 45;
            OHP = 45;
            DL = 45;
            ROW = 45;
            return;
        }

        WorkoutData workout = MainActivity.workouts.get(0);
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
                newBP = exercises.get(0).getWeightLB();
                newDL = exercises.get(1).getWeightLB();
                newOHP = exercises.get(2).getWeightLB();
                newROW = exercises.get(3).getWeightLB();
                break;
            case A_3:
                newBP = exercises.get(0).getWeightLB();
                newSQ = exercises.get(1).getWeightLB();
                newOHP = exercises.get(2).getWeightLB();
                break;
            case B_1:
                newBP = exercises.get(0).getWeightLB();
                newDL = exercises.get(1).getWeightLB();
                newOHP = exercises.get(2).getWeightLB();
                newROW = exercises.get(3).getWeightLB();
                break;
            case B_2:
                newBP = exercises.get(0).getWeightLB();
                newSQ = exercises.get(1).getWeightLB();
                newOHP = exercises.get(2).getWeightLB();
                break;
            case B_3:
                newBP = exercises.get(0).getWeightLB();
                newDL = exercises.get(1).getWeightLB();
                newOHP = exercises.get(2).getWeightLB();
                newROW = exercises.get(3).getWeightLB();
                break;
        }
        if(newBP != 0) { BP = newBP; }
        if(newSQ != 0) { SQ = newSQ; }
        if(newOHP != 0) { OHP = newOHP; }
        if(newDL != 0) { DL = newDL; }
        if(newROW != 0) { ROW = newROW; }
    }

    public static void updateWorkoutType () {
        if(MainActivity.workouts.isEmpty()) {
            currentType = WorkoutType.A_1;
            return;
        }

        WorkoutType type = MainActivity.workouts.get(0).getWorkoutType();
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
            default:
                currentType = WorkoutType.A_1;
                break;
        }
        return;
    }

    public static void clearWorkouts () {
        MainActivity.workouts.clear();
        BP = 45;
        SQ = 45;
        OHP = 45;
        DL = 45;
        ROW = 45;
        currentType = WorkoutType.A_1;
        return;
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

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDate() {
        String temp;
        switch(dayOfWeek) {
            case 1:
                temp = "Sunday, ";
                break;
            case 2:
                temp = "Monday, ";
                break;
            case 3:
                temp = "Tuesday, ";
                break;
            case 4:
                temp = "Wednesday, ";
                break;
            case 5:
                temp = "Thursday, ";
                break;
            case 6:
                temp = "Friday, ";
                break;
            case 7:
                temp = "Saturday, ";
                break;
            default:
                temp = "The Lord's Rest Day, ";
                break;
        }
        temp += String.format(Locale.US, "%d-%d-%d", year, month+1, day);
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

    public int getDayOfWeek() {
        return dayOfWeek;
    }
}
