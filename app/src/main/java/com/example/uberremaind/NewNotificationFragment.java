package com.example.uberremaind;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Date;

public class NewNotificationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView date = view.findViewById(R.id.text_view);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                if (activity == null) return;
                DateTimePickerDialog dialog = new DateTimePickerDialog();
                dialog.setDatePickerCallback(new DateTimePickerDialog.OnDatePickedCallback() {
                    @Override
                    public void onDatePicked(long timestamp) {
                        //"Mon, Aug 34, 1934 | 12:33"
                        Date pickedTime = new Date(timestamp);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd yyyy | hh:mm a");
                        date.setText(simpleDateFormat.format(pickedTime));
                    }
                });
                dialog.show(activity.getSupportFragmentManager(), DateTimePickerDialog.class.getName());
            }
        });

        EditText description = view.findViewById(R.id.description);
        EditText title = view.findViewById(R.id.title);
    }


}
