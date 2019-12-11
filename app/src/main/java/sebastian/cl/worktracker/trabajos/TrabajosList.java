package sebastian.cl.worktracker.trabajos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sebastian.cl.worktracker.R;
import sebastian.cl.worktracker.helpers.SingletonListas;
import sebastian.cl.worktracker.usuarios.Usuario;

public class TrabajosList extends AppCompatActivity {


    ListView listView;
    Usuario user;
    List<Trabajos> listaTrabajos;
    TrabajosListAdapter adapter;


    /*Obsoleto!
    protected void trabajosByUsers(List<Trabajos> trabajosList, int user_ID){
        listaTrabajos.clear();
        for (Trabajos tr: trabajosList) {
            if(tr.getUser_ID() == user_ID){
                listaTrabajos.add(tr);
            }
        }
    }
    */

    protected void cargaListaTrabajos(){
        TrabajoDatabaseHelper trabDB = new TrabajoDatabaseHelper(this);
        this.listaTrabajos = trabDB.getTrabajos(this.listaTrabajos, user.getID());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos_list);
        user = (Usuario)getIntent().getSerializableExtra("user");
        listView = (ListView) findViewById(R.id.LVtrabajos);
        this.listaTrabajos = new ArrayList<>();
        cargaListaTrabajos();

        //trabajosByUsers(SingletonListas.getInstance().trabajosList,user.getID());

        adapter = new
                TrabajosListAdapter(this,R.layout.trabajos_list_item,listaTrabajos);

        listView.setAdapter(adapter);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                cargaListaTrabajos();
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                cargaListaTrabajos();
                adapter.notifyDataSetChanged();
            }
        }

    }

    public void OnCreateTrabajo(View view){
        Intent intent = new Intent(TrabajosList.this,CreateTrabajo.class);
        intent.putExtra("userId",user.getID());
        startActivityForResult(intent, 1);

    }

}
