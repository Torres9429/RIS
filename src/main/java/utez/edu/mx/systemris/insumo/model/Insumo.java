package utez.edu.mx.systemris.insumo.model;

import jakarta.persistence.*;
import utez.edu.mx.systemris.medicamento.model.Medicamento;

import java.util.List;

@Entity
@Table(name = "insumo")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamentos;
    @Column(name="stock",columnDefinition = "INT")
    private int stock;
    @Column(name="descripcion",columnDefinition = "VARCHAR(80)")
    private String descripcion;
    @Column(name="status",columnDefinition = "BOOL")
    private boolean status;

    public Insumo(Long id, Medicamento medicamentos, int stock, String descripcion, boolean status) {
        this.id = id;
        this.medicamentos = medicamentos;
        this.stock = stock;
        this.descripcion = descripcion;
        this.status = status;
    }

    public Insumo() {
    }

    public Insumo(Medicamento medicamentos, int stock, String descripcion, boolean status) {
        this.medicamentos = medicamentos;
        this.stock = stock;
        this.descripcion = descripcion;
        this.status = status;
    }

    public Insumo(boolean status, String descripcion, int stock, Long medicamentoId) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicamento getMedicamentos() {

        return medicamentos;
    }

    public void setMedicamentos(Medicamento medicamentos) {
        this.medicamentos = medicamentos;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
