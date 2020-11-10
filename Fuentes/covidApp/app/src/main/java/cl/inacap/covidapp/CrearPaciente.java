package cl.inacap.covidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    private Switch sintomasTxt;
    private EditText tempTxt;
    private Switch tosTxt;
    private EditText presionTxt;
    private Button agregarBtn;
    private Spinner areaSpin;
    private PacientesDAO pacDAO = new PacientesDAOSQLite(this);
    Calendar c;
    DatePickerDialog dpd;
    int dia;
    int mes;
    int anio;
    int estadoSint = 0;
    int estadoTos = 0;
    String[] area = {"Atención a público", "Otro"};


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_paciente);
        this.toolbar = findViewById(R.id.toolbar);
        this.rutTxt = findViewById(R.id.rut_pac_txt_cr);
        this.nombreTxt = findViewById(R.id.nombre_pac_txt_cr);
        this.apellidoTxt = findViewById(R.id.apellido_pac_txt_cr);
        this.sintomasTxt = findViewById(R.id.sint_sw_cr);
        this.areaSpin = findViewById(R.id.area_spinn);
        this.tempTxt = findViewById(R.id.temp_pac_cr);
        this.tosTxt = findViewById(R.id.tos_sw_cr);
        this.presionTxt = findViewById(R.id.presion_pac_txt_cr);
        this.agregarBtn = findViewById(R.id.agregar_paciente_cr);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, area);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpin.setAdapter(adapter2);


        mTv = (TextView) findViewById(R.id.fecha_ex_cr);
        mBtn = (Button) findViewById(R.id.fechaBtn_cr);

        mBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                c = Calendar.getInstance();


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
                List<String> errores = new ArrayList<>();
                Paciente p = new Paciente();
                if(rutTxt.getText().toString().matches("^[0-9]+-[0-9kK]{1}$")) {
                    p.setRut(rutTxt.getText().toString());
                }else{
                    errores.add("Rut inválido");
                }

                if(nombreTxt.getText().toString().isEmpty()){
                    errores.add("Debe ingresar nombre");
                }else{
                    p.setNombre(nombreTxt.getText().toString());
                }

                if(apellidoTxt.getText().toString().isEmpty()){
                    errores.add("Debe ingresar apellido");
                }else{
                    p.setApellido(apellidoTxt.getText().toString());
                }
                if(mTv.getText().toString().isEmpty()){
                    errores.add("Debe seleccionar Fecha");
                }else{
                    p.setFechaExamen(mTv.getText().toString());
                }
                if(areaSpin.getSelectedItem().toString().isEmpty()){
                    errores.add("Debe seleccionar área de trabajo");
                }else{
                    p.setAreaTrabajo(areaSpin.getSelectedItem().toString().trim());
                }


                p.setPresion(Integer.parseInt(presionTxt.getText().toString()));


                p.setTemperatura(Integer.parseInt(tempTxt.getText().toString()));


                if(sintomasTxt.isChecked()){
                    p.setSintomas(true);
                }else{
                    p.setSintomas(false);
                }

                if(tosTxt.isChecked()){
                    p.setTos(true);
                }else{
                    p.setTos(false);
                }

                if(errores.isEmpty()){
                    pacDAO.save(p);
                    startActivity(new Intent(CrearPaciente.this, PrincipalActivity.class));
                    Toast.makeText(CrearPaciente.this, "Paciente Agregado!", Toast.LENGTH_SHORT).show();
                }else{
                    String tempString ="";
                    for(int i = 0 ; i < errores.size(); i++ ){
                        tempString = tempString + ". " + errores.get(i);
                    }
                    tempString = tempString.substring(2);
                    Toast.makeText(getApplicationContext(), tempString, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}