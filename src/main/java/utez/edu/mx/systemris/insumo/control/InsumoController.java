package utez.edu.mx.systemris.insumo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.systemris.insumo.model.InsumoDto;
import utez.edu.mx.systemris.utils.Message;

@RestController
@RequestMapping("/insumos")
@CrossOrigin(origins = {"*"},methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class InsumoController {
    private  final InsumoService insumoService;
    @Autowired
    public InsumoController(InsumoService insumoService) {
        this.insumoService = insumoService;
    }
    @GetMapping("/all")
    public ResponseEntity<Message> findAllInsumo(){
        return insumoService.findAll();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Message> findInsumoById(@PathVariable long id){
        return insumoService.findById(id);
    }
    @GetMapping("/activos")
    public ResponseEntity<Message> findActivos(){
        return insumoService.findActives();
    }
    @PostMapping("/save")
    public ResponseEntity<Message> saveInsumo(@RequestBody InsumoDto insumoDto){
        return insumoService.saveInsumo(insumoDto);
    }
    @PutMapping("/update")
    public ResponseEntity<Message> updateInsumo(@RequestBody InsumoDto insumoDto){
        return insumoService.updateInsumo(insumoDto);
    }
    @PutMapping("/change-status")
    public ResponseEntity<Message> updateStatus(@RequestBody InsumoDto insumoDto){
        return insumoService.updateInsumo(insumoDto);
    }
}
