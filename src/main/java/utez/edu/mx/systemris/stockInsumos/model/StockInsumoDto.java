package utez.edu.mx.systemris.stockInsumos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StockInsumoDto {
    @NotNull(groups = {Modify.class, ChangeStatus.class},message = "El id no puede quedar vacío")
    private Long id;
    @NotBlank(groups = {Modify.class, Register.class},message = "El insumo no puede quedar vacío")
    private String insumo;
    @NotBlank(groups = {Modify.class, Register.class},message = "La descripción no puede quedar vacía")
    private String description;
    @NotNull(groups = {Modify.class, Register.class}, message = "El stock no puede quedar vacío")
    private int stock;

    public StockInsumoDto() {
    }

    public StockInsumoDto(Long id, String insumo, String description, int stock) {
        this.id = id;
        this.insumo = insumo;
        this.description = description;
        this.stock = stock;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public interface Register {}
    public interface Modify {}
    public interface ChangeStatus {}
}
