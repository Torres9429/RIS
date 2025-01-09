package utez.edu.mx.systemris.facturacion.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_factura", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_factura;
    @Column(name = "concepto", columnDefinition = "VARCHAR(50)")
    private String concepto;
    @Column(name = "total", columnDefinition = "DOUBLE")
    private double total;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctores;
    @ManyToOne
    @JoinColumn(name = "enfermera_id")
    private Enfermera enfermeras;

    public Factura() {
    }

    public Factura(Date fecha_factura, String concepto, double total, Doctor doctores, Enfermera enfermeras) {
        this.fecha_factura = fecha_factura;
        this.concepto = concepto;
        this.total = total;
        this.doctores = doctores;
        this.enfermeras = enfermeras;
    }

    public Factura(Long id, Date fecha_factura, String concepto, double total, Doctor doctor, Enfermera enfermera) {
        this.id = id;
        this.fecha_factura = fecha_factura;
        this.concepto = concepto;
        this.total = total;
        this.doctor = doctor;
        this.enfermera = enfermera;
    }

    public Factura(Long id, Date fecha_factura, String concepto, double total, Doctor doctor) {
        this.id = id;
        this.fecha_factura = fecha_factura;
        this.concepto = concepto;
        this.total = total;
        this.doctor = doctor;
    }

    public Factura(Long id, Date fecha_factura, String concepto, double total, Enfermera enfermera) {
        this.id = id;
        this.fecha_factura = fecha_factura;
        this.concepto = concepto;
        this.total = total;
        this.enfermera = enfermera;
    }

    public Factura(Date fecha_factura, String concepto, double total) {
        this.fecha_factura = fecha_factura;
        this.concepto = concepto;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Enfermera getEnfermera() {
        return enfermera;
    }

    public void setEnfermera(Enfermera enfermera) {
        this.enfermera = enfermera;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
