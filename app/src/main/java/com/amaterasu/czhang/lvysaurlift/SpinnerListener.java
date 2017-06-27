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
        if(id == act.spinner.getId()){
            act.setWorkout(new WorkoutData(WorkoutData.WorkoutType.A_2));
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }
}

