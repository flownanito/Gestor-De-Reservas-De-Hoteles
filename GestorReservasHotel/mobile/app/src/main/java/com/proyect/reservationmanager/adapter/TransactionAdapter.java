package com.proyect.reservationmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.proyect.reservationmanager.model.Payment;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TxViewHolder> {

    private List<Payment> txList;

    public TransactionAdapter(List<Payment> list) {
        this.txList = list;
    }

    @Override
    public TxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new TxViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TxViewHolder holder, int position) {
        Payment p = txList.get(position);
        holder.text1.setText("Monto: $" + p.getAmount());
        holder.text2.setText("Estado: " + p.getStatus() + " - " + p.getPaymentDate());
    }

    @Override
    public int getItemCount() {
        return txList.size();
    }

    static class TxViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;

        public TxViewHolder(View item) {
            super(item);
            text1 = item.findViewById(android.R.id.text1);
            text2 = item.findViewById(android.R.id.text2);
        }
    }
}
