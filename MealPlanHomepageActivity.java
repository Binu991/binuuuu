package com.example.meal;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MealPlanHomepageActivity extends AppCompatActivity {

    public static ArrayList<String> selectedMeals = new ArrayList<>();
    private ListView mealListView;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan_homepage);

        mealListView = findViewById(R.id.selectedMealList);
        emptyView = findViewById(R.id.emptyMealText);

        if (selectedMeals.isEmpty()) {
            emptyView.setText("No meals added yet. Start your Meal Plan!");
        } else {
            emptyView.setText("");
            mealListView.setAdapter(new android.widget.ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, selectedMeals
            ));
        }
    }
}
