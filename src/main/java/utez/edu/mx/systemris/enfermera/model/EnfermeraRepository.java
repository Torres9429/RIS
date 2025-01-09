package utez.edu.mx.systemris.enfermera.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnfermeraRepository extends JpaRepository<Enfermera, Long> {
    Optional<Enfermera> findById(Long id);

    List<Enfermera> findAllByStatusIsTrue();
}
