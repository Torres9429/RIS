package utez.edu.mx.systemris.enfermera.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import utez.edu.mx.systemris.turno.model.Turno;

import java.util.List;

@Entity
@Table(name = "enfermeras")
public class Enfermera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(70)")
    private String nombre;

    @Column(name = "apellidos", columnDefinition = "VARCHAR(70)")
    private String apellidos;

    @Column(name = "areaTrabajo", columnDefinition = "VARCHAR(70)")
    private String areaTrabajo;

    @Column(name = "aniosExperiencia", columnDefinition = "INT")
    private int aniosExperiencia;

    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;
    @Column(name = "turno",columnDefinition = "VARCHAR(25)")
    private String turno;
    @OneToMany(mappedBy = "enfermeras")
    @JsonIgnore
    private List<Turno> turnos;

    public Enfermera() {
    }

    public Enfermera(String nombre, String apellidos, String areaTrabajo, int aniosExperiencia, boolean status, String turno) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.areaTrabajo = areaTrabajo;
        this.aniosExperiencia = aniosExperiencia;
        this.status = true;
        this.turno = turno;
    }

    public Enfermera(String nombre, String apellidos, String areaTrabajo, int aniosExperiencia, String turno) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.areaTrabajo = areaTrabajo;
        this.aniosExperiencia = aniosExperiencia;
        this.turno = turno;
    }

    public Enfermera(Long id, String nombre, String apellidos, String areaTrabajo, int aniosExperiencia, boolean status) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.areaTrabajo = areaTrabajo;
        this.aniosExperiencia = aniosExperiencia;
        this.status = status;
    }

    public Enfermera(String nombre, String apellidos, String areaTrabajo, int aniosExperiencia, boolean status) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.areaTrabajo = areaTrabajo;
        this.aniosExperiencia = aniosExperiencia;
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

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

}
