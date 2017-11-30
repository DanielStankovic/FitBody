package link.fitbody;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import link.fitbody.R;
import link.fitbody.link.fitbody.core.Health;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalorieCalculatorFragment extends Fragment implements View.OnClickListener {


    Button calculateBtn;
    EditText ageText;
    RadioGroup genderRadioGroup;
    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;
    EditText heightText;
    EditText weightText;
    Spinner activitySpinner;


    public CalorieCalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_calorie_calculator, container, false);
        calculateBtn = (Button)view.findViewById(R.id.calorieCalculatorButton);
        calculateBtn.setOnClickListener(this);

        ageText = (EditText)view.findViewById(R.id.calorieCalculatorAge);
        genderRadioGroup = (RadioGroup)view.findViewById(R.id.genderRadioGroup);
        heightText = (EditText)view.findViewById(R.id.calorieCalculatorHeight);
        weightText = (EditText)view.findViewById(R.id.calorieCalculatorWeight);
        activitySpinner = (Spinner)view.findViewById(R.id.calorieCalculatorSpinner);

        maleRadioButton = (RadioButton)view.findViewById(R.id.calorieCalculatorMale);
        femaleRadioButton = (RadioButton)view.findViewById(R.id.calorieCalculatorFemale);

        return view;
    }

    @Override
    public void onClick(View view) {

        String age = ageText.getText().toString();
        String height = heightText.getText().toString();
        String weight = weightText.getText().toString();
        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        int selectedActivityIndex = activitySpinner.getSelectedItemPosition();

        if(age.equals("") || height.equals("") || weight.equals("") || selectedGenderId == -1 || selectedActivityIndex == 0){
            if(listener != null){
                listener.onFragmentSendMessage("Please, enter all values.");
            }

        }
        else {

            int ageNum = Integer.valueOf(age);
            double heightNum = Double.valueOf(height);
            double weightNum = Double.valueOf(weight);
            String gender = "";

            if(selectedGenderId == maleRadioButton.getId()){
                gender = "M";
            }
            else if(selectedGenderId == femaleRadioButton.getId()){
                gender = "F";
            }

            String [] spinnerValues = getResources().getStringArray(R.array.spinner_values);
            double activity = Double.valueOf(spinnerValues[selectedActivityIndex]);

            Health health = new Health();
            int result = health.calculateCalorie(gender, ageNum, heightNum,weightNum, activity);

            if(result != -1){
                Spannable spannable = new SpannableString("You need " + result + " calories/day to maintain your weight.");
                spannable.setSpan(new ForegroundColorSpan(Color.rgb(239,106,144)),9,9 + String.valueOf(result).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                if(listener != null){
                    listener.onFragmentSendResult("Calorie Calculator", spannable);
                }

            }
        }

    }

    onFragmentSendMessageListener listener;
    public void onAttach(Context context){
        super.onAttach(context);
       this.listener = (onFragmentSendMessageListener)context;
    }
}
