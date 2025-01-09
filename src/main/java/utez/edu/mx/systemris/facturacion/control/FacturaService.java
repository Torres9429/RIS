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
    @Autowired
    public FacturaService(FacturaRepository facturaRepository){
        this.facturaRepository = facturaRepository;
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
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> saveFactura(FacturaDto facturaDto){
        if (facturaDto.getConcepto().length() > 50){
            return new ResponseEntity<>(new Message(facturaDto,"El concepto sobrepasa el numero de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        Doctor doctor = doctorRepository.findById(facturaDto.getDoctorId()).orElse(null)
        Factura factura = new Factura(facturaDto.getFecha_factura(),facturaDto.getConcepto(), facturaDto.getTotal());
        factura.setDoctor();
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> updateFactura(FacturaDto facturaDto){
        Optional<Factura> facturaOptional = facturaRepository.findById(facturaDto.getId());
        if (!facturaOptional.isPresent()){
            return new ResponseEntity<>(new Message("La factura no fue encontrada",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        if (facturaDto.getConcepto().length() > 50){
            return new ResponseEntity<>(new Message(facturaDto,"El concepto sobrepasa el numero de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        Factura factura = facturaOptional.get();
        factura.setFecha_factura();
    }

}
