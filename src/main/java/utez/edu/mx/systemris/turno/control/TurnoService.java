package utez.edu.mx.systemris.turno.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.systemris.doctor.model.DoctorRepository;
import utez.edu.mx.systemris.enfermera.model.Enfermera;
import utez.edu.mx.systemris.enfermera.model.EnfermeraRepository;
import utez.edu.mx.systemris.turno.model.Turno;
import utez.edu.mx.systemris.turno.model.TurnoDto;
import utez.edu.mx.systemris.turno.model.TurnoRepository;
import utez.edu.mx.systemris.utils.Message;
import utez.edu.mx.systemris.utils.TypesResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private static final Logger logger = LoggerFactory.getLogger(TurnoService.class);
    private final TurnoRepository turnoRepository;
    private final DoctorRepository doctorRepository;
    private final EnfermeraRepository enfermeraRepository;

    public TurnoService(TurnoRepository turnoRepository, DoctorRepository doctorRepository, EnfermeraRepository enfermeraRepository) {
        this.turnoRepository = turnoRepository;
        this.doctorRepository = doctorRepository;
        this.enfermeraRepository = enfermeraRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Turno> turnos = turnoRepository.findAll();
        logger.info("La búsqueda de todos los turnos ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(turnos, "Listado de turnos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()) {
            Turno foundTurno = turnoOptional.get();
            return new ResponseEntity<>(new Message(foundTurno, "Turno encontrado", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("El turno no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(TurnoDto dto) {
        Turno turno = new Turno(dto.getTurno(), true);
        turno = turnoRepository.saveAndFlush(turno);
        if(turno == null){
            return new ResponseEntity<>(new Message("El turno no se registró",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("El registro ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(turno,"El turno se registró correctamente",TypesResponse.SUCCESS),HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(TurnoDto dto) {
        Optional<Turno> turnoOptional = turnoRepository.findById(dto.getId());
        if(!turnoOptional.isPresent()){
            return new ResponseEntity<>(new Message("El turno no existe",TypesResponse.ERROR),HttpStatus.NOT_FOUND);
        }
        Turno turno = turnoOptional.get();
        turno.setTurno(dto.getTurno());
        if(turno == null){
            return new ResponseEntity<>(new Message("El turno no se actualizó",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(turno,"El turno se actualizó correctamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findActives() {
        List<Turno> respuestas = turnoRepository.findAllByStatusIsTrue();
        logger.info("Lista de turnos activos");
        return new ResponseEntity<>(new Message(respuestas, "Turnos con status activo", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
