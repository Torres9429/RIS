package utez.edu.mx.systemris.insumo.model;

import jakarta.persistence.*;
import utez.edu.mx.systemris.medicamento.model.Medicamento;

import java.util.List;

@Entity
@Table(name = "insumos")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="stock",columnDefinition = "INT")
    private int stock;
    @Column(name="descripcion",columnDefinition = "VARCHAR(80)")
    private String descripcion;
    @Column(name="status",columnDefinition = "BOOL")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamentos;

    public Insumo(Long id, int stock, String descripcion, boolean status) {
        this.id = id;
        this.stock = stock;
        this.descripcion = descripcion;
        this.status = status;
    }

    public Insumo() {
    }

    public Insumo(int stock, String descripcion, boolean status) {
        this.stock = stock;
        this.descripcion = descripcion;
        this.status = status;
    }

    public Insumo(boolean status, String descripcion, int stock) {
        this.stock = stock;
        this.descripcion = descripcion;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Medicamento getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(Medicamento medicamentos) {
        this.medicamentos = medicamentos;
    }
}
