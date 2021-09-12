package javeriana.ms.restador.Controller;

import javeriana.ms.restador.Model.LogPersist;
import javeriana.ms.restador.Utils.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubController {
    @Autowired
    Environment environment;
    private LogRepository logRepository = new LogRepository();

    @GetMapping("/resta")
    public String Resta(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        String port = environment.getProperty("local.server.port");
        logRepository.PersistCall(user);
        int result = a - b;
        String response = "Resultado: " + result + " -> Microservicio multiplicacion corriendo en el puerto: " + port;
        return response;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET, produces = "application/json")
    public List<LogPersist> History(){
        return logRepository.LoadLogs();
    }
}