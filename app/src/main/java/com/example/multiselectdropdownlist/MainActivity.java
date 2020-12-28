package com.example.multiselectdropdownlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    MaterialCardView selectCard;
    TextView tvCourses;
    boolean [] selectedCourses;
    ArrayList<Integer> courseList = new ArrayList<>();
    String [] courseArray = {"Biology","English","Physics","Chemistry","Computer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize all views
        selectCard = findViewById(R.id.selectCard);
        tvCourses = findViewById(R.id.tvCourses);
        selectedCourses = new boolean[courseArray.length];

        selectCard.setOnClickListener(v -> {

            showCoursesDialog();
        });

    }

    private void showCoursesDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Select Courses");
        builder.setCancelable(false);

        builder.setMultiChoiceItems(courseArray, selectedCourses, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked){
                    courseList.add(which);
                }else {
                    courseList.remove(which);
                }
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // create string builder
                StringBuilder stringBuilder = new StringBuilder();
                for (int i=0; i < courseList.size(); i++){

                    stringBuilder.append(courseArray[courseList.get(i)]);

                    //check condition
                    if (i != courseList.size() -1 ){

                        // when i value not equal to course list size
                        // then add a comma
                        stringBuilder.append(", ");
                    }

                    //setting selected courses to textview
                    tvCourses.setText(stringBuilder.toString());
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // clearing all selected courses on clear all click
                for (int i=0; i < selectedCourses.length; i++){

                    selectedCourses[i] = false;

                    courseList.clear();
                    tvCourses.setText("");
                }
            }
        });
        builder.show();
    }
}