package javeriana.ms.calculadora;

import javeriana.ms.calculadora.Model.LogPersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class CalculatorController {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/calculadora/suma")
    public String CalculadoraSuma(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        String response = restTemplate.getForObject("http://sumador/suma?a={a}&b={b}&user={user}", String.class, a, b, user);
        return response;
    }

    @GetMapping("/calculadora/multiplicacion")
    public String CalculadoraMul(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        String response = restTemplate.getForObject("http://multiplicador/multiplicacion?a={a}&b={b}&user={user}", String.class, a, b, user);
        return response;
    }
    @GetMapping("/calculadora/resta")
    public String CalculadoraRes(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        String response = restTemplate.getForObject("http://restador/resta?a={a}&b={b}&user={user}", String.class, a, b, user);
        return response;
    }
    @GetMapping("/calculadora/division")
    public String CalculadoraDiv(@RequestParam int a, @RequestParam int b, @RequestParam String user){
        String response = restTemplate.getForObject("http://divisor/division?a={a}&b={b}&user={user}", String.class, a, b, user);
        return response;
    }

    @RequestMapping(value = "/calculadora/history", method = RequestMethod.GET, produces = "application/json")
    public List<LogPersist> OperationHistory(@RequestParam String operation){
        return restTemplate.getForObject("http://{operation}/history", List.class, operation);
    }
}
