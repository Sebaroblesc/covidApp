package cl.inacap.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

import cl.inacap.covidapp.dao.PacientesDAO;
import cl.inacap.covidapp.dao.PacientesDAOSQLite;
import cl.inacap.covidapp.dto.Paciente;

public class CrearPaciente extends AppCompatActivity {

    TextView mTv;
    Button mBtn;
    private Toolbar toolbar;
    private EditText rutTxt;
    private EditText nombreTxt;
    private EditText apellidoTxt;
    private EditText fechaTxt;
    private EditText sintomasTxt;
    private EditText tempTxt;
    private EditText tosTxt;
    private EditText presionTxt;
    private Button agregarBtn;
    private PacientesDAO pacDAO = new PacientesDAOSQLite(this);
    Calendar c;
    DatePickerDialog dpd;
    int dia;
    int mes;
    int anio;
    int estadoSint = 0;
    int estadoTos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_paciente);
        this.toolbar = findViewById(R.id.toolbar);
        this.rutTxt = findViewById(R.id.rut_pac_txt_cr);
        this.nombreTxt = findViewById(R.id.nombre_pac_txt_cr);
        this.apellidoTxt = findViewById(R.id.apellido_pac_txt_cr);
        this.sintomasTxt = findViewById(R.id.sint_sw_cr);
        this.tempTxt = findViewById(R.id.temp_pac_cr);
        this.tosTxt = findViewById(R.id.tos_sw_cr);
        this.presionTxt = findViewById(R.id.presion_pac_txt_cr);
        this.agregarBtn = findViewById(R.id.agregar_paciente_cr);


        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        mTv = (TextView) findViewById(R.id.fecha_ex_cr);
        mBtn = (Button) findViewById(R.id.fechaBtn_cr);



        mBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));


                int dia = c.get(Calendar.DAY_OF_MONTH);
                int mes = c.get(Calendar.MONTH);
                int anio = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(CrearPaciente.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mAnio, int mMes, int mDia) {
                        mTv.setText(mDia + "/" + (mMes + 1) + "/" + mAnio);
                    }
                }, dia , mes, anio);
                dpd.show();
            }
        });

        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paciente p = new Paciente();
                p.setRut(rutTxt.getText().toString());
                p.setNombre(nombreTxt.getText().toString());
                p.setApellido(apellidoTxt.getText().toString());
                p.setFechaExamen(mTv.getText().toString());
                p.setSintomas(Integer.parseInt(sintomasTxt.getText().toString()));
                p.setTemperatura(Integer.parseInt(tempTxt.getText().toString()));
                p.setTos(Integer.parseInt(tosTxt.getText().toString()));
                p.setPresion(Integer.parseInt(presionTxt.getText().toString()));
                pacDAO.save(p);
                startActivity(new Intent(CrearPaciente.this, PrincipalActivity.class));
                Toast.makeText(CrearPaciente.this, "Paciente Agregado!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}