package sebastian.cl.worktracker.usuarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UsuarioDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="usuarios.db";
    private static final int DB_VERSION=1;


    public UsuarioDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlText = "CREATE TABLE `usuarios`(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "username TEXT," +
                "password TEXT," +
                "correo TEXT," +
                "habilitado INTEGER);";

        sqLiteDatabase.execSQL(sqlText);
        }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //Aqui las comprobaciones ya se deberian haber realizado, se envia el usuario y se agrega  a la base de datos.
    public void registrarUsuario(Usuario usuario){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre",usuario.getNombre());
        valores.put("username",usuario.getUsername());
        valores.put("password",usuario.getPassword());
        valores.put("correo", usuario.getCorreo());
        valores.put("habilitado", 1);

        db.insert("usuarios", null, valores);
        db.close();

    }


    //chequea si el usuario que se desea registrar ya existe en la base de datos. El usuario y correo no se pueden repetir.
    //El tipo puede ser "usuario' o 'correo', si se define cualquier otro valor, se considerara como tipo usuario.
    public boolean userExist(String tipo, String dato){
        String sqlText;
        SQLiteDatabase db = getReadableDatabase();
        switch (tipo){
            case "usuario":
                sqlText = "SELECT ID FROM usuarios WHERE username = ?";
                break;
            case "correo":
                sqlText = "SELECT ID FROM usuarios WHERE correo = ?";
                break;
            default:
                sqlText = "SELECT ID FROM usuarios WHERE username = ?";
                break;

        }
        String[] argumentos = new String[]{dato};
        try{
            Cursor cursor = db.rawQuery(sqlText, argumentos);
            cursor.moveToFirst();
            if(cursor.getInt(0) >= 1){
                return true;
            }
        }
        catch //me if you can
        (Exception ex){
            return false;
        }
        return false;
    }

    public Usuario iniciaSesion(String usuario, String password){
        Usuario user;
        SQLiteDatabase db = getReadableDatabase();
        String sqlText =  "SELECT  `ID`, `correo`" +
                "FROM usuarios WHERE username = ? AND password = ? AND habilitado = '1'";
        String[] argumentos = new String[]{usuario, password};
        try{
            Cursor cursor = db.rawQuery(sqlText, argumentos);
            cursor.moveToFirst();


            user = new Usuario(cursor.getInt(0),password, usuario, cursor.getString(1), true);
            return user;
        }catch(Exception ex){
            return null;//Usuario no coincide, regresa null
        }
    }




}
