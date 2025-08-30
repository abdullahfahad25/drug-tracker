package com.example.fahad.drugtracker.user.userDrugs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fahad.drugtracker.R;
import com.example.fahad.drugtracker.common.data.local.model.DrugEntity;
import com.example.fahad.drugtracker.common.data.remote.model.DrugsResponse;
import com.example.fahad.drugtracker.user.search.SearchResultAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserDrugsAdapter extends RecyclerView.Adapter<UserDrugsAdapter.ViewHolder> {

    private List<DrugEntity> items = new ArrayList<>();

    public void setItems(List<DrugEntity> list) {
        this.items = list != null ? list : new ArrayList<>();
        notifyDataSetChanged();
    }
    public DrugEntity getItemAt(int pos) {
        return items.get(pos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drug, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DrugEntity entity = items.get(position);
        holder.title.setText(entity.getName());
        holder.rxcui.setText(entity.getRxcui());
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
