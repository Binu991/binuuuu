package com.example.meal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MealPlanScreenActivity extends AppCompatActivity {

    private ImageButton backButton;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private MealAdapter adapter;
    private ArrayList<MealModel> mealList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan_screen);

        backButton = findViewById(R.id.backButton);
        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.mealRecyclerView);

        mealList = new ArrayList<>();
        mealList.add(new MealModel("Spaghetti Carbonara", R.drawable.spha));
        mealList.add(new MealModel("Chicken Stir Fry", R.drawable.chicken));
        mealList.add(new MealModel("Paneer Butter Masala", R.drawable.paneer));
        mealList.add(new MealModel("Garlic Chicken", R.drawable.garlic_chicken));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MealAdapter(mealList, meal -> {
            Intent intent = new Intent(MealPlanScreenActivity.this, RecipeDetailActivity.class);
            intent.putExtra("meal_name", meal.getName());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });

        backButton.setOnClickListener(v -> finish());
    }
}
