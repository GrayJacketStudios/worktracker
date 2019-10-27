package sebastian.cl.worktracker.registros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import sebastian.cl.worktracker.R;

public class RegistrosListAdapter extends ArrayAdapter<Registro> {

    Context context;
    int resource;
    List<Registro> registroList;

    public RegistrosListAdapter(Context context, int resource, List<Registro> registroList){
        super(context, resource, registroList);
        this.context = context;
        this.resource = resource;
        this.registroList = registroList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, null, false);

        TextView textViewTiempo = view.findViewById(R.id.txtTiempoTrabajado);
        TextView textViewCreado = view.findViewById(R.id.txtCreado);
        TextView textViewTrabajado = view.findViewById(R.id.txtFechaTrabajo);
        TextView textViewActu = view.findViewById(R.id.txtLastUpdate);

        Registro registro = registroList.get(position);

        textViewTiempo.setText("Tiempo trabajado: "+registro.minutosAHorasMinuto(registro.getHoras_trabajadas()));
        textViewCreado.setText("Creada el: "+registro.getF_creacion());
        textViewActu.setText("Actualizada el: "+registro.getF_actualizacion());
        textViewTrabajado.setText("Trabajado el: "+registro.getF_trabajo());


        return view;
    }
}
