package utez.edu.mx.systemris.facturacion.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.systemris.facturacion.model.FacturaDto;
import utez.edu.mx.systemris.utils.Message;

@RestController
@RequestMapping("/facturas")
@CrossOrigin(origins = {"*"},methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class FacturaController {
    private final FacturaService facturaService;
    @Autowired
    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }
    @GetMapping("/all")
    public ResponseEntity<Message> findAllFacturas(){
        return facturaService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id){
        return facturaService.findById(id);
    }
    @PostMapping("/save")
    public ResponseEntity<Message> saveFactura(@RequestBody FacturaDto facturaDto){
        return facturaService.saveFactura(facturaDto);
    }
}
