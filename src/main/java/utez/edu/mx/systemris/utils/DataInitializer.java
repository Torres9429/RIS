package utez.edu.mx.systemris.utils;



import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import utez.edu.mx.systemris.role.model.Role;
import utez.edu.mx.systemris.role.model.RoleRepository;
import utez.edu.mx.systemris.usuario.model.Usuario;
import utez.edu.mx.systemris.usuario.model.UsuarioRepository;

import java.util.Optional;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {


            Optional<Role> optionalRole = roleRepository.findByNombre("ROLE_ADMIN_ACCESS");
            if (!optionalRole.isPresent()) {
                Role roleAdmin = new Role("ROLE_ADMIN_ACCESS");
                roleRepository.saveAndFlush(roleAdmin);

                Optional<Usuario> optionalUser = usuarioRepository.findFirstByCorreo("20233tn105@utez.edu.mx");
                if (!optionalUser.isPresent()) {
                    //Usuario usuarioAdmin = new Usuario("20233tn083@utez.edu.mx", passwordEncoder.encode("12345"));
                    Usuario usuarioAdmin = new Usuario();
                    usuarioAdmin.setApellidos("Rodriguez");
                    usuarioAdmin.setNombre("Rocio");
                    usuarioAdmin.setTelefono("77729283309");
                    usuarioAdmin.setCorreo("20233tn105@utez.edu.mx");
                    usuarioAdmin.setContrasena(passwordEncoder.encode("1234567"));
                    usuarioAdmin.getRoles().add(roleAdmin);
                    usuarioRepository.saveAndFlush(usuarioAdmin);
                }
            }

            optionalRole = roleRepository.findByNombre("ROLE_GERENTE_ACCESS");
            if (!optionalRole.isPresent()) {
                Role roleGerente = new Role("ROLE_GERENTE_ACCESS");
                roleRepository.saveAndFlush(roleGerente);

                Optional<Usuario> optionalUser = usuarioRepository.findFirstByCorreo("20233tn083@utez.edu.mx");
                if (!optionalUser.isPresent()) {
                   Usuario userGerente = new Usuario();
                    // Usuario userGerente = new Usuario("20233tn093@utez.edu.mx", passwordEncoder.encode("12345"));
                    userGerente.setApellidos("Delgado");
                    userGerente.setNombre("Alexa");
                    userGerente.setTelefono("1234567890");
                    userGerente.setCorreo("20233tn083@utez.edu.mx");
                    userGerente.setContrasena(passwordEncoder.encode("1234567"));
                    userGerente.getRoles().add(roleGerente);
                    usuarioRepository.saveAndFlush(userGerente);
                }
            }

        };
    }
}
