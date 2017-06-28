package com.amaterasu.czhang.lvysaurlift;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by czhang on 6/27/2017.
 */

public class SpinnerListener implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        EditWorkoutActivity act = (EditWorkoutActivity) view.getContext();
        if(pos == 0 && act.getWorkout().getWorkoutType() != WorkoutData.WorkoutType.A_1){
            act.setWorkout(new WorkoutData(WorkoutData.WorkoutType.A_1));
            act.loadDataView();
        }
        else if(pos == 1 && act.getWorkout().getWorkoutType() != WorkoutData.WorkoutType.A_2){
            act.setWorkout(new WorkoutData(WorkoutData.WorkoutType.A_2));
            act.loadDataView();
        }
        else if(pos == 2 && act.getWorkout().getWorkoutType() != WorkoutData.WorkoutType.A_3){
            act.setWorkout(new WorkoutData(WorkoutData.WorkoutType.A_3));
            act.loadDataView();
        }
        else if(pos == 3 && act.getWorkout().getWorkoutType() != WorkoutData.WorkoutType.B_1){
            act.setWorkout(new WorkoutData(WorkoutData.WorkoutType.B_1));
            act.loadDataView();
        }
        else if(pos == 4 && act.getWorkout().getWorkoutType() != WorkoutData.WorkoutType.B_2){
            act.setWorkout(new WorkoutData(WorkoutData.WorkoutType.B_2));
            act.loadDataView();
        }
        else if(pos == 5 && act.getWorkout().getWorkoutType() != WorkoutData.WorkoutType.B_3){
            act.setWorkout(new WorkoutData(WorkoutData.WorkoutType.B_3));
            act.loadDataView();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}

