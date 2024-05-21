package com.example.runnerplaner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TrainingAdapter extends BaseAdapter {
    private Context context;
    private List<OneTraining> trainingList;

    public TrainingAdapter(Context context, List<OneTraining> trainingList) {
        this.context = context;
        this.trainingList = trainingList;
    }

    @Override
    public int getCount() {
        return trainingList.size();
    }

    @Override
    public Object getItem(int position) {
        return trainingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View trainingItemView = convertView;

        if (trainingItemView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            trainingItemView = inflater.inflate(R.layout.activity_old, null);
        }

        // Uzmi trenutni trening
        OneTraining currentTraining = (OneTraining) getItem(position);

        // Postavi informacije o treningu u odgovarajuće TextView-ove
        TextView dateTextView = trainingItemView.findViewById(R.id.dateTextView);
        dateTextView.setText("Datum: " + currentTraining.getDate());

        TextView typeTextView = trainingItemView.findViewById(R.id.typeTextView);
        typeTextView.setText("Tip treninga: " + currentTraining.getType());

        TextView distanceTextView = trainingItemView.findViewById(R.id.distanceTextView);
        distanceTextView.setText("Distanca: " + currentTraining.getDistance() + " kilometara");

        TextView timeTextView = trainingItemView.findViewById(R.id.timeTextView);
        timeTextView.setText("Vreme: " + currentTraining.getTime() + " minuta");

        return trainingItemView;
    }
}
