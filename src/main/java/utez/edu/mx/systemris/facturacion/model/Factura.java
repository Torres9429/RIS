package utez.edu.mx.systemris.facturacion.model;

import jakarta.persistence.*;
import utez.edu.mx.systemris.medicamento.model.Medicamento;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_factura", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_factura;
    @Column(name = "total", columnDefinition = "DOUBLE")
    private double total;
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Medicamento> medicamentos;

    public Factura() {
    }

    public Factura(Long id, Date fecha_factura, double total, List<Medicamento> medicamentos) {
        this.id = id;
        this.fecha_factura = fecha_factura;
        this.total = total;
        this.medicamentos = medicamentos;
    }

    public Factura(Date fecha_factura, double total, List<Medicamento> medicamentos) {
        this.fecha_factura = fecha_factura;
        this.total = total;
        this.medicamentos = medicamentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Medicamento> getMedicamento() {
        return medicamentos;
    }

    public void setMedicamento(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
