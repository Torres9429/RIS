package utez.edu.mx.systemris.turno.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import utez.edu.mx.systemris.turno.TurnoEnum;

public class TurnoDto {
    @NotNull(groups = {TurnoDto.Modify.class, TurnoDto.ChangeStatus.class}, message = "Es necesario el id")
    private Long id;

    @NotBlank(groups = {TurnoDto.Modify.class, TurnoDto.Register.class}, message = "Es necesario el nombre")
    private String turno;

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

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public interface Modify { }
    public interface ChangeStatus { }
    public interface Register { }
}
