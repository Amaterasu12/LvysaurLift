package com.amaterasu.czhang.lvysaurlift;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * Created by czhang on 6/26/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditWorkoutActivity act = (EditWorkoutActivity) getActivity();
        int mYear, mMonth, mDay;
        if(act != null) {
            mYear = act.getWorkout().getYear();
            mMonth = act.getWorkout().getMonth();
            mDay = act.getWorkout().getDay();
        }
        else {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
        }

        return new DatePickerDialog(getActivity(), this, mYear, mMonth, mDay);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        EditWorkoutActivity act = (EditWorkoutActivity) getActivity();
        act.getWorkout().setYear(year);
        act.getWorkout().setMonth(month);
        act.getWorkout().setDay(day);

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, day);
        act.getWorkout().setDayOfWeek(gregorianCalendar.get(Calendar.DAY_OF_WEEK));
        act.date1.setText(String.format(Locale.US, "Date: " + act.getWorkout().getDate()));
    }
}

