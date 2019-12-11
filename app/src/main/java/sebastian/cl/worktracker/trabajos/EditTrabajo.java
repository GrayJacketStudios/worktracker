package sebastian.cl.worktracker.trabajos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sebastian.cl.worktracker.R;
import sebastian.cl.worktracker.helpers.SingletonListas;

public class EditTrabajo extends AppCompatActivity {

    EditText nombreEditText;
    EditText descripcionEditText;


    Trabajos trabajo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trabajo);

        nombreEditText  = (EditText)findViewById(R.id.ETETNombreTrabajo);
        descripcionEditText = (EditText)findViewById(R.id.ETETDescProyecto);

        trabajo = (Trabajos)getIntent().getSerializableExtra("trabajo");
        nombreEditText.setText(trabajo.getTitulo());
        descripcionEditText.setText(trabajo.getDescripcion());
    }

    public void onGuardar(View view){
        TrabajoDatabaseHelper traDB = new TrabajoDatabaseHelper(this);
        trabajo.setDescripcion(descripcionEditText.getText().toString());
        trabajo.setTitulo(nombreEditText.getText().toString());
        try{
            traDB.cambiarTrabajo(trabajo);
            setResult(RESULT_OK);
            Toast.makeText(this, "Cambios guardados con exito", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }catch (Exception e){
            setResult(RESULT_CANCELED);
            Toast.makeText(this, "Error al guardar los cambios.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }



        /*Obsoleto, codigo de legado :)
        for(Trabajos tr: SingletonListas.getInstance().trabajosList){
            if(tr.getTrabajoID() == trabajo.getTrabajoID()){
                tr.setTitulo(nombreEditText.getText().toString());
                tr.setDescripcion((descripcionEditText.getText().toString()));
                setResult(RESULT_OK);
                Toast.makeText(this, "Cambios guardados con exito", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }

        */
        //setResult(RESULT_CANCELED);



    }

    public void onCancelarEdit(View view){
        setResult(RESULT_CANCELED);
        finish();
        return;

    }

    public void eliminarTrabajo(int ID){
        TrabajoDatabaseHelper traDB = new TrabajoDatabaseHelper(this);
        traDB.eliminarTrabajo(ID);
        setResult(RESULT_OK);
        Toast.makeText(EditTrabajo.this, "Trabajo eliminado", Toast.LENGTH_SHORT).show();
        finish();
        return;
    }

    public void onEliminar(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Eliminar tarea");
        alertDialog.setMessage("Estas a punto de eliminar una tarea con todos sus registros, ¿Estas segur@?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Eliminar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        eliminarTrabajo(trabajo.getTrabajoID());

                        setResult(RESULT_OK);
                        Toast.makeText(EditTrabajo.this, "Trabajo eliminado", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();



    }



}
