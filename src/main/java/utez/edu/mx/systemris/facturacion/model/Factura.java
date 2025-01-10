package utez.edu.mx.systemris.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "concepto", columnDefinition = "VARCHAR(50)")
    private String concepto;
    /*@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Medicamento> medicamentos;*/
    @ManyToOne
    @JoinColumn(name = "medicamento_id", referencedColumnName = "id")
    private Medicamento medicamentos;

    public Factura() {
    }

    public Factura(Long id, Date fecha_factura, double total, String concepto, Medicamento medicamentos) {
        this.id = id;
        this.fecha_factura = fecha_factura;
        this.total = total;
        this.concepto = concepto;
        this.medicamentos = medicamentos;
    }

    public Factura(double total, String concepto, Medicamento medicamentos) {
        this.total = total;
        this.concepto = concepto;
        this.medicamentos = medicamentos;
    }

    public Factura(String concepto, double total, Medicamento medicamentos) {
        this.total = total;
        this.fecha_factura = new Date();
        this.concepto = concepto;
        this.medicamentos = medicamentos;
    }
    /*public Factura(Long id, Date fecha_factura, double total, List<Medicamento> medicamentos) {
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

    public Factura(String concepto, double total, List<Medicamento> medicamentos) {
        this.fecha_factura = new Date();
        this.concepto = concepto;
        this.total = total;
        this.medicamentos = medicamentos;
    }*/

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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /*public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }*/

    public Medicamento getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(Medicamento medicamentos) {
        this.medicamentos = medicamentos;
    }
}
