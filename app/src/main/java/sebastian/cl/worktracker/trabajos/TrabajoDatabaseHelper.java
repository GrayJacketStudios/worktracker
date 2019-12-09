package sebastian.cl.worktracker.trabajos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
