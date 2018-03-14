package pe.tuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.tuna.models.EmpleadoNuevo;

@Repository
public interface EmpleadoNuevoRepository extends JpaRepository<EmpleadoNuevo, Integer> {
}
