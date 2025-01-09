package utez.edu.mx.systemris.doctor.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.systemris.doctor.model.DoctorDto;
import utez.edu.mx.systemris.utils.Message;

@RestController
@RequestMapping("/doctores")
@CrossOrigin(origins = {"*"},methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        return doctorService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@RequestBody DoctorDto doctorDto) {
        return doctorService.save(doctorDto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@RequestBody DoctorDto doctorDto) {
        return doctorService.update(doctorDto);
    }

    @PutMapping("/changeStatus")
    public ResponseEntity<Message> changeStatus(@RequestBody DoctorDto doctorDto) {
        return doctorService.changeStatus(doctorDto);
    }
}
