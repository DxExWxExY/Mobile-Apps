package dxexwxexy.activities.Tuition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.RadioGroup;
import android.widget.TextView;

import dxexwxexy.activities.R;

public class TuitionCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuition_calculator);
        setTitle("Tuition Calculator");

        TextView hours = findViewById(R.id.hours);
        hours.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView bill = findViewById(R.id.result);
                if (!hours.getText().toString().equals("")) {
                    Tuition tuition = new Tuition(Integer.parseInt(hours.getText().toString()), onResidencySelected(), onLevelSelected());
                    bill.setText("");
                    bill.append("Bill");
                    bill.append("\nTuition Cost: $" + tuition.getTuition());
                    bill.append("\nFees Cost: $" + tuition.getFees());
                    bill.append("\nTotal: $"+tuition.getTotal());
                } else {
                    bill.clearComposingText();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    int onLevelSelected() {
        RadioGroup residency = findViewById(R.id.edu_level);
        switch (residency.getCheckedRadioButtonId()) {
            case R.id.grad:
                return Tuition.GRADUATE;
            case R.id.undergrad:
                return Tuition.UNDERGRADUATE;
        }
        return -1;
    }

    int onResidencySelected() {
        RadioGroup level = findViewById(R.id.recidency);
        switch (level.getCheckedRadioButtonId()) {
            case R.id.in_state:
                return Tuition.IN_STATE;
            case R.id.out_state:
                return Tuition.OUT_STATE;
        }
        return -1;
    }

}
