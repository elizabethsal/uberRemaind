package com.example.uberremaind;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NewNotificationDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) return super.onCreateDialog(savedInstanceState);
        final View view = LayoutInflater.from(activity).inflate(R.layout.dialog_new_nofication, null, false);
        return new AlertDialog.Builder(activity)
                .setView(view)
                .setTitle(activity.getString(R.string.new_notification_title))
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity activity = (MainActivity) getActivity();
                        if (activity == null) return;

                        String title = ((EditText) view.findViewById(R.id.title)).getText().toString();
                        String description = ((EditText) view.findViewById(R.id.description)).getText().toString();

                        String toastMessage;
                        if (title.length() <= 0 && description.length() <= 0) {
                            toastMessage = activity.getString(R.string.empty_notification_alert);
                        } else {
                            toastMessage = title + "\n" + description;
                        }
                        Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG).show();
                        dismissAllowingStateLoss();
                    }
                }).setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismissAllowingStateLoss();
                    }
                }).create();
    }
}
