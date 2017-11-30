package link.fitbody;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Daniel on 7/29/2017.
 */

public class ResultDialog extends DialogFragment {

    private String title;
    private String message;

    public void setTitle(String title){
        this.title = title;
    }

    public void setMessage(String message){
        this.message = message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View result_dialog = inflater.inflate(R.layout.result_dialog, null);

        ImageButton closeBtn = (ImageButton)result_dialog.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultDialog.this.dismiss();
            }
        });

        TextView titleTextView = (TextView)result_dialog.findViewById(R.id.dialogTitle);
        TextView messageTextView = (TextView)result_dialog.findViewById(R.id.dialogMessage);
        if(savedInstanceState != null){
            title = savedInstanceState.getString("TITLE");
            message = savedInstanceState.getString("MESSAGE");
        }

        if(title != null){
            titleTextView.setText(title);
        }

        if(message != null){
            messageTextView.setText(message);
        }

        builder.setView(result_dialog);

        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("TITLE", title);
        outState.putString("MESSAGE", message);
    }
}
