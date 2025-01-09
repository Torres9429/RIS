package utez.edu.mx.systemris.enfermera.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import utez.edu.mx.systemris.turno.model.Turno;

@Entity
@Table(name = "enfermeras")
public class Enfermera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", columnDefinition = "VARCHAR(70)")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Column(name = "apellidos", columnDefinition = "VARCHAR(70)")
    private String apellidos;

    @NotBlank(message = "El área de trabajo es obligatoria")
    @Column(name = "areaTrabajo", columnDefinition = "VARCHAR(70)")
    private String areaTrabajo;

    @NotNull(message = "El tiempo de experiencia es obligatorio")
    @Column(name = "aniosExperiencia", columnDefinition = "INT")
    private int aniosExperiencia;

    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "turno_id")
    private Turno turno;

    public Enfermera() {
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
}
