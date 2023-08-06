package algonquin.cst2335.god;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SavedImagesActivity extends AppCompatActivity {

    private RecyclerView savedImagesRecyclerView;
    private ImageDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_images);

        savedImagesRecyclerView = findViewById(R.id.savedImagesRecyclerView);
        savedImagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = ImageDatabase.getInstance(this);
        List<ImageEntity> images = database.imageDao().getAllImages();
        ImageAdapter adapter = new ImageAdapter(images, this);
        savedImagesRecyclerView.setAdapter(adapter);
    }
}

