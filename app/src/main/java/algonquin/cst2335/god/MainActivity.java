package algonquin.cst2335.god;

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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etWidth;
    private EditText etHeight;
    private Button btnGenerate;
    private Button btnShowSavedImages;
    private ImageView imageDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // Add click listeners for the buttons (You need to implement the click listeners)
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement image generation logic here
            }
        });

        btnShowSavedImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logic to show the list of saved images here
            }
        });
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
