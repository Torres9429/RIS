package utez.edu.mx.systemris.doctor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import utez.edu.mx.systemris.turno.model.Turno;

import java.util.List;

@Entity
@Table(name = "doctores")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(70)")
    private String nombre;

    @Column(name = "apellidos", columnDefinition = "VARCHAR(70)")
    private String apellidos;

    @Column(name = "cedula", columnDefinition = "VARCHAR(30)")
    private String cedula;

    @Column(name = "especialidad", columnDefinition = "VARCHAR(50)")
    private String especialidad;

    @Column(name = "consultorio", columnDefinition = "VARCHAR(50)")
    private String consultorio;

    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;


    @OneToMany(mappedBy = "doctores")
    @JsonIgnore
    private List<Turno> turnos;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
