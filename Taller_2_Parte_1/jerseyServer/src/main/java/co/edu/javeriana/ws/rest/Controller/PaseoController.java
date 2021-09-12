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

    private final String FILE_NAME_PASEOSXML = "Paseos.xml";

    public PaseoController(){
    }

    public Paseo createPaseo(Paseo newPaseo){
        ArrayList<Paseo> paseos = loadPaseos();
        newPaseo.setId(getIdSecuence(paseos));
        paseos.add(newPaseo);
        this.persistPaseos(paseos);
        return newPaseo;
    }

    public void persistPaseos(ArrayList<Paseo> paseos){
        try {
            ArchivoPaseo archivoPaseo = new ArchivoPaseo(paseos);
            BufferedWriter output = Files.newBufferedWriter(Path.of(FILE_NAME_PASEOSXML));
            JAXB.marshal(archivoPaseo, output);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error de marshall");
        }
    }

    public ArrayList<Paseo> loadPaseos(){
        try {
            BufferedReader input = Files.newBufferedReader(Path.of(FILE_NAME_PASEOSXML));
            ArchivoPaseo archivoPaseo = JAXB.unmarshal(input, ArchivoPaseo.class);
            return (ArrayList<Paseo>) archivoPaseo.getPaseos();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error unmarshall");
        }
        return new ArrayList<Paseo>();
    }

    private int getIdSecuence(ArrayList<Paseo> paseos){
        int paseoIdSequence = 0;
        for (Paseo p:
             paseos) {
            if(p.getId() >= paseoIdSequence){
                paseoIdSequence = p.getId() + 1;
            }
        }
        return paseoIdSequence;
    }

    public Paseo findById(int id){
        ArrayList<Paseo> paseos = loadPaseos();
        for (Paseo p:
             paseos) {
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public boolean removePaseo(int id){
        ArrayList<Paseo> paseos = loadPaseos();
        Paseo paseoToRemove = null;
        for (Paseo p:
                paseos) {
            if(p.getId() == id){
                paseoToRemove = p;
            }
        }
        if(paseoToRemove != null){
            paseos.remove(paseoToRemove);
            this.persistPaseos(paseos);
            return true;
        }
        return false;
    }

    public Paseo updatePaseo(Paseo paseo){
        ArrayList<Paseo> paseos = loadPaseos();
        Paseo paseoToUpdate = null;
        for (Paseo p:
             paseos) {
            if(p.getId() == paseo.getId()){
                paseoToUpdate = p;
            }
        }
        if(paseoToUpdate != null){
            paseoToUpdate.setLugarOrigen(paseo.getLugarOrigen());
            paseoToUpdate.setLugarDestino(paseo.getLugarDestino());
            this.persistPaseos(paseos);
        }
        return paseoToUpdate;
    }

    public List<Paseo> getPaseos(){
        return loadPaseos();
    }
}