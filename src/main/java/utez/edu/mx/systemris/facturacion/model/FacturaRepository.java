package utez.edu.mx.systemris.facturacion.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findAll();
    List<Factura> findById();
}
