package com.example.meal;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ImportRecipeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_recipe);

        Toast.makeText(this, "Feature coming soon: Import from the Web", Toast.LENGTH_LONG).show();
    }
}
