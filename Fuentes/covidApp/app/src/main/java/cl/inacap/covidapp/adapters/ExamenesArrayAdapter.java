package cl.inacap.covidapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import cl.inacap.covidapp.R;
import cl.inacap.covidapp.dto.Paciente;

public class ExamenesArrayAdapter extends ArrayAdapter<Paciente> {
    private Activity activity;
    private List<Paciente> pacientes;

    public ExamenesArrayAdapter(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.pacientes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View fila = inflater.inflate(R.layout.examenes_list, null, true);

        TextView rutTv = fila.findViewById(R.id.rut_lv);
        TextView nombreTv = fila.findViewById(R.id.nombre_lv);
        TextView apellidoTv = fila.findViewById(R.id.apellido_lv);
        TextView fechaTv = fila.findViewById(R.id.fecha_lv);
        ImageView iconoTv = fila.findViewById(R.id.img_positivo_lv);





        Paciente actual = pacientes.get(position);

        rutTv.setText(actual.getRut());
        nombreTv.setText(actual.getNombre());
        apellidoTv.setText(actual.getApellido());
        fechaTv.setText(actual.getFechaExamen());
        if(pacientes.get(position).isSintomas()){
            iconoTv.setImageResource(R.drawable.infected);
        }

        return fila;
    }
}
