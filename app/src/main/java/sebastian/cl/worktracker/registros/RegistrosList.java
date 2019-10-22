package sebastian.cl.worktracker.registros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sebastian.cl.worktracker.R;
import sebastian.cl.worktracker.trabajos.Trabajos;

public class RegistrosList extends AppCompatActivity {

    List<Registro> registroList = new ArrayList<>();
    ListView listView;
    Trabajos trabajo;

    private List<Registro> registrosByTrabajos(List<Registro> registroList, int idTrabajo){
        List<Registro> newList = new ArrayList<>();
        for(Registro reg: registroList){
            if(reg.getTrabajoID() == idTrabajo){
                newList.add(reg);
            }
        }
        return newList;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros_list);

        trabajo = (Trabajos) getIntent().getSerializableExtra("trabajo");

        listView = findViewById(R.id.LVRegistros);

        registroList.add(new Registro(1,1,"15-10-2019","15-10-2019",35));
        registroList.add(new Registro(2,1,"16-10-2019","16-10-2019",65));
        registroList.add(new Registro(3,1,"18-10-2019","17-10-2019",185));


        registroList.add(new Registro(4,2,"14-10-2019","12-10-2019",115));
        registroList.add(new Registro(5,2,"17-10-2019","16-10-2019",85));

        RegistrosListAdapter adapter = new
                RegistrosListAdapter(this, R.layout.registro_list_item,registrosByTrabajos(registroList,trabajo.getTrabajoID()));

        listView.setAdapter(adapter);


    }
}
