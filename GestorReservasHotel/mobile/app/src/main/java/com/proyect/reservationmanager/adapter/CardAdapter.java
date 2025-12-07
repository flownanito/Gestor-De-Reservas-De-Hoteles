package com.proyect.reservationmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.model.PaymentCard;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<PaymentCard> cardList;
    private OnCardDeleteListener deleteListener;

    public interface OnCardDeleteListener {
        void onDeleteCard(int position);
    }

    public CardAdapter(List<PaymentCard> list, OnCardDeleteListener listener) {
        this.cardList = list;
        this.deleteListener = listener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        PaymentCard card = cardList.get(position);
        holder.text1.setText("**** **** **** " + card.number.substring(card.number.length() - 4));
        holder.text2.setText(card.holder + " - " + card.expiration);

        holder.btnDelete.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteCard(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;
        ImageButton btnDelete;

        public CardViewHolder(View item) {
            super(item);
            text1 = item.findViewById(R.id.text1);
            text2 = item.findViewById(R.id.text2);
            btnDelete = item.findViewById(R.id.btnDeleteCard);
        }
    }
}
