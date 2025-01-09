package utez.edu.mx.systemris.enfermera.control;

import utez.edu.mx.systemris.enfermera.model.EnfermeraDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.systemris.utils.Message;

@RestController
@RequestMapping("/enfermeras")
@CrossOrigin(origins = {"*"},methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EnfermeraController {
    private final EnfermeraService enfermeraService;

    @Autowired
    public EnfermeraController(EnfermeraService enfermeraService) {
        this.enfermeraService = enfermeraService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> findAll() {
        return enfermeraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        return enfermeraService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody EnfermeraDto enfermeraDto) {
        return enfermeraService.save(enfermeraDto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody EnfermeraDto enfermeraDto) {
        return enfermeraService.update(enfermeraDto);
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<Message> changeStatus(@RequestBody EnfermeraDto enfermeraDto) {
        return enfermeraService.changeStatus(enfermeraDto);
    }
}
