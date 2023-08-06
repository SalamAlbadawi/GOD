package algonquin.cst2335.god;

// GeneratedImageActivity class

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class GeneratedImageActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_image);

        imageView = findViewById(R.id.imageView);

        // Retrieve the image URL from the intent extra
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("image_url")) {
            String imageUrl = intent.getStringExtra("image_url");

            // Load the image into the ImageView using Picasso or your preferred image loading library
            Picasso.get().load(imageUrl).into(imageView);
        }
    }
}
