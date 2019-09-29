package com.example.uberremaind;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateTimePickerDialog extends DialogFragment {

    interface OnDatePickedCallback {
        void onDatePicked(long timestamp);
    }

    private OnDatePickedCallback callback;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) return super.onCreateDialog(savedInstanceState);
        final View view = LayoutInflater.from(activity).inflate(R.layout.dialog_new_nofication, null, false);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText(activity.getString(R.string.date_picker_title)));
        tabLayout.addTab(tabLayout.newTab().setText(activity.getString(R.string.time_picker_title)));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(ContextCompat.getColor(activity, R.color.primary_text),
                ContextCompat.getColor(activity, R.color.colorAccent));

        ArrayList<View> views = new ArrayList<>();
        final DatePicker datePicker = new DatePicker(activity);
        final TimePicker timePicker = new TimePicker(activity);
        datePicker.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        views.add(datePicker);
        views.add(timePicker);

        viewPager.setAdapter(new CustomPagerAdapter(views));
        return new AlertDialog.Builder(activity)
                .setView(view)
                .setTitle(activity.getString(R.string.new_notification_title))
                .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (callback != null) {
                            GregorianCalendar calendar = new GregorianCalendar(Locale.getDefault());
                            calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                                    timePicker.getHour(), timePicker.getMinute());

                            callback.onDatePicked(calendar.getTimeInMillis());
                        }
                        dismissAllowingStateLoss();
                    }
                }).setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismissAllowingStateLoss();
                    }
                }).create();
    }

    public void setDatePickerCallback(OnDatePickedCallback callback) {
        this.callback = callback;
    }

    private class CustomPagerAdapter extends PagerAdapter {

        private ArrayList<View> views;

        CustomPagerAdapter(@NonNull ArrayList<View> views) {
            this.views = views;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }
    }

}
