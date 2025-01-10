package utez.edu.mx.systemris.insumo.control;

import org.apache.coyote.Response;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.RollbackOn;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.systemris.insumo.model.Insumo;
import utez.edu.mx.systemris.insumo.model.InsumoDto;
import utez.edu.mx.systemris.insumo.model.InsumoRepository;
import utez.edu.mx.systemris.medicamento.model.Medicamento;
import utez.edu.mx.systemris.medicamento.model.MedicamentoRepository;
import utez.edu.mx.systemris.utils.Message;
import utez.edu.mx.systemris.utils.TypesResponse;
import org.slf4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InsumoService {
    private final static Logger logger = LoggerFactory.getLogger(InsumoService.class);
    private final InsumoRepository insumoRepository;
    private final MedicamentoRepository medicamentoRepository;

    @Autowired
    public InsumoService(InsumoRepository insumoRepository, MedicamentoRepository medicamentoRepository) {
        this.insumoRepository = insumoRepository;
        this.medicamentoRepository = medicamentoRepository;
    }
    @Transactional(readOnly = true)
    public ResponseEntity<Message>findAll(){
        List<Insumo> insumos = insumoRepository.findAll();
        logger.info("Busqueda de Insumo exitoso");
                return new ResponseEntity<>(new Message(insumos,"Lista de insumos", TypesResponse.SUCCESS), HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id){
        Optional<Insumo> insumo = insumoRepository.findById(id);
        if (insumo.isPresent()){
            logger.info("Busqueda de Insumo exitoso");
            return new ResponseEntity<>(new Message(insumo.get(),"Insumo encontrado", TypesResponse.SUCCESS), HttpStatus.OK);
        }else{
            logger.info("insumo no encontrado");
            return new ResponseEntity<>(new Message(null,"Insumo no encontrado", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);

        }
    }
    @Transactional(readOnly = true)
    public ResponseEntity<Message>findActives(){
        List<Insumo> insumos = insumoRepository.findAllByStatusIsTrue();
        logger.info("Busqueda de Insumos activos exitoso");
        return new ResponseEntity<>(new Message(insumos,"Lista de insumos activos", TypesResponse.SUCCESS), HttpStatus.OK);
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message>saveInsumo(InsumoDto insumoDto){

        if(insumoDto.getDescripcion().length()>80){
            return new ResponseEntity<>(new Message(insumoDto,"la descripcion sobrepasa el numero de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        Insumo insumo = new Insumo(insumoDto.isStatus(),insumoDto.getDescripcion(),insumoDto.getStock(),insumoDto.getMedicamentoId());
        insumo= insumoRepository.saveAndFlush(insumo);
        if(insumo == null){
            return new ResponseEntity<>(new Message("El insumo no se registró",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("El registro ha sido realizado correctamente");
        return new ResponseEntity<>(new Message(insumo,"El insumo se registró correctamente",TypesResponse.SUCCESS),HttpStatus.CREATED);

    }
    @Transactional(rollbackFor = {SQLException.class})
    public  ResponseEntity<Message> updateInsumo(InsumoDto insumoDto){
        Optional<Insumo> insumoOptional = insumoRepository.findById(insumoDto.getId());
        if (!insumoOptional.isPresent()){
            return new ResponseEntity<>(new Message("el insumo no fue encontrado",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);

        }
        if(insumoDto.getDescripcion().length()>80){
            return new ResponseEntity<>(new Message(insumoDto,"la descripcion sobrepasa el numero de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        Medicamento medicamento = medicamentoRepository.findById(insumoDto.getMedicamentoId()).orElse(null);
        Insumo insumo = insumoOptional.get();
        insumo.setDescripcion(insumoDto.getDescripcion());
        insumo.setStock(insumoDto.getStock());
        insumo.setMedicamentos(medicamento);
        insumo= insumoRepository.saveAndFlush(insumo);
        if(insumo == null){
            return new ResponseEntity<>(new Message("El insumo no se pudo actualizar",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("El registro ha sido actualizado correctamente");
        return new ResponseEntity<>(new Message(insumo,"El insumo se Actualizo correctamente",TypesResponse.SUCCESS),HttpStatus.OK);

    }
    public ResponseEntity<Message> changeStatus(InsumoDto dto) {
        Optional<Insumo> insumoOptional = insumoRepository.findById(dto.getId());
        if(!insumoOptional.isPresent()){
            return new ResponseEntity<>(new Message("La pregunta no existe",TypesResponse.ERROR),HttpStatus.NOT_FOUND);
        }
        Insumo insumo = insumoOptional.get();
        insumo.setStatus(!insumo.isStatus());
        insumo = insumoRepository.saveAndFlush(insumo);
        if(insumo == null){
            return new ResponseEntity<>(new Message("El estado del insumo no se actualizó",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(insumo,"El estado del insumo se actualizó correctamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }





}
