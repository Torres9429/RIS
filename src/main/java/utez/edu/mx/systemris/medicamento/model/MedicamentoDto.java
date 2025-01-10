package utez.edu.mx.systemris.medicamento.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicamentoDto {
    @NotNull(groups = {Modify.class, Register.class},message = "el id no puede quedar vacio")
    private Long id;
    @NotNull(groups = {Modify.class,Register.class},message = "el Precio no puede quedar vacio")
    private Double precio;
    @NotBlank(groups = {Modify.class,Register.class},message = "el Medicamento no puede quedar vacio")
    private Long medicamento;
    @NotBlank(groups = {Modify.class,Register.class},message = "La descripción no puede quedar vacio")
    private String descripcion;

    public MedicamentoDto() {
    }

    public MedicamentoDto(Long id, Double precio, Long medicamento, String descripcion) {
        this.id = id;
        this.precio = precio;
        this.medicamento = medicamento;
        this.descripcion = descripcion;
    }

    public MedicamentoDto(Double precio, Long medicamento, String descripcion) {
        this.precio = precio;
        this.medicamento = medicamento;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Long medicamento) {
        this.medicamento = medicamento;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public interface Register{}
    public interface Modify{}
    public interface ChangeStatus{}
}
