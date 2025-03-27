package com.example.meal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> implements Filterable {

    public interface OnMealClickListener {
        void onMealClick(MealModel meal);
    }

    private final List<MealModel> fullList;
    private final List<MealModel> filteredList;
    private final OnMealClickListener listener;

    public MealAdapter(List<MealModel> mealList, OnMealClickListener listener) {
        this.fullList = mealList;
        this.filteredList = new ArrayList<>(mealList);
        this.listener = listener;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_card, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        MealModel meal = filteredList.get(position);
        holder.title.setText(meal.getName());
        holder.image.setImageResource(meal.getImageResId());
        holder.itemView.setOnClickListener(v -> listener.onMealClick(meal));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    /**
     * Optional manual filter method if you want to trigger filtering yourself.
     * You can call mealAdapter.filter("text") from outside code.
     */
    public void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(fullList);
        } else {
            for (MealModel item : fullList) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    /**
     * Required for implementing Filterable.
     * This allows usage of the standard .filter(...) calls (e.g., from a SearchView).
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<MealModel> tempList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    // If no filter text, show all items
                    tempList.addAll(fullList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (MealModel item : fullList) {
                        if (item.getName().toLowerCase().contains(filterPattern)) {
                            tempList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = tempList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList.clear();
                //noinspection unchecked
                filteredList.addAll((List<MealModel>) results.values);
                notifyDataSetChanged();
            }
        };
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;

        MealViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.mealTitle);
            image = itemView.findViewById(R.id.mealImage);
        }
    }
}
