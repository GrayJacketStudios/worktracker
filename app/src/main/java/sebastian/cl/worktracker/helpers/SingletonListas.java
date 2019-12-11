package sebastian.cl.worktracker.helpers;

import java.util.ArrayList;
import java.util.List;

import sebastian.cl.worktracker.registros.Registro;

public class SingletonListas {
    private static volatile SingletonListas SL = new SingletonListas();

    public List<Registro> registroList;


    private SingletonListas(){


    }

    public static SingletonListas getInstance(){
        return SL;
    }


}
