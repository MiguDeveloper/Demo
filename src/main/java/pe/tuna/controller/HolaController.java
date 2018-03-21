package pe.tuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HolaController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("saludo","Hola este es un saludos desde el controlador principal");
        return "hola/hola";
    }
}
