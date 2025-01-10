package utez.edu.mx.systemris.turno.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TurnoDto {
    @NotNull(groups = {Modify.class, TurnoDto.ChangeStatus.class}, message = "Es necesario el id")
    private Long id;

    @NotBlank(groups = {Modify.class, TurnoDto.Register.class}, message = "Es necesario el nombre")
    private String turno;

    @NotNull(groups = {Modify.class, ChangeStatus.class},message = "El id de la enfermera no puede ser nulo")
    private Long enfermeraId;

    @NotNull(groups = {Modify.class, ChangeStatus.class},message = "El id del doctor no puede ser nulo")
    private Long doctorId;

    public TurnoDto() {
    }

    public TurnoDto(Long id, String turno) {
        this.id = id;
        this.turno = turno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTurno() {
        return turno;
    }

    public Long getEnfermeraId() {
        return enfermeraId;
    }

    public void setEnfermeraId(Long enfermeraId) {
        this.enfermeraId = enfermeraId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public interface Modify { }
    public interface ChangeStatus { }
    public interface Register { }
}
