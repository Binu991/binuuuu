package com.example.meal;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class YourRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MealAdapter adapter;
    private ArrayList<MealModel> yourRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_recipes);

        recyclerView = findViewById(R.id.yourRecipesRecyclerView);
        yourRecipeList = new ArrayList<>();

        yourRecipeList.add(new MealModel("My Custom Pasta", R.drawable.placeholder_image));
        yourRecipeList.add(new MealModel("Tandoori Wrap", R.drawable.placeholder_image));

        adapter = new MealAdapter(yourRecipeList, meal -> {
            Toast.makeText(this, meal.getName(), Toast.LENGTH_SHORT).show();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
