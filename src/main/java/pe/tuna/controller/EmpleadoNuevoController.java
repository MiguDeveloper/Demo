package pe.tuna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.tuna.models.EmpleadoNuevo;
import pe.tuna.repository.EmpleadoNuevoRepository;
import pe.tuna.util.CustomErrorType;

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
        if (!lstEmpleados.isEmpty()) {
            return lstEmpleados;
        } else {
            throw new EmpleadoNotFoundException();
        }
    }

    @GetMapping("/empleado/{id}")
    public Optional<EmpleadoNuevo> getEmpledadById(@PathVariable Integer id) {
        Optional<EmpleadoNuevo> empleadoNuevo = empleadoNuevoRepository.findById(id);
        if (empleadoNuevo.isPresent()) {
            return empleadoNuevo;
        } else {
            throw new EmpleadoNotFoundException(id);
        }
    }

    // Creamos un nuevo empleado para ello debemos de retornar un 201 de created en caso de exito
    @PostMapping("/add")
    public EmpleadoNuevo createEmpleado(@RequestBody EmpleadoNuevo empleadoNuevo, HttpServletResponse response) {
        EmpleadoNuevo empleadoNuevo1 = new EmpleadoNuevo(empleadoNuevo.getNombre(), empleadoNuevo.getApellidos(), empleadoNuevo.getFecha_nacimiento());
        response.setStatus(201);
        return empleadoNuevoRepository.save(empleadoNuevo1);
    }

    // Actualizamos un empleado mediante la anotacion put
    @PutMapping("/empleado")
    public ResponseEntity<?> updateEmpleado(RequestEntity<EmpleadoNuevo> reqEmpleado) {
        if (reqEmpleado.getBody() == null) {
            return new ResponseEntity(new CustomErrorType("Formato de peticion incorrecta"), HttpStatus.BAD_REQUEST);
        }

        EmpleadoNuevo empleadoNuevo = reqEmpleado.getBody();

        Optional<EmpleadoNuevo> empleadoEncontrado = empleadoNuevoRepository.findById(empleadoNuevo.getId());

        if (empleadoEncontrado.isPresent()) {
            EmpleadoNuevo aActualizar = new EmpleadoNuevo(empleadoNuevo.getId(), empleadoNuevo.getNombre(), empleadoNuevo.getApellidos(),
                    empleadoNuevo.getFecha_nacimiento());
            return new ResponseEntity(empleadoNuevoRepository.save(aActualizar), HttpStatus.OK);
        } else {
            return new ResponseEntity(new CustomErrorType("El empleado a modificar no existe"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable int id) {

        Optional<EmpleadoNuevo> empleadoNuevo = empleadoNuevoRepository.findById(id);
        if (empleadoNuevo.isPresent()) {
            empleadoNuevoRepository.deleteById(id);
            return new ResponseEntity<>(empleadoNuevo, HttpStatus.OK);
        } else {
            return new ResponseEntity(new CustomErrorType("El empleado a eliminar no existe"), HttpStatus.NOT_FOUND);
        }

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    private class EmpleadoNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 7295910574475009536L;

        public EmpleadoNotFoundException() {
            super("No existe ningun empleado");
        }

        public EmpleadoNotFoundException(int id) {
            super(String.format("No existe ningun empleado con el ID= %d", id));
        }
    }

}
