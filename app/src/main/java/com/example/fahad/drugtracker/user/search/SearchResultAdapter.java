package com.example.fahad.drugtracker.user.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fahad.drugtracker.R;
import com.example.fahad.drugtracker.common.data.remote.model.DrugsResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private List<DrugsResponse.ConceptProperty> items = new ArrayList<>();

    public void setItems(List<DrugsResponse.ConceptProperty> list) {
        this.items = list != null ? list : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drug, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DrugsResponse.ConceptProperty property = items.get(position);
        holder.title.setText(property.name);
        holder.rxcui.setText(property.rxcui);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView rxcui;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.drugTitle);
            rxcui = itemView.findViewById(R.id.drugRxcui);
        }
    }
}
