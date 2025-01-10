package utez.edu.mx.systemris.enfermera.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.systemris.enfermera.model.Enfermera;
import utez.edu.mx.systemris.enfermera.model.EnfermeraDto;
import utez.edu.mx.systemris.enfermera.model.EnfermeraRepository;
import utez.edu.mx.systemris.utils.Message;
import utez.edu.mx.systemris.utils.TypesResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class EnfermeraService {

    Logger logger = LoggerFactory.getLogger(EnfermeraService.class);
    private final EnfermeraRepository enfermeraRepository;

    public EnfermeraService(EnfermeraRepository enfermeraRepository) {
        this.enfermeraRepository = enfermeraRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Enfermera> enfermeras = enfermeraRepository.findAll();
        logger.info("La búsqueda de todos las enfermeras ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(enfermeras, "Listado de enfermeras", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        Optional<Enfermera> enfermeraOptional = enfermeraRepository.findById(id);
        if (enfermeraOptional.isPresent()) {
            Enfermera foundEnfermera = enfermeraOptional.get();
            return new ResponseEntity<>(new Message(foundEnfermera, "Enfermera encontrada", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("La enfermera no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(EnfermeraDto dto) {
        if (dto.getNombre().length() > 70) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getApellidos().length() > 70) {
            return new ResponseEntity<>(new Message("Los apellidos exceden el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getAreaTrabajo().length() > 70) {
            return new ResponseEntity<>(new Message("La área de trabajo excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Enfermera enfermera = new Enfermera(dto.getNombre(), dto.getApellidos(), dto.getAreaTrabajo(), dto.getAniosExperiencia(), true, dto.getTurno());
        enfermera = enfermeraRepository.saveAndFlush(enfermera);
        if (enfermera == null) {
            return new ResponseEntity<>(new Message("La enfermera no se registró", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("El registro ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(enfermera, "La enfermera se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(EnfermeraDto dto) {
        Optional<Enfermera> enfermeraOptional = enfermeraRepository.findById(dto.getId());
        if (!enfermeraOptional.isPresent()) {
            return new ResponseEntity<>(new Message("La enfermera no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
        if (dto.getNombre().length() > 70) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getApellidos().length() > 70) {
            return new ResponseEntity<>(new Message("Los apellidos exceden el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getAreaTrabajo().length() > 70) {
            return new ResponseEntity<>(new Message("La área de trabajo excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Enfermera enfermera = enfermeraOptional.get();
        enfermera.setNombre(dto.getNombre());
        enfermera.setApellidos(dto.getApellidos());
        enfermera.setAreaTrabajo(dto.getAreaTrabajo());
        enfermera.setAniosExperiencia(dto.getAniosExperiencia());
        enfermera.setTurno(dto.getTurno());
        enfermera = enfermeraRepository.saveAndFlush(enfermera);
        if (enfermera.getId() == null) {
            return new ResponseEntity<>(new Message("La enfermera no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(enfermera, "La enfermera se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(EnfermeraDto dto) {
        Optional<Enfermera> enfermeraOptional = enfermeraRepository.findById(dto.getId());
        if (!enfermeraOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
        Enfermera enfermera = enfermeraOptional.get();
        Enfermera enfermeraNew = enfermeraOptional.get();
        enfermeraNew.setStatus(!enfermera.isStatus());
        enfermeraRepository.save(enfermeraNew);
//        if (enfermeraNew == null) {
//            return new ResponseEntity<>(new Message("El estado del usuario no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
//        }
        logger.info("El estado del usuario se actualizó correctamente");
        return new ResponseEntity<>(new Message(enfermera, "Estado del usuario actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

//    @Transactional(readOnly = true)
//    public ResponseEntity<Message> findActives() {
//        List<Enfermera> respuestas = enfermeraRepository.findAllByStatusIsTrue();
//        logger.info("Lista de enfermeras activas");
//        return new ResponseEntity<>(new Message(respuestas, "Doctores con status activo", TypesResponse.SUCCESS), HttpStatus.OK);
//    }
}