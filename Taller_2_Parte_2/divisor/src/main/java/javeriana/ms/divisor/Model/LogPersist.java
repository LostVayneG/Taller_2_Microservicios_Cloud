package javeriana.ms.divisor.Model;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name = "Log")
public class LogPersist {
    private String fechaInvocacion;
    private String operacion;
    private String user;

    public LogPersist(String user) {
        this.setFechaInvocacion(LocalDateTime.now().toString());
        this.setOperacion("Division");
        this.setUser(user);
    }

    public LogPersist(){

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getFechaInvocacion() {
        return fechaInvocacion;
    }

    public void setFechaInvocacion(String fechaInvocacion) {
        this.fechaInvocacion = fechaInvocacion;
    }
    
    
}
