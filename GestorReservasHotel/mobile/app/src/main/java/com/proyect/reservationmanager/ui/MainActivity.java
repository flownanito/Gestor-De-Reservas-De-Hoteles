package com.proyect.reservationmanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proyect.reservationmanager.R;
import com.proyect.reservationmanager.api.ClientApiService;
import com.proyect.reservationmanager.api.RetrofitClient;
import com.proyect.reservationmanager.model.Client;

public class MainActivity extends AppCompatActivity {
  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.recyclerViewClients);
    FloatingActionButton fab = findViewById(R.id.fabAddClient);

    // Boton de añadir
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, addClientActivity.class);
        startActivity(intent);
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    cargarClientes();
  }

  private void cargarClientes() {
    // Obtener la instancia de la api
    ClientApiService apiService = RetrofitClient.getInstance().getClientApi();

    // Crear la llamada para pedir la lista
    Call<List<Client>> call = apiService.getAllClients();

    // Ejecutamos asincronamente (enqueue)
    call.enqueue(new Callback<List<Client>>() {
      @Override
      public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
        if (response.isSuccessful()) {
          List<Client> clients = response.body();
          // Crea el adaptador con la lista completa
          ClientAdapter clientAdapter = new ClientAdapter(clients, new ClientAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Client client) {
              showDeleteDialog(client);
            }
          });

          // Asignarlo al RecyclerView
          recyclerView.setAdapter(clientAdapter);
        } else {
          Log.e("API_TEST", "Error en la respuesta: " + response.code());
        }
      }

      @Override
      public void onFailure(Call<List<Client>> call, Throwable t) {
        Log.e("API_TEST", "Fallo en la conexión: " + t.getMessage());
      }
    });
  }

  // Muestra la ventana de confirmación
  private void showDeleteDialog(Client client) {
    new android.app.AlertDialog.Builder(this)
            .setTitle("Borrar cliente")
            .setMessage("¿Seguro que quieres eliminar a " + client.getFirstName() + "?")
            .setPositiveButton("Eliminar", (dialog, which) -> {
              // Si dice que si, llamamos a la api
              deleteClientFromApi(client.getId());
            })
            .setNegativeButton("Cancelar", null) // Si dice que no, no hacemos nada
            .show();
  }

  // Logica para llamar al servidor y borrar
  private void deleteClientFromApi(Long id) {
    ClientApiService apiService = RetrofitClient.getInstance().getClientApi();
    Call<Void> call = apiService.deleteClient(id);

    call.enqueue(new Callback<Void>() {
      @Override
      public void onResponse(Call<Void> call, Response<Void> response) {
        if (response.isSuccessful()) {
          Toast.makeText(MainActivity.this, "Cliente eliminado", Toast.LENGTH_SHORT).show();
          // Recargamos la lista para que desaparezca
          cargarClientes();
        } else {
          Toast.makeText(MainActivity.this, "Error al borrar", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<Void> call, Throwable t) {
        Toast.makeText(MainActivity.this, "Fallo de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}