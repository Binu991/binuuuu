package com.example.meal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class AddRecipeActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView recipeImage;
    private EditText recipeTitle, recipeIngredients, recipeInstructions;
    private Button chooseImageBtn, doneBtn;
    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        recipeImage = findViewById(R.id.recipeImage);
        recipeTitle = findViewById(R.id.recipeTitle);
        recipeIngredients = findViewById(R.id.recipeIngredients);
        recipeInstructions = findViewById(R.id.recipeInstructions);
        chooseImageBtn = findViewById(R.id.chooseImageBtn);
        doneBtn = findViewById(R.id.doneButton);

        chooseImageBtn.setOnClickListener(v -> openImagePicker());

        doneBtn.setOnClickListener(v -> {
            String title = recipeTitle.getText().toString().trim();
            String ingredients = recipeIngredients.getText().toString().trim();
            String instructions = recipeInstructions.getText().toString().trim();

            if (title.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Recipe Added!", Toast.LENGTH_SHORT).show();
                finish(); // go back to previous screen
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                recipeImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
