package sebastian.cl.worktracker.trabajos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import sebastian.cl.worktracker.R;
import sebastian.cl.worktracker.helpers.TouchTimer;
import sebastian.cl.worktracker.registros.RegistrosList;

import static android.content.ContentValues.TAG;

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

        view.setClickable(true);
        view.setOnTouchListener(touchListener);

        view.setTag(trabajo);


        textViewNombre.setText(trabajo.getTitulo());
        textViewDescri.setText(trabajo.getDescripcion());

        return view;
    }

    //Funcion para determinar si editaremos/eliminaremos el trabajo o si solo accederemos a el (mas de dos segundos presionando lo editara)
    public View.OnTouchListener touchListener = new TouchTimer() {
        @Override
        protected void onTouchEnded(long TouchInMillis, View v) {
            Log.d(TAG, "TouchTime: "+TouchInMillis);
            if(TouchInMillis >= 1000){
                Trabajos trabajo = (Trabajos) v.getTag();
                Intent intent = new Intent(context, EditTrabajo.class);
                intent.putExtra("trabajo",(Trabajos)trabajo);
                ((Activity)context).startActivityForResult(intent,5);
            }
            else{
                Trabajos trabajo = (Trabajos) v.getTag();
                Intent intent = new Intent(context, RegistrosList.class);
                intent.putExtra("trabajo",(Trabajos)trabajo);
                context.startActivity(intent);
            }
        }
    };


}
