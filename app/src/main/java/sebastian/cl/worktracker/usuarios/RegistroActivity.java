package sebastian.cl.worktracker.usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import sebastian.cl.worktracker.R;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

    }

    public void onRegistro(View view){

        UsuarioDatabaseHelper usDB = new UsuarioDatabaseHelper(this);



        EditText userText = (EditText) findViewById(R.id.ETRUsuario);
        EditText correoText = (EditText) findViewById(R.id.ETRCorreo);
        EditText pass1 = (EditText) findViewById(R.id.ETRPass1);
        EditText pass2 = (EditText) findViewById(R.id.ETRPass2);

        TextView error = (TextView) findViewById(R.id.TVError);

        if(!pass1.getText().toString().equals(pass2.getText().toString())){
            error.setText(R.string.error_pass_match);
            return;
        }

        if(pass1.getText().toString().length() < 4){
            error.setText(R.string.error_pass_lenght);
            return;
        }

        if(userText.getText().toString().equals("") || correoText.getText().toString().equals("") || pass1.getText().toString().equals("") || pass2.getText().toString().equals("")){
            error.setText(R.string.error_campos_vacios);
            return;
        }
        if(usDB.userExist("correo",correoText.getText().toString())){
            error.setText(getString(R.string.error_email_exists,correoText.getText().toString()));
            return;
        }
        if(usDB.userExist("usuario",userText.getText().toString())){
            error.setText(getString(R.string.error_username_exists,userText.getText().toString()));
            return;
        }



        //Ya pasamos todas las verificaciones, es seguro crear nuestro usuario!
        Usuario us = new Usuario(pass1.getText().toString(),userText.getText().toString(), correoText.getText().toString(), true);
        //Users.addUser(userText.getText().toString(),pass1.getText().toString(),correoText.getText().toString());
        usDB.registrarUsuario(us);
        setResult(RESULT_OK);
        finish();


    }

    public void onCancelar(View view){
        setResult(RESULT_CANCELED);
        finish();
    }



}
