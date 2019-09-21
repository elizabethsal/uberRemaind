package com.example.uberremaind;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        MainFragmentAdapter adapter = new MainFragmentAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setItems(getTestItems());
    }

    private ArrayList<Item> getTestItems() {
        ArrayList<Item> list = new ArrayList<>();
        for (int item = 0; item < 50; item++) {
            Item addedItem = new Item();
            addedItem.setTitle("slkdvnlakfnvlaildsnfkbuskd");
            addedItem.setDescription("sjdhvlaisdhgvlaks,dbv,kashdvk,asudb  kaus,hdvajsdn uka,ds");
            list.add(addedItem);
        }
        return list;
    }

    private class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.ViewHolder> {

        private ArrayList<Item> items = new ArrayList<>();

        private class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title, description;

            private ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
                description = itemView.findViewById(R.id.description);
            }
        }

        void setItems(@Nullable List<Item> items) {
            this.items.clear();

            if (items != null)
                this.items.addAll(items);

            notifyDataSetChanged();
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

            holder.title.setText(items.get(position).getTitle());
            holder.description.setText(items.get(position).getDescription());

            //All *if* expressions must be excessive
           /*if (position % 2 == 0)
                holder.itemView.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));
            else
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);*/
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    class Item {
        private String title, description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
