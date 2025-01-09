package utez.edu.mx.systemris.enfermera.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EnfermeraDto {
    @NotNull(groups = {Modify.class, ChangeStatus.class}, message = "Es necesario el id")
    private Long id;

    @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario el nombre")
    private String nombre;

    @NotBlank(groups = {Modify.class, Register.class}, message = "Son necesarios los apellidos")
    private String apellidos;

    @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesaria la área de trabajo")
    private String areaTrabajo;

    @NotNull(groups = {Modify.class, Register.class}, message = "Son necesarios los años de experiencia")
    private int aniosExperiencia;

    public EnfermeraDto() {
    }

    public EnfermeraDto(Long id, String nombre, String apellidos, String areaTrabajo, int aniosExperiencia) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.areaTrabajo = areaTrabajo;
        this.aniosExperiencia = aniosExperiencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public interface Modify { }
    public interface ChangeStatus { }
    public interface Register { }
}
