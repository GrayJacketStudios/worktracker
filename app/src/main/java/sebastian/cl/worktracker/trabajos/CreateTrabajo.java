package sebastian.cl.worktracker.trabajos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import sebastian.cl.worktracker.R;
import sebastian.cl.worktracker.helpers.SingletonListas;

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
        Trabajos trabajo = new Trabajos(
                (SingletonListas.getInstance().trabajosList.size()+1),
                userId,
                "",
                ETNombreTrabajo.getText().toString(),
                ETDescriTrabajo.getText().toString()
        );
        SingletonListas.getInstance().trabajosList.add(trabajo);
        setResult(RESULT_OK);
        finish();
    }

    public void OnCancelar(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

}
