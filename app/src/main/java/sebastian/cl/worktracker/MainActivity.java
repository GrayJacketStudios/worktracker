package sebastian.cl.worktracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import sebastian.cl.worktracker.trabajos.TrabajosList;
import sebastian.cl.worktracker.usuarios.Users;
import sebastian.cl.worktracker.usuarios.Usuario;

public class MainActivity extends AppCompatActivity  {
    protected Users us;
    protected Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        us = new Users();
    }

    public void logear(View view){
        EditText ETUsuario = (EditText)findViewById(R.id.txtUsername);
        EditText ETPassword = (EditText)findViewById(R.id.txtPassword);
        TextView TVError = (TextView)findViewById(R.id.txtErrorMsg);
        if((ETUsuario.getText().toString().equals(""))){//No se ingreso un usuario
            TVError.setText(R.string.login_error_1);
            return;
        }
        if(ETPassword.getText().toString().equals("")){//No se ingreso una contraseña
            TVError.setText(R.string.login_error_2);
            return;
        }
        if((user = us.iniciaSesion(ETUsuario.getText().toString(),ETPassword.getText().toString())) == null){//No se ha encontrado al usuario
            TVError.setText(R.string.login_error_3);
            return;
        }
        //Aqui ya pasamos las comprobaciones y tenemos al objeto usuario en la variable "user"
        Intent intent = new Intent(MainActivity.this, TrabajosList.class);
        intent.putExtra("user", (Usuario) user);
        startActivity(intent);
    }
}
