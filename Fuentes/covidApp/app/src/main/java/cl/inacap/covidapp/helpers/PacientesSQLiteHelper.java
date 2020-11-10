package cl.inacap.covidapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesSQLiteHelper extends SQLiteOpenHelper {

    private final String sqlCreate = "CREATE TABLE " +
            "pacientes(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +
            ",rut INTEGER" +
            ",nombre TEXT" +
            ",apellido TEXT" +
            ",fechaExamen TEXT" +
            ",areaTrabajo TEXT" +
            ",sintomas INTEGER DEFAULT 0" +
            ",temperatura INTEGER" +
            ",tos INTEGER DEFAULT 0" +
            ",presion INTEGER)";




    public PacientesSQLiteHelper(@Nullable Context context, @Nullable String name,
                                 @Nullable SQLiteDatabase.CursorFactory factory,
                                 int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS pacientes");
        db.execSQL(sqlCreate);
    }
}
