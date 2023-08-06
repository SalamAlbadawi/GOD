package algonquin.cst2335.god;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class SavedImagesActivity extends AppCompatActivity {

    private ImageDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_images);

        database = ImageDatabase.getInstance(this);
        new GetImagesTask().execute();
    }

    private class GetImagesTask extends AsyncTask<Void, Void, List<ImageEntity>> {
        @Override
        protected List<ImageEntity> doInBackground(Void... voids) {
            return database.imageDao().getAllImages();
        }

        @Override
        protected void onPostExecute(List<ImageEntity> images) {
            // Update the UI with the fetched images
            // Note: Be cautious when updating the UI from here. Make sure the activity is still active.
            // You might need to handle edge cases, such as the activity being destroyed or recreated.
        }
    }
}
