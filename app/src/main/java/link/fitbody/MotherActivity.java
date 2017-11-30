package link.fitbody;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Daniel on 5/30/2017.
 */

public abstract class MotherActivity extends AppCompatActivity {



    Toast toast = null;
    public void showCalculatorToast(CharSequence message){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.custom_toast_layout));
        TextView text = (TextView)layout.findViewById(R.id.toast_message);
        text.setText(message);
        if(toast != null)
            toast.cancel();

        toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void showResultDialog(CharSequence title, CharSequence message){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ResultDialog resultDialog = new ResultDialog();
        resultDialog.setTitle(title.toString());
        resultDialog.setMessage(message.toString());
        resultDialog.show(ft, "result_dialog");
    }


    public void showInfoToast(CharSequence message){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(null);

        DialogFragment dialogFragment = new InfoDialog();
        dialogFragment.show(ft, "tag");
    }

    protected void changeActionBarText(String text){
        getSupportActionBar().setTitle(text);
    }
    protected void enableUpButton(){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
