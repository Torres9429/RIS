package utez.edu.mx.systemris.usuario.control;

import utez.edu.mx.systemris.role.model.Role;
import utez.edu.mx.systemris.role.model.RoleRepository;
import utez.edu.mx.systemris.usuario.model.Usuario;
import utez.edu.mx.systemris.usuario.model.UsuarioDto;
import utez.edu.mx.systemris.usuario.model.UsuarioRepository;
import utez.edu.mx.systemris.utils.Message;
import utez.edu.mx.systemris.utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;

    public UsuarioService(
            UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        logger.info("La búsqueda de todos los usuarios ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(usuarios, "Listado de usuarios", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario foundCliente = usuarioOptional.get();
            return new ResponseEntity<>(new Message(foundCliente, "Usuario encontrado", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(UsuarioDto dto) {
        // Validación de longitudes
        if (dto.getNombre().length() > 60) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres permitidos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getApellidos().length() > 70) {
            return new ResponseEntity<>(new Message("Los apellidos exceden el número de caracteres permitidos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getCorreo().length() > 100) {
            return new ResponseEntity<>(new Message("El correo excede el número de caracteres permitidos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getTelefono().length() > 10) {
            return new ResponseEntity<>(new Message("El teléfono excede el número de caracteres permitidos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (usuarioRepository.existsByCorreo(dto.getCorreo())) {
            return new ResponseEntity<>(new Message("El correo ya está registrado", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        // Guardar el usuario
        Optional<Role> optionalRole = roleRepository.findByNombre(dto.getRole()); // Cambia 'getRole' según tu DTO
        System.out.println("Rol recibido: " + dto.getRole());
        if (optionalRole.isEmpty()) {
            return new ResponseEntity<>(new Message("Rol no encontrado", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Role role = optionalRole.get();

        Usuario usuario = new Usuario(
                dto.getNombre(),
                dto.getApellidos(),
                dto.getCorreo(),
                dto.getTelefono(),
                dto.getContrasena(),
                true
        );
        // Asociar el rol al usuario
        usuario.getRoles().add(role);
        usuario = usuarioRepository.saveAndFlush(usuario);
        if (usuario == null) {
            return new ResponseEntity<>(new Message("El usuario no se registró", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("El usuario se registró correctamente");
        return new ResponseEntity<>(new Message(usuario.getCorreo(), "Usuario registrado correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(UsuarioDto dto) {
        if (dto.getId() == null) {
            return new ResponseEntity<>(new Message("El id del usuario no puede ser nulo", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        // Validación de longitudes
        if (dto.getNombre().length() > 50) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres permitidos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getApellidos().length() > 100) {
            return new ResponseEntity<>(new Message("Los apellidos exceden el número de caracteres permitidos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getCorreo().length() > 150) {
            return new ResponseEntity<>(new Message("El correo excede el número de caracteres permitidos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getTelefono().length() > 15) {
            return new ResponseEntity<>(new Message("El teléfono excede el número de caracteres permitidos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(dto.getId());
        System.out.println(dto.getId());
        if (!usuarioOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setCorreo(dto.getCorreo());
        usuario.setTelefono(dto.getTelefono());
        usuario = usuarioRepository.saveAndFlush(usuario);
        if (usuario == null) {
            return new ResponseEntity<>(new Message("El usuario no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización del usuario ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(usuario.getNombre(), "Usuario actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(UsuarioDto dto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(dto.getId());
        if (!usuarioOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setStatus(!usuario.isStatus());
        usuario = usuarioRepository.saveAndFlush(usuario);
        if (usuario == null) {
            return new ResponseEntity<>(new Message("El estado del usuario no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("El estado del usuario se actualizó correctamente");
        return new ResponseEntity<>(new Message(usuario, "Estado del usuario actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findActives() {
        List<Usuario> respuestas = usuarioRepository.findAllByStatusIsTrue();
        logger.info("Lista de usuarios activos");
        return new ResponseEntity<>(new Message(respuestas, "Usuarios con status activo", TypesResponse.SUCCESS), HttpStatus.OK);
    }

}

