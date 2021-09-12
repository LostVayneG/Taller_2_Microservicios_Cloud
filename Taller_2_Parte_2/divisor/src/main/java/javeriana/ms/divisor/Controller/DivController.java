package javeriana.ms.divisor.Controller;

import javeriana.ms.divisor.Model.LogPersist;
import javeriana.ms.divisor.Utils.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DivController {
    @Autowired
    Environment environment;
    private LogRepository logRepository = new LogRepository();

    @GetMapping("/division")
    public String Div(@RequestParam double a, @RequestParam double b, @RequestParam String user){
        String port = environment.getProperty("local.server.port");
        double result = a/b;
        logRepository.PersistCall(user);
        String response = "Resultado: " + result + " -> Microservicio division corriendo en el puerto: " + port;
        return response;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET, produces = "application/json")
    public List<LogPersist> History(){
        return logRepository.LoadLogs();
    }
}
