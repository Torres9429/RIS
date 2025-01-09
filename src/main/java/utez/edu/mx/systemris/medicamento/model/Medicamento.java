package utez.edu.mx.systemris.medicamento.model;

import jakarta.persistence.*;
import utez.edu.mx.systemris.insumo.model.Insumo;

@Entity
@Table(name = "medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*@Column(name = "medicamento", columnDefinition = "VARCHAR(30)")
    private String medicamento;*/
    @Column(name = "precio", columnDefinition = "DOUBLE")
    private Double precio;
    @Column(name = "descripcion", columnDefinition = "VARCHAR(50)")
    private String descripcion;
    @OneToOne(mappedBy = "medicamentos", cascade = CascadeType.ALL)
    private Insumo medicamento;

    public Medicamento(Long id, String descripcion, Double precio, Insumo medicamento) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.medicamento = medicamento;
    }

    public Medicamento(Long medicamento, Double precio, String descripcion) {
        this.medicamento = medicamento;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Medicamento() {
    }

    public Medicamento(Insumo medicamento, Double precio, String descripcion) {
        this.medicamento = medicamento;
        this.precio = precio;
        this.descripcion = descripcion;
    }

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
}

