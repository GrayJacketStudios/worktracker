package sebastian.cl.worktracker.usuarios;

public class Usuario {

    private int ID;
    private String password;
    private String username;
    private String correo;
    private boolean habilitado;

    public Usuario(int id, String password, String username, String correo, boolean habilitado) {
        ID = id;
        this.password = password;
        this.username = username;
        this.correo = correo;
        this.habilitado = habilitado;
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
