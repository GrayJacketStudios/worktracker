package sebastian.cl.worktracker.trabajos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import sebastian.cl.worktracker.R;

public class TrabajosListAdapter extends ArrayAdapter<Trabajos> {

    Context context;
    int resource;
    List<Trabajos> trabajosList;

    public TrabajosListAdapter(Context context, int resource, List<Trabajos> trabajosList) {
        super(context, resource, trabajosList);
        this.context = context;
        this.resource = resource;
        this.trabajosList = trabajosList;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, null, false);

        TextView textViewNombre = view.findViewById(R.id.txtTitulo);
        TextView textViewDescri = view.findViewById(R.id.txtDesc);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textViewTH = view.findViewById(R.id.txtTH);
        TextView textViewHS = view.findViewById(R.id.txtHS);
        TextView textViewLU = view.findViewById(R.id.txtLU);

        Trabajos trabajo = trabajosList.get(position);

        textViewNombre.setText(trabajo.getTitulo());
        textViewDescri.setText(trabajo.getDescripcion());

        return view;
    }
}
