package sebastian.cl.worktracker.trabajos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TrabajoDatabaseHelper extends SQLiteOpenHelper {


    private static final String DB_NAME="trabajos.db";
    private static final int DB_VERSION=1;


    public TrabajoDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlText = "CREATE TABLE `trabajos`(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_ID INTEGER," +
                "titulo TEXT," +
                "descripcion TEXT," +
                "imagen TEXT);";

        sqLiteDatabase.execSQL(sqlText);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Aqui debemos obtener la lista ya inicializada, y la limpiamos al obtenerla, para refrescarla en el listview sin problemas.
    public List<Trabajos> getTrabajos(List<Trabajos> trabajos, int userId){
        SQLiteDatabase DB = getReadableDatabase();
        trabajos.clear();
        Cursor cursor = DB.query("trabajos",
                new String[]{"ID","titulo","descripcion","imagen"},
                "user_ID = "+userId,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        do{

            try{
                trabajos.add(new Trabajos(cursor.getInt(0), userId, cursor.getString(3),cursor.getString(1),cursor.getString(2)));
            }catch (Exception e){//
            }
        }while(cursor.moveToNext());
        cursor.close();
        DB.close();
        return trabajos;
    }

    public void addTrabajo(Trabajos trabajo){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("user_ID",trabajo.getUser_ID());
        valores.put("titulo",trabajo.getTitulo());
        valores.put("descripcion",trabajo.getDescripcion());
        valores.put("imagen", trabajo.getImage());

        db.insert("trabajos", null, valores);
        db.close();

    }

    public boolean cambiarTrabajo(Trabajos trab){
        String sqlText = "UPDATE trabajos SET titulo = ?, descripcion = ?, imagen = ? WHERE ID = ?";
        Object[] argumentos = new Object[]{trab.getTitulo(),trab.getDescripcion(),trab.getImage(), trab.getTrabajoID()};
        try{
            getWritableDatabase().execSQL(sqlText,argumentos);
            return true;
        }catch (Exception ex)
        {
            return false;
        }
    }

    public boolean eliminarTrabajo(int ID){
        String sqlText = "DELETE FROM trabajos WHERE ID = ?";
        Object[] argumento = new Object[]{ID};
        try{
            getWritableDatabase().execSQL(sqlText,argumento);
            return true;
        }catch (Exception ex){
            return false;
        }

    }







}
