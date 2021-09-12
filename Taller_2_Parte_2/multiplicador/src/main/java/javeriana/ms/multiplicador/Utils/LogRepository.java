package javeriana.ms.multiplicador.Utils;

import javeriana.ms.multiplicador.Model.LogFile;
import javeriana.ms.multiplicador.Model.LogPersist;

import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class LogRepository {
    private final String FILE_NAME_LOGSXML = "Multiplicaciones.xml";

    public void PersistCall(String user){
        try {
            ArrayList<LogPersist> logs = LoadLogs();
            if(logs == null){
                logs = new ArrayList<>();
            }
            logs.add(new LogPersist(user));
            LogFile logFile = new LogFile(logs);
            BufferedWriter output = Files.newBufferedWriter(Path.of(FILE_NAME_LOGSXML));
            JAXB.marshal(logFile, output);
        } catch (IOException e) {
            System.out.println("Error de marshall");
        }
    }

    public ArrayList<LogPersist> LoadLogs(){
        try {
            BufferedReader input = Files.newBufferedReader(Path.of(FILE_NAME_LOGSXML));
            LogFile logFile = JAXB.unmarshal(input, LogFile.class);
            return (ArrayList<LogPersist>) logFile.getLogs();
        } catch (IOException e) {
            System.out.println("Error unmarshall");
        }
        return null;
    }
}
