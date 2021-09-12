package co.edu.javeriana.ws.rest.Controller;

import co.edu.javeriana.ws.rest.Model.Paseo;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PaseoController {
    @XmlElement
    private ArrayList<Paseo> paseos;
    private int paseoIdSequence;

    private final String FILE_NAME_PASEOSXML = "Paseos.xml";

    public PaseoController(){
        this.paseos = new ArrayList<>();
        this.paseoIdSequence = 0;
        this.loadPaseos();
    }

    public Paseo createPaseo(Paseo newPaseo){
        newPaseo.setId(paseoIdSequence);
        this.paseoIdSequence++;
        this.paseos.add(newPaseo);
        this.persistPaseos();
        return newPaseo;
    }

    public void persistPaseos(){
        try {
            ArchivoPaseo archivoPaseo = new ArchivoPaseo(this.paseos);
            BufferedWriter output = Files.newBufferedWriter(Path.of(FILE_NAME_PASEOSXML));
            JAXB.marshal(archivoPaseo, output);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error de marshall");
        }
    }

    public void loadPaseos(){
        try {
            BufferedReader input = Files.newBufferedReader(Path.of(FILE_NAME_PASEOSXML));
            ArchivoPaseo archivoPaseo = JAXB.unmarshal(input, ArchivoPaseo.class);
            this.paseos = (ArrayList<Paseo>) archivoPaseo.getPaseos();
            actualizeCurrentId();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error unmarshall");
        }
    }

    private void actualizeCurrentId(){
        for (Paseo p:
             this.paseos) {
            if(p.getId() > this.paseoIdSequence){
                this.paseoIdSequence = p.getId() + 1;
            }
        }
    }

    public Paseo findById(int id){
        for (Paseo p:
             this.paseos) {
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public boolean removePaseo(int id){
        Paseo paseoToRemove = this.findById(id);
        if(paseoToRemove != null){
            this.paseos.remove(paseoToRemove);
            this.persistPaseos();
            actualizeCurrentId();
            return true;
        }
        return false;
    }

    public Paseo updatePaseo(Paseo paseo){
        Paseo paseoToUpdate = this.findById(paseo.getId());
        if(paseoToUpdate != null){
            paseoToUpdate.setNombre(paseo.getNombre());
            paseoToUpdate.setLugarOrigen(paseo.getLugarOrigen());
            paseoToUpdate.setLugarDestino(paseo.getLugarDestino());
            this.persistPaseos();
        }
        return paseoToUpdate;
    }

    public List<Paseo> getPaseos(){
        return this.paseos;
    }
}