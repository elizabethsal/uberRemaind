package com.example.uberremaind;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) return;

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MainFragmentAdapter());
    }

    private class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.ViewHolder> {

        private class ViewHolder extends RecyclerView.ViewHolder {

            private ImageView negro;

            private ViewHolder(@NonNull View itemView) {
                super(itemView);
                negro = itemView.findViewById(R.id.picture);
            }
        }

        @NonNull
        @Override
        public MainFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainFragmentAdapter.ViewHolder holder, int position) {
            MainActivity activity = (MainActivity) getActivity();
            if (activity == null) return;

            if (position % 10 == 0) {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
            }
        }

        @Override
        public int getItemCount() {
            return 15;
        }
    }
}
