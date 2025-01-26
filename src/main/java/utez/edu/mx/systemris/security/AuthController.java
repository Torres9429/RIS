package utez.edu.mx.systemris.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utez.edu.mx.systemris.role.model.RoleRepository;
import utez.edu.mx.systemris.security.dto.AuthRequest;
import utez.edu.mx.systemris.security.dto.AuthResponse;
import utez.edu.mx.systemris.usuario.model.Usuario;
import utez.edu.mx.systemris.usuario.model.UsuarioRepository;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil, UsuarioRepository userRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getCorreo(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Usuario o contraseña incorrectos", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getCorreo());
        final String jwt = jwtUtil.generateToken(userDetails);

        Usuario user = usuarioRepository.findFirstByCorreo(authRequest.getCorreo())
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        // Obtener el rol del usuario
        String role = user.getRoles().iterator().next().getNombre();

        long expirationTime = jwtUtil.getExpirationTime();

        return new AuthResponse(jwt, user.getId(), user.getCorreo(), role, expirationTime);
    }
}