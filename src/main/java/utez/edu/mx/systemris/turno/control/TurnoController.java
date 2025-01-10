package utez.edu.mx.systemris.turno.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.systemris.turno.model.TurnoDto;
import utez.edu.mx.systemris.utils.Message;

@RestController
@RequestMapping("/turnos")
@CrossOrigin(origins = {"*"},methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class TurnoController {
    private final TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> findAll() {
        return turnoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        return turnoService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody TurnoDto turnoDto) {
        return turnoService.save(turnoDto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody TurnoDto turnoDto) {
        return turnoService.update(turnoDto);
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<Message> changeStatus(@RequestBody TurnoDto turnoDto) {
        return turnoService.changeStatus(turnoDto);
    }

    @GetMapping("/actives")
    public ResponseEntity<Message> findActives() {
        return turnoService.findActives();
    }
}
