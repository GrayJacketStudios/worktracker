package sebastian.cl.worktracker.trabajos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    
    protected void trabajosByUsers(List<Trabajos> trabajosList, int user_ID){
        List<Trabajos> newTrab = new ArrayList<>();
        for (Trabajos tr: trabajosList) {
            if(tr.getUser_ID() == user_ID){
                listaTrabajos.add(tr);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos_list);
        user = (Usuario)getIntent().getSerializableExtra("user");
        listView = (ListView) findViewById(R.id.LVtrabajos);

        listaTrabajos = new ArrayList<>();

        trabajosByUsers(SingletonListas.getInstance().trabajosList,user.getID());

        TrabajosListAdapter adapter = new
                TrabajosListAdapter(this,R.layout.trabajos_list_item,listaTrabajos);

        listView.setAdapter(adapter);
    }
}
