package cl.inacap.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cl.inacap.covidapp.adapters.ExamenesArrayAdapter;
import cl.inacap.covidapp.dao.PacientesDAO;
import cl.inacap.covidapp.dao.PacientesDAOSQLite;
import cl.inacap.covidapp.dto.Paciente;

public class PrincipalActivity extends AppCompatActivity {
    private ListView examenesLv;
    private FloatingActionButton agregarBtn;
    private List<Paciente> pacientes;
    private ExamenesArrayAdapter adaptador;
    private PacientesDAO pacientesDAO = new PacientesDAOSQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.agregarBtn = findViewById(R.id.agregar_btn_fb);
        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrincipalActivity.this, CrearPaciente.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        pacientes = pacientesDAO.getAll();
        adaptador = new ExamenesArrayAdapter(this, R.layout.examenes_list, pacientes);
        examenesLv = findViewById(R.id.examenes_lv);
        examenesLv.setAdapter(adaptador);
        examenesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PrincipalActivity.this, VerPacienteActivity.class);
                Paciente pacActual = pacientes.get(position);
                intent.putExtra("paciente", pacActual);

                startActivity(intent);
            }
        });

    }
}