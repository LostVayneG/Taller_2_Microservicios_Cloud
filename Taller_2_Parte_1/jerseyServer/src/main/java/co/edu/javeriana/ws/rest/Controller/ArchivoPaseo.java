package co.edu.javeriana.ws.rest.Controller;

import co.edu.javeriana.ws.rest.Model.Paseo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement(name = "Paseos")
public class ArchivoPaseo {
    @XmlElement( name = "Paseo")
    private Collection<Paseo> paseos = new ArrayList<>();

    public ArchivoPaseo(Collection<Paseo> paseos){
        this.paseos = paseos;
    }

    public ArchivoPaseo(){

    }

    public Collection<Paseo> getPaseos() {
        return paseos;
    }
}
