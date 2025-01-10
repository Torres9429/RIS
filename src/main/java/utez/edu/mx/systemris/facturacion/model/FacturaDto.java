package utez.edu.mx.systemris.facturacion.model;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class FacturaDto {
    @NotNull(groups = {Modify.class, ChangeStatus.class},message = "El id no puede quedar vacío")
    private Long id;
    @NotNull
    @FutureOrPresent(groups = {Register.class,Modify.class},message = "La fecha no puede quedar vacía")
    private Date fecha_factura;
    @NotBlank(groups = {Register.class, Modify.class},message = "El concepto no puede quedar vacío")
    private String concepto;
    @NotNull(groups = {Register.class,Modify.class},message = "El total no puede quedar vacío")
    private double total;
    @NotNull(groups = {Register.class, Modify.class}, message = "El id de la factura no puede quedar vacío")
    private Long facturaId;
    public FacturaDto() {
    }

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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    public Long getFacturaId() {
        return facturaId;
    }
    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public interface Register{}
    public interface Modify{}
    public interface ChangeStatus{}
}
