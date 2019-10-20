package sebastian.cl.worktracker.usuarios;

import android.app.Application;

import java.util.ArrayList;

public class Users {
    private ArrayList<Usuario> users;
    public Users(){
        super();
        users = new ArrayList<Usuario>();

        //Aqui procedemos a asignar un usuario de prueba, ya que no conectaremos nada a BD aun.
        users.add(new Usuario(1,"123456","foobar","a@a.com",true));
    }
    
    public Usuario iniciaSesion(String user, String pass){//Metodo para verificar inicio de sesion sin base de datos pero con  una lista.
        for (Usuario us: users) {
            if(!us.getUsername().equals(user)){
                continue;//No retornamos aun, pero nos saltamos los siguientes pasos del foreach para pasar al siguiente elemento.
            }
            if(!us.checkPassword(pass)){
                continue;
            }
            //Pasamos los dos verificadores por separado, procedemos a iniciar sesión.
            return us;
        }
        //Se retornara null si no se encontro ningun usuario valido.
        return null;
    }


    public Usuario addUser(String user, String pass, String correo){
        Usuario us = new Usuario((users.size()+1),pass,user,correo,true);
        users.add(us);
        return us;
    }
    
    
    
}
