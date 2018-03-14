package pe.tuna.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import pe.tuna.models.Citas;
import pe.tuna.models.Empleado;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpleadoController {
    List<Empleado> lstEmpleados;

    @GetMapping("/empleados")
    public List<Empleado> list() {
        return lstEmpleados;
    }

    @GetMapping("/empleado/{id}")
    public Empleado getEmpleado(@PathVariable Long id, HttpServletResponse response) {
        if (id > 0 && id <= lstEmpleados.size()) {
            return lstEmpleados.get((int) (id - 1));
        } else {
            //response.setStatus(404);
            //throw new EmpleadoNotFoundException();
            //return null;
            throw new EmpleadoNotFoundException(id);
        }

    }

    @GetMapping("/empleado/{id}/citas")
    public List<Citas> getCitasEmpleado(@PathVariable int id) {
        if (id > 0 && id <= lstEmpleados.size()) {
            return lstEmpleados.get(id - 1).getCitas();
        }
        return null;
    }

    @GetMapping("/empleado/{idE}/cita/{idC}")
    public Citas getCitaEmpleado(@PathVariable Long idE, @PathVariable Long idC, HttpServletRequest request)
            throws NoHandlerFoundException {
        Citas cita = null;
        if (idE > 0 && idE <= lstEmpleados.size()) {
            List<Citas> lstCitas = lstEmpleados.get((int) (idE - 1)).getCitas();
            if (lstCitas != null) {
                for (Citas c : lstCitas) {
                    if (c.getId() == idC) {
                        cita = c;
                    }
                }
            }
        }

        if (cita != null) {
            return cita;
        } else {
            throw new NoHandlerFoundException("GET", request.getRequestURL().toString(), null);
        }

    }

    @PostConstruct
    private void init() {
        lstEmpleados = new ArrayList<Empleado>();
        lstEmpleados.add(new Empleado(1L, "Pepe", "Gotera", new Date()));
        lstEmpleados.get(0).getCitas().add(new Citas(1L, "Reunion de trabajo", new Date()));
        lstEmpleados.get(0).getCitas().add(new Citas(2L, "Visita a un cliente", new Date()));

        lstEmpleados.add(new Empleado(2L, "Otilio", "Garcia", new Date()));
        lstEmpleados.get(1).getCitas().add(new Citas(3L, "Visita a un proveedor", new Date()));

        lstEmpleados.add(new Empleado(3L, "Mortadelo", "Gomez", new Date()));
        lstEmpleados.add(new Empleado(4L, "Filemon", "Guzman", new Date()));
        lstEmpleados.add(new Empleado(5L, "Super", "Lopez", new Date()));
        lstEmpleados.add(new Empleado(6L, "Jose", "Quispe", new Date()));
    }

    // Le asociamos al evento 404 la informacion de la razon
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "La cita no existe")
    @ExceptionHandler(NoHandlerFoundException.class)
    public void citaInexistente() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    //@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "El empleado no existe")
    private class EmpleadoNotFoundException extends RuntimeException {
        private static final long serialVersionUID = -53323244242424242L;

        public EmpleadoNotFoundException(Long id) {
            super(String.format("El empleado %d no existe.", id));
        }
    }
}
