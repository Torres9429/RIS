package utez.edu.mx.systemris.doctor.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.systemris.doctor.model.Doctor;
import utez.edu.mx.systemris.doctor.model.DoctorDto;
import utez.edu.mx.systemris.doctor.model.DoctorRepository;
import utez.edu.mx.systemris.utils.Message;
import utez.edu.mx.systemris.utils.TypesResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DoctorService {
    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Doctor> doctores = doctorRepository.findAll();
        logger.info("La búsqueda de todos los doctores ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(doctores, "Listado de doctores", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isPresent()) {
            Doctor foundDoctor = doctorOptional.get();
            return new ResponseEntity<>(new Message(foundDoctor, "Doctor encontrado", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("El doctor no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(DoctorDto dto) {
        if(dto.getNombre().length() > 70) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getApellidos().length() > 70) {
            return new ResponseEntity<>(new Message("Los apellidos exceden el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getCedula().length() > 30) {
            return new ResponseEntity<>(new Message("La cédula excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getEspecialidad().length() > 30) {
            return new ResponseEntity<>(new Message("La especialidad excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getConsultorio().length() > 50) {
            return new ResponseEntity<>(new Message("El nombre del consultorio excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        Doctor doctor = new Doctor(dto.getNombre(), dto.getApellidos(), dto.getCedula(), dto.getEspecialidad(), dto.getConsultorio(), true);
        doctor = doctorRepository.saveAndFlush(doctor);
        if(doctor == null){
            return new ResponseEntity<>(new Message("El doctor no se registró",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("El registro ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(doctor,"El doctor se registró correctamente",TypesResponse.SUCCESS),HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(DoctorDto dto) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(dto.getId());
        if(!doctorOptional.isPresent()){
            return new ResponseEntity<>(new Message("El doctor no existe",TypesResponse.ERROR),HttpStatus.NOT_FOUND);
        }
        if(dto.getNombre().length() > 70) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getApellidos().length() > 70) {
            return new ResponseEntity<>(new Message("Los apellidos exceden el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getCedula().length() > 30) {
            return new ResponseEntity<>(new Message("La cédula excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getEspecialidad().length() > 30) {
            return new ResponseEntity<>(new Message("La especialidad excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getConsultorio().length() > 50) {
            return new ResponseEntity<>(new Message("El nombre del consultorio excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }

        Doctor doctor = doctorOptional.get();
        doctor.setNombre(dto.getNombre());
        doctor.setApellidos(dto.getApellidos());
        doctor.setCedula(dto.getCedula());
        doctor.setConsultorio(dto.getConsultorio());
        doctor = doctorRepository.saveAndFlush(doctor);
        if(doctor == null){
            return new ResponseEntity<>(new Message("El doctor no se actualizó",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(doctor,"El doctor se actualizó correctamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(DoctorDto dto) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(dto.getId());
        if (!doctorOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
        Doctor doctor = doctorOptional.get();
        doctor.setStatus(!doctor.isStatus());
        doctor = doctorRepository.saveAndFlush(doctor);
        if (doctor == null) {
            return new ResponseEntity<>(new Message("El estado del usuario no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("El estado del usuario se actualizó correctamente");
        return new ResponseEntity<>(new Message(doctor, "Estado del usuario actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findActives() {
        List<Doctor> respuestas = doctorRepository.findAllByStatusIsTrue();
        logger.info("Lista de doctores activos");
        return new ResponseEntity<>(new Message(respuestas, "Doctores con status activo", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
