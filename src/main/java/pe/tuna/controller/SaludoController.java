package pe.tuna.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.tuna.models.Saludo;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SaludoController {

    private static final String template = "Hola, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/saludo")
    public Saludo saludo(@RequestParam(value = "name", defaultValue = "Miguel") String name){
        return new Saludo(counter.incrementAndGet(), String.format(template,name));
    }
}
