package javeriana.ms.multiplicador.Controller;

import javeriana.ms.multiplicador.Model.LogPersist;
import javeriana.ms.multiplicador.Utils.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MultiplicationController {
    @Autowired
    Environment environment;
    private LogRepository logRepository = new LogRepository();

    @GetMapping("/multiplicacion")
    public String Mul(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        String port = environment.getProperty("local.server.port");
        int result = a * b;
        logRepository.PersistCall(user);
        String response = "Resultado: " + result + " -> Microservicio multiplicacion corriendo en el puerto: " + port;
        return response;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET, produces = "application/json")
    public List<LogPersist> History(){
        return logRepository.LoadLogs();
    }
}
