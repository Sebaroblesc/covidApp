package cl.inacap.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nombreTxt;
    private EditText passwordTxt;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nombreTxt = findViewById(R.id.nombreUsuario);
        this.passwordTxt = findViewById(R.id.password);
        this.login = findViewById(R.id.iniciarBtn);
        nombreTxt.setBackgroundResource(R.drawable.textview_border);



        this.login.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                List<String> errores = new ArrayList<>();

                if(nombreTxt.getText().toString().isEmpty()) {
                    nombreTxt.setBackgroundResource(R.drawable.textview_border_error);
                    errores.add("Debe ingresar nombre de usuario");
                }else if(!nombreTxt.getText().toString().matches("^[0-9]{7,8}+-[0-9kK]{1}$")) {
                    nombreTxt.setBackgroundResource(R.drawable.textview_border);
                    errores.add("Rut inválido");
                }

                String input = nombreTxt.getText().toString();
                String ultimosSeis = "";


                if (input.length()>4){
                    ultimosSeis = input.substring(input.length() - 6);
                }
                String ultimosCuatro = "";
                if(ultimosSeis.length() == 6){
                    ultimosCuatro = ultimosSeis.substring(0 , 4);
                }


                if(passwordTxt.getText().toString().equals(ultimosCuatro)){

                }else{
                    errores.add("Contraseña inválida");
                }

                if(errores.isEmpty()) {
                    startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
                }else{
                String tempString ="";
                for(int i = 0 ; i < errores.size(); i++ ){
                    tempString = tempString + " \n" + errores.get(i);
                }
                tempString = tempString.substring(2);
                Toast.makeText(getApplicationContext(), tempString, Toast.LENGTH_LONG).show();
            }
             }



        });

    }
}
