package utez.edu.mx.systemris.insumo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InsumoDto{
    @NotNull(groups = {Modify.class,ChangeStatus.class},message = "el id no puede quedar vacio")
    private Long id;
    @NotNull(groups = {Modify.class,Register.class},message = "el stock no puede quedar vacio")
    private int stock;
    @NotBlank(groups = {Register.class,Modify.class},message = "la descripcion no puede quedar vacio ")
    private String descripcion;
    @NotNull(groups = {Register.class,Modify.class},message = "el insumo no puede quedar vacio")
    private Long medicamentoId;

    public InsumoDto() {

    }

    public Long getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
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

    public interface Register{}
    public interface Modify{}
    public interface ChangeStatus{}

}
