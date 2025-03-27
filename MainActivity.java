package com.example.meal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Button startMealPlanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startMealPlanButton = findViewById(R.id.startMealPlanButton);
        startMealPlanButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MealPlanScreenActivity.class);
            startActivity(intent);
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        NavigationView navView = findViewById(R.id.navigation_view);

        topAppBar.setNavigationOnClickListener(v -> drawerLayout.open());
        topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_notifications) {
                Toast.makeText(this, "Notifications clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.action_search) {
                Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_recipes) {
                startActivity(new Intent(MainActivity.this, BrowseMealsActivity.class));
            } else if (id == R.id.nav_grocery) {
                startActivity(new Intent(MainActivity.this, GroceryActivity.class));
            } else if (id == R.id.nav_plan) {
                startActivity(new Intent(MainActivity.this, MealPlanScreenActivity.class));
            } else if (id == R.id.nav_share) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out MealMate app!");
                startActivity(Intent.createChooser(shareIntent, "Share MealMate"));
            } else if (id == R.id.nav_about) {
                Toast.makeText(this, "About MealMate", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_logout) {
                finishAffinity(); // exit app
            }

            drawerLayout.close();
            return true;
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_meals) {
                startActivity(new Intent(MainActivity.this, MealPlanScreenActivity.class));
                return true;
            } else if (id == R.id.nav_grocery) {
                startActivity(new Intent(MainActivity.this, GroceryActivity.class));
                return true;
            } else if (id == R.id.nav_favorites) {
                startActivity(new Intent(MainActivity.this, FavoritesActivity.class));
                return true;
            } else if (id == R.id.nav_settings) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;
            }
            return false;
        });
    }
}
