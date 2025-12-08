package com.proyect.reservationmanager;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proyect.reservationmanager.adapter.FeatureAdapter;
import com.proyect.reservationmanager.model.Feature;

import java.util.ArrayList;
import java.util.List;


public class RoomFeaureActivity extends AppCompatActivity {

    private RecyclerView recyclerFeatures;
    private FeatureAdapter featureAdapter;
    private List<Feature> features = new ArrayList<>();
    private LinearLayout searchContainer;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_feaure);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Gestionar Características");
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Inicializar vistas
        searchContainer = findViewById(R.id.searchContainer);
        etSearch = findViewById(R.id.etSearch);
        recyclerFeatures = findViewById(R.id.recyclerFeatures);
        FloatingActionButton fabAdd = findViewById(R.id.fabAddFeature);

        // Configurar RecyclerView
        recyclerFeatures.setLayoutManager(new LinearLayoutManager(this));
        featureAdapter = new FeatureAdapter(features,
                position -> showEditDialog(position),
                position -> showDeleteConfirmation(position));
        recyclerFeatures.setAdapter(featureAdapter);

        // Configurar FAB
        fabAdd.setOnClickListener(v -> showAddDialog());

        // Cargar datos de ejemplo
        loadSampleData();

        // Configurar búsqueda
        setupSearch();
    }

    private void setupSearch() {
        etSearch.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                featureAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });
    }

    private void loadSampleData() {
        features.add(new Feature(1L, "Aire Acondicionado", "❄️"));
        features.add(new Feature(2L, "Balcón con Vistas al Mar", "🏖️"));
        features.add(new Feature(3L, "TV Pantalla Plana", "📺"));
        features.add(new Feature(4L, "WiFi de Alta Velocidad", "📶"));
        featureAdapter.notifyDataSetChanged();
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Añadir Característica");

        // Crear layout para el diálogo
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);

        final EditText inputName = new EditText(this);
        inputName.setHint("Nombre de la característica");
        layout.addView(inputName);

        final EditText inputIcon = new EditText(this);
        inputIcon.setHint("Icono (emoji o texto)");
        layout.addView(inputIcon);

        builder.setView(layout);

        builder.setPositiveButton("Añadir", (dialog, which) -> {
            String name = inputName.getText().toString().trim();
            String icon = inputIcon.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show();
                return;
            }

            Long newId = features.isEmpty() ? 1L : features.get(features.size() - 1).getId() + 1;
            features.add(new Feature(newId, name, icon.isEmpty() ? "🔹" : icon));
            featureAdapter.notifyItemInserted(features.size() - 1);
            Toast.makeText(this, "Característica añadida", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void showEditDialog(int position) {
        Feature feature = features.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Característica");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);

        final EditText inputName = new EditText(this);
        inputName.setHint("Nombre de la característica");
        inputName.setText(feature.getName());
        layout.addView(inputName);

        final EditText inputIcon = new EditText(this);
        inputIcon.setHint("Icono (emoji o texto)");
        inputIcon.setText(feature.getIcon());
        layout.addView(inputIcon);

        builder.setView(layout);

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String name = inputName.getText().toString().trim();
            String icon = inputIcon.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "El nombre es obligatorio", Toast.LENGTH_SHORT).show();
                return;
            }

            feature.setName(name);
            feature.setIcon(icon.isEmpty() ? "🔹" : icon);
            featureAdapter.notifyItemChanged(position);
            Toast.makeText(this, "Característica actualizada", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void showDeleteConfirmation(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar característica")
                .setMessage("¿Estás seguro de que deseas eliminar esta característica?")
                .setPositiveButton("Eliminar", (dialog, which) -> deleteFeature(position))
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void deleteFeature(int position) {
        features.remove(position);
        featureAdapter.notifyItemRemoved(position);
        Toast.makeText(this, "Característica eliminada", Toast.LENGTH_SHORT).show();
    }
}