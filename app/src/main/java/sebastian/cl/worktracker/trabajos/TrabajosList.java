package sebastian.cl.worktracker.trabajos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sebastian.cl.worktracker.R;

public class TrabajosList extends AppCompatActivity {

    List<Trabajos> trabajosList;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajos_list);

        trabajosList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.LVtrabajos);

        trabajosList.add(new Trabajos(1,1,"","Proyecto INIF","Desarrollo front-end de INIF"));
        trabajosList.add(new Trabajos(2,1,"","back-end PUQTour","Desarrollo back-end de PUQTour"));
        trabajosList.add(new Trabajos(3,1,"","Prueba","Relleno para tests"));


        TrabajosListAdapter adapter = new
                TrabajosListAdapter(this,R.layout.trabajos_list_item,trabajosList);

        listView.setAdapter(adapter);
    }
}
