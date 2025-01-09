package utez.edu.mx.systemris.insumo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "insumo")
public class Insumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "insumo",columnDefinition = "VARCHAR(30)")
    private String insumo;
    @Column(name="stock",columnDefinition = "INT")
    private int stock;
    @Column(name="descripcion",columnDefinition = "VARCHAR(80)")
    private String descripcion;
    @Column(name="status",columnDefinition = "BOOL")
    private boolean status;

    public Insumo(String descripcion, int stock, String insumo, Long id, boolean status) {
        this.descripcion = descripcion;
        this.stock = stock;
        this.insumo = insumo;
        this.id = id;
        this.status = status;
    }

    public Insumo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
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

    public Insumo(boolean status, String descripcion, int stock, String insumo) {
        this.status = status;
        this.descripcion = descripcion;
        this.stock = stock;
        this.insumo = insumo;
    }
}
