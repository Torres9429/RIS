package utez.edu.mx.systemris.insumo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InsumoDto{
    @NotNull(groups = {Modify.class,ChangeStatus.class},message = "el id no puede quedar vacio")
    private Long id;
    @NotBlank(groups = {Register.class,Modify.class},message = "la descripcion no puede quedar vacio ")
    private String descripcion;
    @NotBlank(groups = {Register.class,Modify.class},message = "el insumo no puede quedar vacio")
    private String insumo;
    @NotNull(groups = {Modify.class,Register.class},message = "el stock no puede quedar vacio")
    private int stock;
    @NotNull(groups = {Modify.class,ChangeStatus.class,Register.class},message = "el estado no puede ser vacio")
    private boolean status;

    public InsumoDto() {

    }

    public String getInsumo() {
        return insumo;
    }

    public void setInsumo(String insumo) {
        this.insumo = insumo;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public interface Register{}
    public interface Modify{}
    public interface ChangeStatus{}

}
