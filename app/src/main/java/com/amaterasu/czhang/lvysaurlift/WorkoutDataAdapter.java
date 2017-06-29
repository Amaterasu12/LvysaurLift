package com.amaterasu.czhang.lvysaurlift;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by czhang on 6/13/2017.
 */

public class WorkoutDataAdapter extends RecyclerView.Adapter<WorkoutDataAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public static final String EXTRA_INDEX = "com.amaterasu.czhang.lvysaurlift.INDEX";

        public TextView name1;
        public TextView sets1;
        public TextView weight1;
        public TextView name2;
        public TextView sets2;
        public TextView weight2;
        public TextView name3;
        public TextView sets3;
        public TextView weight3;
        public TextView name4;
        public TextView sets4;
        public TextView weight4;

        public TextView dateView;
        public TextView typeView;
        public ViewHolder(View itemView) {
            super(itemView);
            name1 = (TextView) itemView.findViewById(R.id.name1);
            sets1 = (TextView) itemView.findViewById(R.id.sets1);
            weight1 = (TextView) itemView.findViewById(R.id.weight1);
            name2 = (TextView) itemView.findViewById(R.id.name2);
            sets2 = (TextView) itemView.findViewById(R.id.sets2);
            weight2 = (TextView) itemView.findViewById(R.id.weight2);
            name3 = (TextView) itemView.findViewById(R.id.name3);
            sets3 = (TextView) itemView.findViewById(R.id.sets3);
            weight3 = (TextView) itemView.findViewById(R.id.weight3);
            name4 = (TextView) itemView.findViewById(R.id.name4);
            sets4 = (TextView) itemView.findViewById(R.id.sets4);
            weight4 = (TextView) itemView.findViewById(R.id.weight4);
            dateView = (TextView) itemView.findViewById(R.id.date);
            typeView = (TextView) itemView.findViewById(R.id.workoutType);
            CardView cardView = (CardView) itemView.findViewById(R.id.card_view);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(m_context, EditWorkoutActivity.class);
                intent.putExtra(EXTRA_INDEX, position);
                m_context.startActivity(intent);
            }
        }

    }

    private ArrayList<WorkoutData> m_workouts;
    private Context m_context;

    public WorkoutDataAdapter(Context context, ArrayList<WorkoutData> workouts) {
        m_workouts = workouts;
        m_context = context;
    }

    private Context getContext() {
        return m_context;
    }

    @Override
    public WorkoutDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View workoutDataView = inflater.inflate(R.layout.item_workout, parent, false);

        ViewHolder viewHolder = new ViewHolder(workoutDataView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WorkoutDataAdapter.ViewHolder viewHolder, int position) {
        WorkoutData workoutData = m_workouts.get(position);

        ArrayList<Exercise> exercises = workoutData.getExercises();
        Exercise exercise1 = exercises.get(0);
        Exercise exercise2 = exercises.get(1);
        Exercise exercise3 = exercises.get(2);
        Exercise exercise4 = exercises.get(3);

        TextView name1 = viewHolder.name1;
        name1.setText(exercise1.getShortName());
        TextView sets1 = viewHolder.sets1;
        sets1.setText(String.format(Locale.US, "%dx%d", exercise1.getSets(), exercise1.getReps()));
        TextView weight1 = viewHolder.weight1;
        weight1.setText(String.format(Locale.US, "%dlb", exercise1.getWeightLB()));

        TextView name2 = viewHolder.name2;
        name2.setText(exercise2.getShortName());
        TextView sets2 = viewHolder.sets2;
        sets2.setText(String.format(Locale.US, "%dx%d", exercise2.getSets(), exercise2.getReps()));
        TextView weight2 = viewHolder.weight2;
        weight2.setText(String.format(Locale.US, "%dlb", exercise2.getWeightLB()));

        TextView name3 = viewHolder.name3;
        name3.setText(exercise3.getShortName());
        TextView sets3 = viewHolder.sets3;
        sets3.setText(String.format(Locale.US, "%dx%d", exercise3.getSets(), exercise3.getReps()));
        TextView weight3 = viewHolder.weight3;
        weight3.setText(String.format(Locale.US, "%dlb", exercise3.getWeightLB()));

        TextView name4 = viewHolder.name4;
        name4.setText(exercise4.getShortName());
        TextView sets4 = viewHolder.sets4;
        sets4.setText(String.format(Locale.US, "%dx%d", exercise4.getSets(), exercise4.getReps()));
        TextView weight4 = viewHolder.weight4;
        weight4.setText(String.format(Locale.US, "%dlb", exercise4.getWeightLB()));

        viewHolder.dateView.setText(workoutData.getDate());
        viewHolder.typeView.setText(String.format(Locale.US, "Split: %s", workoutData.getWorkoutType().toString()));
    }

    @Override
    public int getItemCount() {
        return m_workouts.size();
    }
}
