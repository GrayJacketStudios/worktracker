package sebastian.cl.worktracker.registros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sebastian.cl.worktracker.R;
import sebastian.cl.worktracker.trabajos.Trabajos;

public class RegistrosList extends AppCompatActivity {

    ListView listView;
    Trabajos trabajo;
    int HorasTotales = 0;
    RegistrosListAdapter adapter;
    List<Registro> listaRegistro = new ArrayList<>();


    private void cargaListaRegistro(){
        RegistroDatabaseHelper regDB = new RegistroDatabaseHelper(this);
        this.listaRegistro = regDB.getRegistros(this.listaRegistro,trabajo.getTrabajoID());
        this.setHorasTotales();
    }

    private void setHorasTotales(){
        this.HorasTotales = 0;
        for(Registro reg: this.listaRegistro){
            this.HorasTotales += reg.getHoras_trabajadas();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_list);

        trabajo = (Trabajos) getIntent().getSerializableExtra("trabajo");
        listView = findViewById(R.id.LVRegistros);

        cargaListaRegistro();


        adapter = new
                RegistrosListAdapter(this, R.layout.registro_list_item,listaRegistro);

        TextView txtHorasTrabajadas = (TextView) findViewById(R.id.txtHtotales);
        txtHorasTrabajadas.setText(getString(R.string.tTotal,Registro.minutosAHorasMinuto(HorasTotales)));
        listView.setAdapter(adapter);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 || requestCode == 5) {
            if (resultCode == RESULT_OK) {
                cargaListaRegistro();
                TextView txtHorasTrabajadas = (TextView) findViewById(R.id.txtHtotales);
                txtHorasTrabajadas.setText(getString(R.string.tTotal,Registro.minutosAHorasMinuto(HorasTotales)));
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
