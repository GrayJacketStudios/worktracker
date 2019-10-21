package sebastian.cl.worktracker.trabajos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sebastian.cl.worktracker.R;
import sebastian.cl.worktracker.usuarios.Usuario;

public class TrabajosList extends AppCompatActivity {

    List<Trabajos> trabajosList;

    ListView listView;
    Usuario user;
    
    protected List<Trabajos> trabajosByUsers(List<Trabajos> trabajosList, int user_ID){
        List<Trabajos> newTrab = new ArrayList<>();
        for (Trabajos tr: trabajosList) {
            if(tr.getUser_ID() == user_ID){
                newTrab.add(tr);
            }
        }
        return newTrab;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos_list);
        user = (Usuario)getIntent().getSerializableExtra("user");


        trabajosList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.LVtrabajos);

        trabajosList.add(new Trabajos(1,1,"","Proyecto INIF","Desarrollo front-end de INIF"));
        trabajosList.add(new Trabajos(2,1,"","back-end PUQTour","Desarrollo back-end de PUQTour"));
        trabajosList.add(new Trabajos(3,1,"","Prueba","Relleno para tests"));
        trabajosList.add(new Trabajos(4,5,"","Otro usuario","Trabajo de otro usuario"));


        TrabajosListAdapter adapter = new
                TrabajosListAdapter(this,R.layout.trabajos_list_item,trabajosByUsers(trabajosList,user.getID()));

        listView.setAdapter(adapter);
    }
}
