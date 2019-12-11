package sebastian.cl.worktracker.registros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import sebastian.cl.worktracker.R;

import sebastian.cl.worktracker.helpers.DatePickerFragment;

public class EditRegistroActivity extends AppCompatActivity {
    Registro registro;
    EditText FechaTrabajadaEditText;
    EditText TiempoTrabajadoEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_registro);
        registro = (Registro) getIntent().getSerializableExtra("registro");
        FechaTrabajadaEditText = (EditText) findViewById(R.id.ETFechaTrabajada);
        TiempoTrabajadoEditText = (EditText) findViewById(R.id.ETTiempoTrabajado);
        FechaTrabajadaEditText.setText(registro.getF_trabajo());
        FechaTrabajadaEditText.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  switch (view.getId()){
                      case R.id.ETFechaTrabajada:
                          showDatePickerDialog();
                          break;
                  }

              }
        });
        TiempoTrabajadoEditText.setText(""+ registro.getHoras_trabajadas());

    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "-" + (month+1) + "-" + year;
                FechaTrabajadaEditText.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public void onGuardar(View view){
        registro.setF_trabajo(FechaTrabajadaEditText.getText().toString());
        registro.setHoras_trabajadas(Integer.parseInt(TiempoTrabajadoEditText.getText().toString()));
        RegistroDatabaseHelper regDB = new RegistroDatabaseHelper(this);
        regDB.cambiarRegistro(registro);
        setResult(RESULT_OK);
        Toast.makeText(this, "Cambios guardados con exito", Toast.LENGTH_SHORT).show();
        finish();
        return;


        //Obsoleto
        /*for(Registro reg: SingletonListas.getInstance().registroList){
            if(reg.getID() == registro.getID()){
                reg.setF_trabajo(FechaTrabajadaEditText.getText().toString());
                reg.setHoras_trabajadas(Integer.parseInt(TiempoTrabajadoEditText.getText().toString()));
                setResult(RESULT_OK);
                Toast.makeText(this, "Cambios guardados con exito", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }
        finish();
        return;
        */
    }



    public void onCancelar(View view){
        setResult(RESULT_CANCELED);
        finish();
        return;
    }
}
