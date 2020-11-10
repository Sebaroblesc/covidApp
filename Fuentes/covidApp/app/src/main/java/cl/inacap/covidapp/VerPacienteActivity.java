package cl.inacap.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import cl.inacap.covidapp.dto.Paciente;

public class VerPacienteActivity extends AppCompatActivity {

    private TextView rutTxt;
    private Toolbar toolbar;
    private TextView nombreTxt;
    private TextView apellidoTxt;
    private TextView areaTxt;
    private TextView fechaTxt;
    private TextView sintomasTxt;
    private TextView tosTxt;
    private TextView tempTxt;
    private TextView presionTxt;


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        this.rutTxt = findViewById(R.id.rut_view);
        this.nombreTxt = findViewById(R.id.nombre_view);
        this.apellidoTxt = findViewById(R.id.apellido_view);
        this.areaTxt = findViewById(R.id.area_view);
        this.fechaTxt = findViewById(R.id.fecha_view);
        this.sintomasTxt = findViewById(R.id.sintomas_view);
        this.tosTxt = findViewById(R.id.tos_view);
        this.tempTxt = findViewById(R.id.temp_view);
        this.presionTxt = findViewById(R.id.presion_view);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        if(getIntent()!= null){
            Paciente paciente = (Paciente) getIntent().getSerializableExtra("paciente");
            this.rutTxt.setText(paciente.getRut());
            this.nombreTxt.setText(paciente.getNombre());
            this.apellidoTxt.setText(paciente.getApellido());
            this.areaTxt.setText(paciente.getAreaTrabajo());
            this.fechaTxt.setText(paciente.getFechaExamen());
            this.sintomasTxt.setText("Sintomas: " + paciente.isSintomas());
            this.tosTxt.setText("Tos: " + paciente.isTos());
            this.tempTxt.setText(paciente.getTemperatura()+ "°C");
            this.presionTxt.setText("Presión" + paciente.getPresion());
        }
    }
}