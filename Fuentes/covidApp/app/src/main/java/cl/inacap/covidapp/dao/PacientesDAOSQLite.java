package cl.inacap.covidapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.covidapp.dto.Paciente;
import cl.inacap.covidapp.helpers.PacientesSQLiteHelper;

public class PacientesDAOSQLite implements PacientesDAO {
    private PacientesSQLiteHelper pacHelper;

    public PacientesDAOSQLite(Context context){
        this.pacHelper = new PacientesSQLiteHelper(context, "DBPacientes", null, 1);
    }

    @Override
    public List<Paciente> getAll() {
        SQLiteDatabase reader = this.pacHelper.getReadableDatabase();
        List<Paciente> pacientes = new ArrayList<>();
        try{
            if(reader != null){
                Cursor c = reader.rawQuery("SELECT id,rut,nombre,apellido,fechaExamen,areaTrabajo,sintomas,temperatura,tos,presion" +
                        " FROM pacientes", null);
                if(c.moveToFirst()){
                    do{
                        Paciente p = new Paciente();
                        p.setId(c.getInt(0));
                        p.setRut(c.getString(1));
                        p.setNombre(c.getString(2));
                        p.setApellido(c.getString(3));
                        p.setFechaExamen(c.getString(4));
                        p.setAreaTrabajo(c.getString(5));
                        p.setSintomas(c.getInt(6));
                        p.setTemperatura(c.getInt(7));
                        p.setTos(c.getInt(8));
                        p.setPresion(c.getInt(9));
                        pacientes.add(p);
                    }while(c.moveToNext());
                    }
                reader.close();
                }
            }catch (Exception ex){
            Log.e("PACIENTESDAO", ex.toString());
            pacientes = null;
        }

        return pacientes;
    }

    @Override
    public Paciente save(Paciente p) {
        SQLiteDatabase writer = this.pacHelper.getWritableDatabase();
        String sql = String.format("INSERT INTO pacientes(rut,nombre,apellido,fechaExamen,areaTrabajo,sintomas,temperatura,tos,presion)" +
                " VALUES ('%s','%s','%s','%s','%s','%d','%d','%d','%d')", p.getRut(), p.getNombre(),p.getApellido(),p.getFechaExamen(),
                        p.getAreaTrabajo(),p.getSintomas(),p.getTemperatura(),p.getTos(),p.getPresion());

        writer.execSQL(sql);
         writer.close();
        return p;
    }
}