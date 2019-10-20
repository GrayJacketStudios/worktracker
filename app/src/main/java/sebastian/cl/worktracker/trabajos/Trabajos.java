package sebastian.cl.worktracker.trabajos;

public class Trabajos {
    int user_ID, trabajoID;



    String image, titulo, descripcion;

    public Trabajos(int ID, int user_ID, String image, String titulo, String descripcion) {
        this.trabajoID = ID;
        this.user_ID = user_ID;
        this.image = image;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getTrabajoID() {
        return trabajoID;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
