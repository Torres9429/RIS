package utez.edu.mx.systemris.facturacion.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findAll();
    //List<Factura> findById();
    Optional<Factura> findById(Long id);
}
