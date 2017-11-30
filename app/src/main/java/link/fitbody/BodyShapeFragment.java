package link.fitbody;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import link.fitbody.link.fitbody.core.Health;

/**
 * A simple {@link Fragment} subclass.
 */
public class BodyShapeFragment extends Fragment implements View.OnClickListener {
    Button calculateBtn;
    EditText chest;
    EditText waist;
    EditText hip;

    public BodyShapeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_body_shape, container, false);
        calculateBtn = (Button)view.findViewById(R.id.calculateButton);
        calculateBtn.setOnClickListener(this);

        chest = (EditText)view.findViewById(R.id.bodyShapeChestSize);
        waist = (EditText)view.findViewById(R.id.bodyShapeWaistSize);
        hip = (EditText)view.findViewById(R.id.bodyShapeHipSize);



        return view;
    }

    @Override
    public void onClick(View view) {

        if (chest.getText().toString().equals("") || waist.getText().toString().equals("") || hip.getText().toString().equals("")) {
            if(listener != null){
                listener.onFragmentSendMessage("Please enter all values.");
            }



        }
        else{
            double chestNum = Double.valueOf(chest.getText().toString());
            double waistNum = Double.valueOf(waist.getText().toString());
            double hipNum = Double.valueOf(hip.getText().toString());
            Health health = new Health();
            String result = health.calculateBodyType(chestNum, waistNum, hipNum);
            Spannable spannable = new SpannableString("Your body type is " + result);
            spannable.setSpan(new ForegroundColorSpan(Color.rgb(239,106,144)), 17, spannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if(listener != null){
                listener.onFragmentSendResult("Body Shape", spannable);
            }
           
        }

    }

    onFragmentSendMessageListener listener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.listener = (onFragmentSendMessageListener)context;
    }
}
