package utez.edu.mx.systemris.turno.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.systemris.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    Optional<Turno> findById(Long id);

    List<Turno> findAllByStatusIsTrue();
}
