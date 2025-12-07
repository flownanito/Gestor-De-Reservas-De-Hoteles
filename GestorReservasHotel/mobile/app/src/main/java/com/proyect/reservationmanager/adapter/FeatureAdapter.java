package com.proyect.reservationmanager.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.model.Feature;

import java.util.ArrayList;
import java.util.List;
public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder> {
    private List<Feature> featureList;
    private List<Feature> featureListFull; // Para búsqueda
    private OnFeatureEditListener editListener;
    private OnFeatureDeleteListener deleteListener;

    public interface OnFeatureEditListener {
        void onEditFeature(int position);
    }

    public interface OnFeatureDeleteListener {
        void onDeleteFeature(int position);
    }

    public FeatureAdapter(List<Feature> list, OnFeatureEditListener editListener, OnFeatureDeleteListener deleteListener) {
        this.featureList = list;
        this.featureListFull = new ArrayList<>(list);
        this.editListener = editListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feature, parent, false);
        return new FeatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {
        Feature feature = featureList.get(position);

        holder.tvIcon.setText(feature.getIcon() != null ? feature.getIcon() : "🔹");
        holder.tvName.setText(feature.getName());

        holder.btnEdit.setOnClickListener(v -> {
            if (editListener != null) {
                editListener.onEditFeature(holder.getAdapterPosition());
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteFeature(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return featureList.size();
    }

    public void filter(String text) {
        featureList.clear();
        if (text.isEmpty()) {
            featureList.addAll(featureListFull);
        } else {
            text = text.toLowerCase();
            for (Feature feature : featureListFull) {
                if (feature.getName().toLowerCase().contains(text)) {
                    featureList.add(feature);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class FeatureViewHolder extends RecyclerView.ViewHolder {
        TextView tvIcon, tvName;
        ImageButton btnEdit, btnDelete;

        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIcon = itemView.findViewById(R.id.tvFeatureIcon);
            tvName = itemView.findViewById(R.id.tvFeatureName);
            btnEdit = itemView.findViewById(R.id.btnEditFeature);
            btnDelete = itemView.findViewById(R.id.btnDeleteFeature);
        }
    }
}
