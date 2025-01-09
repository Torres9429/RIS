package utez.edu.mx.systemris.doctor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import utez.edu.mx.systemris.turno.model.Turno;

@Entity
@Table(name = "doctores")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", columnDefinition = "VARCHAR(70)")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Column(name = "apellidos", columnDefinition = "VARCHAR(70)")
    private String apellidos;

    @NotBlank(message = "La cédula obligatoria")
    @Column(name = "cedula", columnDefinition = "VARCHAR(30)")
    private String cedula;

    @NotBlank(message = "La especialidad es obligatoria")
    @Column(name = "especialidad", columnDefinition = "VARCHAR(50)")
    private String especialidad;

    @NotBlank(message = "El consultorio asignado es obligatorio")
    @Column(name = "consultorio", columnDefinition = "VARCHAR(50)")
    private String consultorio;

    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "turno_id")
    private Turno turno;

    public Doctor() {
    }

    public Doctor(Long id, String nombre, String apellidos, String cedula, String especialidad, String consultorio, boolean status) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.consultorio = consultorio;
        this.status = true;
    }

    public Doctor(String nombre, String apellidos, String cedula, String especialidad, String consultorio, boolean status) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.especialidad = especialidad;
        this.consultorio = consultorio;
        this.status = status;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
