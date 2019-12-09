package sebastian.cl.worktracker.usuarios;
import java.io.Serializable;
@SuppressWarnings("serial")
public class Usuario implements Serializable {

    private int ID;
    private String password;
    private String username;
    private String correo;
    private String nombre;
    private boolean habilitado;



    public Usuario(String password, String username, String correo, boolean habilitado) {
        this.password = password;
        this.username = username;
        this.correo = correo;
        this.habilitado = habilitado;
    }

    public Usuario(Integer ID, String password, String username, String correo, boolean habilitado) {
        this.ID = ID;
        this.password = password;
        this.username = username;
        this.correo = correo;
        this.habilitado = habilitado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public int getID(){
        return ID;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String pass2){//Creamos este metodo para no compartir la pass, pero chequear que sea valida.
        return password.equals(pass2);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }





}
