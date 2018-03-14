package pe.tuna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.tuna.models.Empleado;
import pe.tuna.models.EmpleadoNuevo;
import pe.tuna.repository.EmpleadoNuevoRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/json")
public class EmpleadoNuevoController {

    @Autowired
    EmpleadoNuevoRepository empleadoNuevoRepository;

    @GetMapping("/empleados")
    public List<EmpleadoNuevo> getEmpleados() {
        List<EmpleadoNuevo> lstEmpleados = empleadoNuevoRepository.findAll();
        if (lstEmpleados != null) {
            return lstEmpleados;
        } else {
            throw new EmpleadoNotFoundException();
        }
    }

    @GetMapping("/empleado/{id}")
    public Optional<EmpleadoNuevo> getEmpledadById(@PathVariable Integer id){
        Optional<EmpleadoNuevo> empleadoNuevo = empleadoNuevoRepository.findById(id);
        if (empleadoNuevo != null){
            return empleadoNuevo;
        }else {
            throw new EmpleadoNotFoundException(id);
        }
    }

    // Debemos de retornar un 201 de created
    @PostMapping("/add")
    public EmpleadoNuevo createEmpleado(@RequestBody EmpleadoNuevo empleadoNuevo, HttpServletResponse response){
        EmpleadoNuevo empleadoNuevo1 = new EmpleadoNuevo(empleadoNuevo.getNombre(), empleadoNuevo.getApellidos(), empleadoNuevo.getFecha_nacimiento());
        response.setStatus(201);
        return empleadoNuevoRepository.save(empleadoNuevo1);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private class EmpleadoNotFoundException extends RuntimeException{
        private static final long serialVersionUID = 7295910574475009536L;

        public EmpleadoNotFoundException() {
            super("No existe ningun empleado");
        }

        public EmpleadoNotFoundException(int id) {
            super(String.format("No existe ningun empleado con el ID= %d", id));
        }
    }

}
