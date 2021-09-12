package javeriana.ms.sumador.Controller;
import javeriana.ms.sumador.Model.LogPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import javeriana.ms.sumador.Utils.LogRepository;

import java.util.List;

@RestController
public class MySumController {
    @Autowired
    Environment environment;
    private LogRepository logRepository = new LogRepository();

    @GetMapping("/suma")
    public String Sum(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        String port = environment.getProperty("local.server.port");
        int result = a + b;
        logRepository.PersistCall(user);
        String response = "Resultado: " + result + " -> Microservicio suma corriendo en el puerto: " + port;
        return response;
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET, produces = "application/json")
    public List<LogPersist> History(){
        return logRepository.LoadLogs();
    }
}
