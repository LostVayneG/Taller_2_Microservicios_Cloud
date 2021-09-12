package Model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Paseo {
    private int id;
    private String nombre;
    private String lugarOrigen;
    private String lugarDestino;
    private Date fecha;

    public Paseo(){
    }

    @Override
    public String toString() {
        return "Paseo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", lugarOrigen='" + lugarOrigen + '\'' +
                ", lugarDestino='" + lugarDestino + '\'' +
                ", fecha=" + fecha +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugarOrigen() {
        return lugarOrigen;
    }

    public void setLugarOrigen(String lugarOrigen) {
        this.lugarOrigen = lugarOrigen;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = lugarDestino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
