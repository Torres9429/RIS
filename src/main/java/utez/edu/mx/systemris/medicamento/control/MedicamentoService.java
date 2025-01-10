package utez.edu.mx.systemris.medicamento.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.systemris.medicamento.model.Medicamento;
import utez.edu.mx.systemris.medicamento.model.MedicamentoDto;
import utez.edu.mx.systemris.medicamento.model.MedicamentoRepository;
import utez.edu.mx.systemris.utils.Message;
import utez.edu.mx.systemris.utils.TypesResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MedicamentoService {
    Logger logger = LoggerFactory.getLogger(MedicamentoService.class);
    private final MedicamentoRepository medicamentoRepository;

    @Autowired
    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Medicamento> insumos = medicamentoRepository.findAll();
        logger.info("Búsqueda de medicamento exitoso");
        return new ResponseEntity<>(new Message(insumos, "Lista de medicamentos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        Optional<Medicamento> insumo = medicamentoRepository.findById(id);
        if (insumo.isPresent()) {
            logger.info("Busqueda de Medicamento exitoso");
            return new ResponseEntity<>(new Message(insumo.get(), "Medicamento encontrado", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            logger.info("medicamento no encontrado");
            return new ResponseEntity<>(new Message(null, "Medicamento no encontrado", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);

        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> saveMedicamento(MedicamentoDto medicamentoDto) {
        if(medicamentoDto.getMedicamento().length() > 30){
            return  new ResponseEntity<>(new Message(medicamentoDto, "El nombre del medicamento excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if(medicamentoDto.getDescripcion().length() > 50){
            return  new ResponseEntity<>(new Message(medicamentoDto, "La descripción excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Medicamento medicamento = new Medicamento(medicamentoDto.getMedicamento(), medicamentoDto.getPrecio(), medicamentoDto.getDescripcion());
        medicamento = medicamentoRepository.saveAndFlush(medicamento);
        if (medicamento == null) {
            return new ResponseEntity<>(new Message("El medicamento no se registró", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("El registro ha sido realizado correctamente");
        return new ResponseEntity<>(new Message(medicamento, "El medicamento se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);

    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> updateMedicamento(MedicamentoDto medicamentoDto) {
        Optional<Medicamento> insumoOptional = medicamentoRepository.findById(medicamentoDto.getId());
        Medicamento insumo = insumoOptional.get();
        if (!insumoOptional.isPresent()) {
            return new ResponseEntity<>(new Message("el medicamento no fue encontrado", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);

        }
        if(medicamentoDto.getMedicamento().length() > 30){
            return  new ResponseEntity<>(new Message(medicamentoDto, "El nombre del medicamento excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if(medicamentoDto.getDescripcion().length() > 50){
            return  new ResponseEntity<>(new Message(medicamentoDto, "La descripción excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        insumo.setMedicamento(medicamentoDto.getMedicamento());
        insumo.setPrecio(medicamentoDto.getPrecio());
        insumo.setDescripcion(medicamentoDto.getDescripcion());

        insumo = medicamentoRepository.saveAndFlush(insumo);

        if (insumo.getId() == null) {
            return new ResponseEntity<>(new Message("El medicamento no se pudo actualizar", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("El registro ha sido actualizado correctamente");
        return new ResponseEntity<>(new Message(insumo, "El medicamento se Actualizo correctamente", TypesResponse.SUCCESS), HttpStatus.OK);

    }
}

