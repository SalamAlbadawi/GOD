package algonquin.cst2335.god;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import algonquin.cst2335.god.R;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_NAME = "UserPreferences";
    private static final String KEY_WIDTH = "width";
    private static final String KEY_HEIGHT = "height";

    private EditText etWidth;
    private EditText etHeight;
    private Button btnGenerate;
    private Button btnShowSavedImages;
    private ImageView imageDisplay;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        etWidth = findViewById(R.id.etWidth);
        etHeight = findViewById(R.id.etHeight);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnShowSavedImages = findViewById(R.id.btnShowSavedImages);
        imageDisplay = findViewById(R.id.imageDisplay);

        // Add input validation for positive whole numbers
        etWidth.setFilters(new InputFilter[]{new PositiveWholeNumberInputFilter()});
        etHeight.setFilters(new InputFilter[]{new PositiveWholeNumberInputFilter()});

        // Add text change listeners to validate input while typing
        etWidth.addTextChangedListener(new PositiveWholeNumberTextWatcher(etWidth));
        etHeight.addTextChangedListener(new PositiveWholeNumberTextWatcher(etHeight));

        // Restore user's previous input from SharedPreferences
        int savedWidth = preferences.getInt(KEY_WIDTH, 0);
        int savedHeight = preferences.getInt(KEY_HEIGHT, 0);

        etWidth.setText(String.valueOf(savedWidth));
        etHeight.setText(String.valueOf(savedHeight));

        // Add click listener for the Generate Image button
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateImage();
            }
        });

        // Add click listener for the Show Saved Images button
        btnShowSavedImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSavedImages();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save user's input to SharedPreferences when the app is paused
        String widthInput = etWidth.getText().toString();
        String heightInput = etHeight.getText().toString();

        int width = TextUtils.isEmpty(widthInput) ? 0 : Integer.parseInt(widthInput);
        int height = TextUtils.isEmpty(heightInput) ? 0 : Integer.parseInt(heightInput);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_WIDTH, width);
        editor.putInt(KEY_HEIGHT, height);
        editor.apply();
    }

    private void generateImage() {
        String widthInput = etWidth.getText().toString();
        String heightInput = etHeight.getText().toString();

        if (TextUtils.isEmpty(widthInput) || TextUtils.isEmpty(heightInput)) {
            Toast.makeText(this, "Please enter both width and height.", Toast.LENGTH_SHORT).show();
            return;
        }

        int width = Integer.parseInt(widthInput);
        int height = Integer.parseInt(heightInput);

        // Implement image generation logic here using the given width and height
        // For example, you can create a Bitmap or load an image into the ImageView.
        // For demonstration purposes, let's set a placeholder image.

        imageDisplay.setImageResource(R.drawable.icon1);
    }

    private void showSavedImages() {
        // Implement logic to show the list of saved images here (if applicable).
        Toast.makeText(this, "Showing saved images...", Toast.LENGTH_SHORT).show();
    }

    // InputFilter to allow only positive whole numbers
    private static class PositiveWholeNumberInputFilter implements InputFilter {
        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            if (TextUtils.isEmpty(source)) {
                return null; // Allow empty input
            }

            String input = dest.subSequence(0, dstart) + source.toString() + dest.subSequence(dend, dest.length());
            if (input.matches("0") || input.matches("-0")) {
                return ""; // Prevent leading zero
            }

            try {
                int value = Integer.parseInt(input);
                if (value < 0) {
                    return ""; // Prevent negative numbers
                }
            } catch (NumberFormatException e) {
                return ""; // Prevent non-numeric input
            }

            return null; // Accept the input
        }
    }

    // TextWatcher to validate input while typing
    private static class PositiveWholeNumberTextWatcher implements TextWatcher {
        private EditText editText;

        public PositiveWholeNumberTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String input = s.toString();
            if (input.matches("0") || input.matches("-0")) {
                s.clear(); // Clear leading zero
            }

            try {
                int value = Integer.parseInt(input);
                if (value < 0) {
                    s.clear(); // Clear negative numbers
                }
            } catch (NumberFormatException e) {
                s.clear(); // Clear non-numeric input
            }
        }
    }
}
