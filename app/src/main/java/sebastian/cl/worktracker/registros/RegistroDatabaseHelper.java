package sebastian.cl.worktracker.registros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegistroDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="registros.db";
    private static final int DB_VERSION=1;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            .withZone(ZoneId.systemDefault());


    public RegistroDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlText = "CREATE TABLE `registros`(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "trabajo_ID INTEGER," +
                "f_creacion TEXT," +
                "f_actualizacion TEXT," +
                "f_trabajada TEXT," +
                "horas_trabajadas INTEGER);";

        sqLiteDatabase.execSQL(sqlText);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<Registro> getRegistros(List<Registro> registros, int trabajoID){
        SQLiteDatabase DB = getReadableDatabase();
        registros.clear();
        Cursor cursor = DB.query("registros",
                new String[]{"ID","f_creacion","f_actualizacion","f_trabajada", "horas_trabajadas"},
                "trabajo_ID = "+trabajoID,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();


        do{
            try{
                registros.add(new Registro(cursor.getInt(0), trabajoID, cursor.getString(2),cursor.getString(3),cursor.getInt(4)));
            }catch (Exception e){

            }
        }while(cursor.moveToNext());
        cursor.close();
        DB.close();
        return registros;

    }



    public void addRegistro(Registro registro){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("trabajo_ID",registro.getTrabajoID());
        valores.put("f_creacion",registro.getF_creacion());
        valores.put("f_actualizacion",registro.getF_actualizacion());
        valores.put("f_trabajada", registro.getF_trabajo());
        valores.put("horas_trabajadas", registro.getHoras_trabajadas());

        db.insert("registros", null, valores);
        db.close();
    }


    public boolean cambiarRegistro(Registro reg){
        String sqlText = "UPDATE registros SET f_actualizacion = ?, f_trabajada = ?, horas_trabajadas = ? WHERE ID = ?";
        Object[] argumentos = new Object[]{formatter.format(Instant.now()), reg.getF_trabajo(), reg.getHoras_trabajadas(),reg.getID()};
        try{
            getWritableDatabase().execSQL(sqlText,argumentos);
            return true;
        }catch (Exception ex)
        {
            return false;
        }
    }





}
