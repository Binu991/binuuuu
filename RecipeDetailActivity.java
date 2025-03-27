package com.example.meal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    ImageView mealImage;
    TextView mealName, mealIngredients, mealInstructions;
    Button addToMealPlanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        mealImage = findViewById(R.id.mealImage);
        mealName = findViewById(R.id.mealName);
        mealIngredients = findViewById(R.id.mealIngredients);
        mealInstructions = findViewById(R.id.mealInstructions);
        addToMealPlanButton = findViewById(R.id.addToMealPlanButton);

        String name = getIntent().getStringExtra("meal_name");

        mealName.setText(name);
        mealImage.setImageResource(R.drawable.placeholder_image); // Default
        mealIngredients.setText("• 1 tbsp oil• 1 onion• 2 tomatoes• Spices");
        mealInstructions.setText("1. Heat oil 2. Add onion, cook 3. Add tomatoes & spices 4. Simmer 10 min");

        addToMealPlanButton.setOnClickListener(v -> {
            Toast.makeText(this, name + " added to Meal Plan", Toast.LENGTH_SHORT).show();
            finish(); // Go back
        });
    }
}
