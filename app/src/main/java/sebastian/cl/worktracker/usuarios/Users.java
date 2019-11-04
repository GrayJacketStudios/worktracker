package sebastian.cl.worktracker.usuarios;

import android.app.Application;

import java.util.ArrayList;

import sebastian.cl.worktracker.helpers.SingletonListas;

public class Users {
    public Users(){
        super();
        //Aqui procedemos a asignar un usuario de prueba, ya que no conectaremos nada a BD aun.
        addUser("foobar","123456","a@a.com");
    }
    
    public Usuario iniciaSesion(String user, String pass){//Metodo para verificar inicio de sesion sin base de datos pero con  una lista.
        for (Usuario us: SingletonListas.getInstance().usuariosList) {
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
        Usuario us = new Usuario((SingletonListas.getInstance().usuariosList.size()+1),pass,user,correo,true);
        SingletonListas.getInstance().usuariosList.add(us);
        return us;
    }


    
    
    
}
