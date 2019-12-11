package sebastian.cl.worktracker.trabajos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sebastian.cl.worktracker.R;

public class CreateTrabajo extends AppCompatActivity {
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trabajo);

        userId = (Integer)getIntent().getIntExtra("userId",0);


    }

    public void addTrabajo(View view){
        EditText ETNombreTrabajo = (EditText)findViewById(R.id.ETCTNombreTrabajo);
        EditText ETDescriTrabajo = (EditText)findViewById(R.id.ETCTDescTrabajo);
        EditText ETTiempoObjetivo = (EditText)findViewById(R.id.ETCTTiempoObjetivo);

        if(ETNombreTrabajo.getText().toString().equals("")){
            Toast.makeText(this, "Debes rellenar los datos", Toast.LENGTH_SHORT).show();
            return;
        }

        Trabajos trabajo = new Trabajos(
                userId,
                "",
                ETNombreTrabajo.getText().toString(),
                ETDescriTrabajo.getText().toString()
        );
        try{
            TrabajoDatabaseHelper trabDB = new TrabajoDatabaseHelper(this);
            trabDB.addTrabajo(trabajo);
            Toast.makeText(this, "Trabajo añadido con exito", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        }catch (Exception e){
            Toast.makeText(this, "Error al añadir el trabajo", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED);
            finish();
            return;
        }


        //SingletonListas.getInstance().trabajosList.add(trabajo);

    }

    public void OnCancelar(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

}
