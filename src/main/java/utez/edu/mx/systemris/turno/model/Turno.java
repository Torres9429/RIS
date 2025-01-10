package utez.edu.mx.systemris.turno.model;

import jakarta.persistence.*;
import utez.edu.mx.systemris.doctor.model.Doctor;
import utez.edu.mx.systemris.enfermera.model.Enfermera;

import java.util.List;

@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "turno", columnDefinition = "VARCHAR(20)")
    private String turno;

    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctores;

    @ManyToOne
    @JoinColumn(name = "enfermera_id")
    private Enfermera enfermeras;

    public Turno() {
    }

    public Turno(Long id, String turno, boolean status) {
        this.id = id;
        this.turno = turno;
        this.status = status;
    }

    public Turno(String turno, boolean status) {
        this.turno = turno;
        this.status = status;
    }

    public Turno(String turno) {
        this.turno = turno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Doctor getDoctores() {
        return doctores;
    }

    public void setDoctores(Doctor doctores) {
        this.doctores = doctores;
    }

    public Enfermera getEnfermeras() {
        return enfermeras;
    }

    public void setEnfermeras(Enfermera enfermeras) {
        this.enfermeras = enfermeras;
    }
}
