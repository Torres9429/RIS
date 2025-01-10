package utez.edu.mx.systemris.medicamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import utez.edu.mx.systemris.facturacion.model.Factura;
import utez.edu.mx.systemris.insumo.model.Insumo;

import java.util.List;

@Entity
@Table(name = "medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "medicamento", columnDefinition = "VARCHAR(30)")
    private String medicamento;
    @Column(name = "precio", columnDefinition = "DOUBLE")
    private Double precio;
    @Column(name = "descripcion", columnDefinition = "VARCHAR(50)")
    private String descripcion;

    @OneToMany(mappedBy = "medicamentos")
    @JsonIgnore
    private List<Insumo> insumos;
    /*@ManyToOne
    @JoinColumn(name = "factura_id", referencedColumnName = "id")
    private Factura factura;*/
    @OneToMany(mappedBy = "medicamentos")
    @JsonIgnore
    private List<Factura> factura;

    public Medicamento(Long id, String medicamento, String descripcion, Double precio) {
        this.id = id;
        this.medicamento = medicamento;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Medicamento(String medicamento, Double precio, String descripcion) {
        this.medicamento = medicamento;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    /*public Medicamento(Long id, String medicamento, Double precio, String descripcion, List<Insumo> insumos, Factura factura) {
        this.id = id;
        this.medicamento = medicamento;
        this.precio = precio;
        this.descripcion = descripcion;
        this.insumos = insumos;
        this.factura = factura;
    }*/

    public Medicamento(Long id, String medicamento, Double precio, String descripcion, List<Insumo> insumos, List<Factura> factura) {
        this.id = id;
        this.medicamento = medicamento;
        this.precio = precio;
        this.descripcion = descripcion;
        this.insumos = insumos;
        this.factura = factura;
    }

    public Medicamento() {
    }

    /*public Medicamento(Insumo medicamento, Double precio, String descripcion) {
        this.medicamento = medicamento;
        this.precio = precio;
        this.descripcion = descripcion;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    /*public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Factura getFactura() {
        return factura;
    }*/

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<Insumo> insumos) {
        this.insumos = insumos;
    }

    public List<Factura> getFactura() {
        return factura;
    }

    public void setFactura(List<Factura> factura) {
        this.factura = factura;
    }
}

