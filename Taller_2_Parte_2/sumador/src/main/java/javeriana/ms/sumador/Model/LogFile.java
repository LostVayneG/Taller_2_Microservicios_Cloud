package javeriana.ms.sumador.Model;

import org.apache.juli.logging.Log;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@XmlRootElement(name = "Logs")
public class LogFile {
    @XmlElement( name = "logs")
    private Collection<LogPersist> logs = new ArrayList<>();

    public LogFile(Collection<LogPersist> paseos){
        this.logs = paseos;
    }

    public LogFile(){

    }

    public Collection<LogPersist> getLogs() {
        return logs;
    }
}
