package utez.edu.mx.systemris.facturacion.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.systemris.facturacion.model.Factura;
import utez.edu.mx.systemris.facturacion.model.FacturaDto;
import utez.edu.mx.systemris.facturacion.model.FacturaRepository;
import utez.edu.mx.systemris.medicamento.model.Medicamento;
import utez.edu.mx.systemris.medicamento.model.MedicamentoRepository;
import utez.edu.mx.systemris.utils.Message;
import utez.edu.mx.systemris.utils.TypesResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FacturaService {
    private final static Logger logger = LoggerFactory.getLogger(FacturaService.class);
    private final FacturaRepository facturaRepository;
    private final MedicamentoRepository medicamentoRepository;
    @Autowired
    public FacturaService(FacturaRepository facturaRepository, MedicamentoRepository medicamentoRepository) {
        this.facturaRepository = facturaRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        List<Factura> facturas = facturaRepository.findAll();
        logger.info("Búsqueda de facturas exitosa");
        return new ResponseEntity<>(new Message(facturas,"Lista de facturas", TypesResponse.SUCCESS), HttpStatus.OK);
    }
    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id){
        Optional<Factura> factura = facturaRepository.findById(id);
        if (factura.isPresent()){
            logger.info("Factura encontrada");
            return new ResponseEntity<>(new Message(factura.get(),"Factura encontrada",TypesResponse.SUCCESS), HttpStatus.OK);
        }else {
            logger.info("Factura no encontrada");
            return new ResponseEntity<>(new Message(null,"Factura no encontrada",TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
    }
    @Transactional(rollbackFor = {SQLException.class}) public ResponseEntity<Message> saveFactura(FacturaDto facturaDto) {
        if (facturaDto.getConcepto().length() > 50) {
            return new ResponseEntity<>(new Message(facturaDto, "El concepto sobrepasa el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        Medicamento medicamento = medicamentoRepository.findById(facturaDto.getMedicamentoId()).orElse(null);
        if (medicamento == null) {
            return new ResponseEntity<>(new Message(null, "Medicamento no encontrado", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        //Factura factura = new Factura(facturaDto.getConcepto(),facturaDto.getTotal(), List.of(medicamento));
        Factura factura = new Factura(facturaDto.getConcepto(),facturaDto.getTotal(),medicamento);
        facturaRepository.save(factura);
        logger.info("Factura registrada correcatamente");
        return new ResponseEntity<>(new Message(factura, "Factura guardada correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
