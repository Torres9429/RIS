package utez.edu.mx.systemris.insumo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    List<Insumo> findAll();
    List<Insumo> findAllByStatusIsTrue();
}
