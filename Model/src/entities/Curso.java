package entities;

import java.io.Serializable;


import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name="Curso_Gen", sequenceName="SEQ_CURSO",allocationSize=1)
public class Curso implements Serializable {
    private static final long serialVersionUID = 8021503866785009388L;
    public enum Modalidad{
        VIRTUAL,
        PRESENCIAL;
    }
    
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Curso_Gen")
    @Column(nullable = false)
    private Long id;
     
    @NotNull
    @Column(nullable = false)
    private Long cantidadNota;
    
    @NotNull
    @Column(nullable = false, length = 20)
    private String codigo;
    
    @NotNull
    @Column(nullable = false)
    private Long cupos;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaFin;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaInicio;
    
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Curso.Modalidad modalidad;
    
    @NotNull
    @Column(nullable = false, length = 500)
    private String nombre;
    
    public Long getCantidadNota() {
        return cantidadNota;
    }

    public void setCantidadNota(Long cantidadNota) {
        this.cantidadNota = cantidadNota;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getCupos() {
        return cupos;
    }

    public void setCupos(Long cupos) {
        this.cupos = cupos;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setModalidad(Curso.Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public Curso.Modalidad getModalidad() {
        return modalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
