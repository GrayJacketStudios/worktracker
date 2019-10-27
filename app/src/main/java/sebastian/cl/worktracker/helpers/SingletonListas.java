package sebastian.cl.worktracker.helpers;

import java.util.ArrayList;
import java.util.List;

import sebastian.cl.worktracker.registros.Registro;
import sebastian.cl.worktracker.trabajos.Trabajos;

public class SingletonListas {
    private static volatile SingletonListas SL = new SingletonListas();

    public List<Registro> registroList;
    public List<Trabajos> trabajosList;

    private SingletonListas(){
        registroList = new ArrayList<>();
        trabajosList = new ArrayList<>();

        trabajosList.add(new Trabajos(1,1,"","Proyecto INIF","Desarrollo front-end de INIF"));
        trabajosList.add(new Trabajos(2,1,"","back-end PUQTour","Desarrollo back-end de PUQTour"));
        trabajosList.add(new Trabajos(3,1,"","Prueba","Relleno para tests"));
        trabajosList.add(new Trabajos(4,5,"","Otro usuario","Trabajo de otro usuario"));

        registroList.add(new Registro(1,1,"15-10-2019","15-10-2019",35));
        registroList.add(new Registro(2,1,"16-10-2019","16-10-2019",65));
        registroList.add(new Registro(3,1,"18-10-2019","17-10-2019",185));


        registroList.add(new Registro(4,2,"14-10-2019","12-10-2019",115));
        registroList.add(new Registro(5,2,"17-10-2019","16-10-2019",85));

    }

    public static SingletonListas getInstance(){
        return SL;
    }
}
