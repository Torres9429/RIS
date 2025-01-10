package utez.edu.mx.systemris.doctor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DoctorDto {
    @NotNull(groups = {Modify.class, ChangeStatus.class}, message = "Es necesario el id")
    private Long id;

    @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario el nombre")
    private String nombre;

    @NotBlank(groups = {Modify.class, Register.class}, message = "Son necesarios los apellidos")
    private String apellidos;

    @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario la cédula")
    private String cedula;

    @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario la especialidad")
    private String especialidad;

    @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario el consultorio")
    private String consultorio;
    @NotBlank(groups = {Modify.class, Register.class}, message = "Es necesario el turno")
    private String turno;

    public DoctorDto() {
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
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
