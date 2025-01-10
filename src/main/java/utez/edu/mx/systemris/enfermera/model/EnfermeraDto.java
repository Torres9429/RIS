package utez.edu.mx.systemris.enfermera.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import utez.edu.mx.systemris.doctor.model.DoctorDto;

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
    @NotBlank(groups = {DoctorDto.Modify.class, DoctorDto.Register.class}, message = "Es necesario el turno")
    private String turno;


    public EnfermeraDto() {
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
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
    public interface Modify { }
    public interface ChangeStatus { }
    public interface Register { }
}
