package utez.edu.mx.systemris.medicamento.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.systemris.medicamento.model.MedicamentoDto;
import utez.edu.mx.systemris.utils.Message;

@RestController
@RequestMapping("/medicamentos")
@CrossOrigin(origins = {"*"},methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class MedicamentoController {
    private  final MedicamentoService medicamentoService;
    @Autowired
    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }
    @GetMapping("/all")
    public ResponseEntity<Message> findAllInsumo(){
        return medicamentoService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Message> findInsumoById(@PathVariable long id){
        return medicamentoService.findById(id);
    }
    @PostMapping("/save")
    public ResponseEntity<Message> saveMedicamento(@RequestBody MedicamentoDto medicamentoDto){
        return medicamentoService.saveMedicamento(medicamentoDto);
    }
    @PutMapping("/update")
    public ResponseEntity<Message> updateInsumo(@RequestBody MedicamentoDto medicamentoDto){
        return medicamentoService.updateMedicamento(medicamentoDto);
    }
    @PutMapping("/change-status")
    public ResponseEntity<Message> updateStatus(@RequestBody MedicamentoDto medicamentoDto){
        return medicamentoService.updateMedicamento(medicamentoDto);
    }
}
