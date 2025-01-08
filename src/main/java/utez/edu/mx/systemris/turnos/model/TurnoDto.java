package utez.edu.mx.systemris.turnos.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import utez.edu.mx.systemris.usuario.model.UsuarioDto;

public class TurnoDto {
    @NotNull(groups = {UsuarioDto.Modify.class, UsuarioDto.ChangeStatus.class}, message = "Es necesario el id")
    private Long id;

    @NotBlank(groups = {UsuarioDto.Modify.class, UsuarioDto.Register.class}, message = "Es necesario el nombre")
    private String nombre;
}
