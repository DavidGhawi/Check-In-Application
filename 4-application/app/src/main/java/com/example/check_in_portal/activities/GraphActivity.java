package com.example.check_in_portal.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.check_in_portal.R;
import com.example.check_in_portal.databinding.GraphActivityBinding;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    private GraphView graphView;
    private GraphActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the binding layout and set it as the content view
        binding = GraphActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Find the GraphView in the layout
        graphView = findViewById(R.id.idGraphView);

        // Get the score and date data arrays from the Intent
        int[] dateList = getIntent().getIntArrayExtra("date_list");
        int[] scoreList = getIntent().getIntArrayExtra("score_list");

        // Loops through the arrays and adds new DataPoints
        if (dateList != null) {
            if (dateList.length >= 2) {
                binding.idGraphView.setVisibility(View.VISIBLE);
                binding.errorMessage.setVisibility(View.INVISIBLE);
                ArrayList<DataPoint> data = new ArrayList();
                for (int i = 0; i < dateList.length; i++) {
                    data.add(new DataPoint(dateList[i], scoreList[i]));
                }

                // initializes the array of DataPoint objects
                LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(data.toArray(new DataPoint[0]));

                // Add the series to the GraphView
                graphView.addSeries(series1);

                // Set the number of horizontal labels to the number of dates in the array
                graphView.getGridLabelRenderer().setNumHorizontalLabels(dateList.length);

                // Set the title of the graph
                graphView.setTitle("Graph of previous check ins");

                // Set the color of the graph title
                graphView.setTitleColor(R.color.primaryTextColor);

                // Set the size of the graph title
                graphView.setTitleTextSize(60);
            }
        }
    }
}
