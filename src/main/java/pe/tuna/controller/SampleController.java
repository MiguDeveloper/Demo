package pe.tuna.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiSample")
public class SampleController {

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/hello")
    public String sayHello(){
        return "Hola Miguel desde el WS";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/hello")
    public String sayHelloForAdmin(){
        return "Hola estimado Administrador";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/hello")
    public String submitGreet(@RequestBody String greet){
        return "El saludo recibido es: " + greet;
    }
}
