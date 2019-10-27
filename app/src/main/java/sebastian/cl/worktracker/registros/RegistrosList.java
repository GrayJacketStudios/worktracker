package sebastian.cl.worktracker.registros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sebastian.cl.worktracker.R;
import sebastian.cl.worktracker.helpers.SingletonListas;
import sebastian.cl.worktracker.trabajos.Trabajos;

public class RegistrosList extends AppCompatActivity {

    ListView listView;
    Trabajos trabajo;
    int HorasTotales = 0;
    RegistrosListAdapter adapter;
    List<Registro> listaRegistro;

    private void registrosByTrabajos(List<Registro> registroList, int idTrabajo){
        for(Registro reg: registroList){
            if(reg.getTrabajoID() == idTrabajo){
                listaRegistro.add(reg);
                HorasTotales += reg.getHoras_trabajadas();
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_list);

        trabajo = (Trabajos) getIntent().getSerializableExtra("trabajo");
        listView = findViewById(R.id.LVRegistros);

        listaRegistro = new ArrayList<>();
        registrosByTrabajos(SingletonListas.getInstance().registroList,trabajo.getTrabajoID());

        adapter = new
                RegistrosListAdapter(this, R.layout.registro_list_item,listaRegistro);

        TextView txtHorasTrabajadas = (TextView) findViewById(R.id.txtHtotales);
        txtHorasTrabajadas.setText(R.string.tTotal+Registro.minutosAHorasMinuto(HorasTotales));
        listView.setAdapter(adapter);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                registrosByTrabajos(SingletonListas.getInstance().registroList,trabajo.getTrabajoID());
                TextView txtHorasTrabajadas = (TextView) findViewById(R.id.txtHtotales);
                txtHorasTrabajadas.setText(R.string.tTotal+Registro.minutosAHorasMinuto(HorasTotales));
                adapter.notifyDataSetChanged();
            }
        }
    }


    public void OnCreaRegistro(View view){
        Intent intent = new Intent(RegistrosList.this,CreateRegistro.class);
        intent.putExtra("trabajoId",trabajo.getTrabajoID());
        startActivityForResult(intent, 1);
    }
}
