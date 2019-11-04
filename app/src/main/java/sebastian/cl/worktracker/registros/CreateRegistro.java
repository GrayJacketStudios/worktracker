package sebastian.cl.worktracker.registros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import sebastian.cl.worktracker.R;
import sebastian.cl.worktracker.helpers.DatePickerFragment;
import sebastian.cl.worktracker.helpers.SingletonListas;

public class CreateRegistro extends AppCompatActivity {
    EditText etFechaTrabajo;
    int trabajoID;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            .withZone(ZoneId.systemDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_registro);

        trabajoID = (Integer)getIntent().getIntExtra("trabajoId",0);
        etFechaTrabajo = (EditText) findViewById(R.id.ETFechaTrabajada);

        etFechaTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.ETFechaTrabajada:
                        showDatePickerDialog();
                        break;
                }
            }
        });
    }


    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + "-" + (month+1) + "-" + year;
                etFechaTrabajo.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public void addRegistro(View view){
        EditText fechaEditText = (EditText) findViewById(R.id.ETFechaTrabajada);
        EditText tiempoEditText = (EditText) findViewById(R.id.ETTiempoTrabajado);
        Registro regi = new Registro(
                (SingletonListas.getInstance().registroList.size()+1),
                trabajoID,
                formatter.format(Instant.now())
                ,
                //"05-10-2019",
                fechaEditText.getText().toString(),
                Integer.parseInt(tiempoEditText.getText().toString())
        );

        SingletonListas.getInstance().registroList.add(regi);
        setResult(RESULT_OK);
        finish();


    }

    public void OnCancelar(View view){

        setResult(RESULT_CANCELED);
        finish();
    }

}
