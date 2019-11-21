package sebastian.cl.worktracker.registros;

import java.io.Serializable;
import java.util.Date;
@SuppressWarnings("serial")
public class Registro implements Serializable {
    private int id, trabajoID;
    private String f_creacion, f_actualizacion, f_trabajo;
    private int horas_trabajadas;//Se manejara en minutos

    public Registro(int id, int trabajoID, String f_creacion, String f_trabajo, int horas_trabajadas) {
        this.id = id;
        this.trabajoID = trabajoID;
        this.f_creacion = f_creacion;
        this.f_trabajo = f_trabajo;
        this.horas_trabajadas = horas_trabajadas;
    }

    public int getID(){return id; }

    public int getTrabajoID() {
        return trabajoID;
    }

    public void setTrabajoID(int trabajoID) {
        this.trabajoID = trabajoID;
    }

    public String getF_creacion() {
        return f_creacion;
    }

    public void setF_creacion(String f_creacion) {
        this.f_creacion = f_creacion;
    }

    public String getF_actualizacion() {
        return f_actualizacion;
    }

    public void setF_actualizacion(String f_actualizacion) {
        this.f_actualizacion = f_actualizacion;
    }

    public String getF_trabajo() {
        return f_trabajo;
    }

    public void setF_trabajo(String f_trabajo) {
        this.f_trabajo = f_trabajo;
    }

    public int getHoras_trabajadas() {
        return horas_trabajadas;
    }

    public void setHoras_trabajadas(int horas_trabajadas) {
        this.horas_trabajadas = horas_trabajadas;
    }


    public static String minutosAHorasMinuto(int totalMinutos){
        String minutos = Integer.toString(totalMinutos % 60);
        minutos = minutos.length() == 1 ? "0" + minutos : minutos;
        return (totalMinutos/60) + ":" + minutos;
    }
}
