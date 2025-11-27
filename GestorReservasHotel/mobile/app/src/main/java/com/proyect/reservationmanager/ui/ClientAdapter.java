package com.proyect.reservationmanager.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.models.Client;
import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {

  private List<Client> clients;
  private OnItemLongClickListener listener;

  public interface OnItemLongClickListener {
    void onItemLongClick(Client client);
  }

  // Constructor: Recibimos la lista de datos
  public ClientAdapter(List<Client> clients, OnItemLongClickListener listener) {
    this.clients = clients;
    this.listener = listener;
  }

  // 1. Crear el molde visual (Inflar el XML)
  @NonNull
  @Override
  public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_client, parent, false);
    return new ClientViewHolder(view);
  }

  // 2. Rellenar los datos (Binding)
  @Override
  public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
    Client client = clients.get(position);

    // Asignamos los textos usando los datos del cliente
    holder.tvName.setText(client.getFirstName() + " " + client.getLastName());
    holder.tvEmail.setText(client.getEmail());
    holder.tvPhone.setText(client.getPhone());

    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        // Avisamos al MainActivity a través de la interfaz
        listener.onItemLongClick(client);
        return true;
      }
    });
  }

  // 3. Cantidad de elementos
  @Override
  public int getItemCount() {
    return (clients != null) ? clients.size() : 0;
  }

  // --- ViewHolder: Mantiene las referencias a los controles de la vista ---
  public static class ClientViewHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvEmail, tvPhone;

    public ClientViewHolder(@NonNull View itemView) {
      super(itemView);
      tvName = itemView.findViewById(R.id.tvClientName);
      tvEmail = itemView.findViewById(R.id.tvClientEmail);
      tvPhone = itemView.findViewById(R.id.tvClientPhone);
    }
  }
}