package entities;

import java.io.Serializable;


import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    public enum Modalidad{
        VIRTUAL,
        PRESENCIAL;
    }
    private static final long serialVersionUID = 8021503866785009388L;
    
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Certificacion_Gen")
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
    @Column(nullable = false, length = 20)
    private Curso.Modalidad modalidad;
    
    @NotNull
    @Column(nullable = false, length = 500)
    private String nombre;
    
    @NotNull
    @OneToMany(mappedBy = "curso", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Matricula> matriculaList1;


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

    public List<Matricula> getMatriculaList1() {
        return matriculaList1;
    }

    public void setMatriculaList1(List<Matricula> matriculaList1) {
        this.matriculaList1 = matriculaList1;
    }

    public Matricula addMatricula(Matricula matricula) {
        getMatriculaList1().add(matricula);
        matricula.setCurso(this);
        return matricula;
    }

    public Matricula removeMatricula(Matricula matricula) {
        getMatriculaList1().remove(matricula);
        matricula.setCurso(null);
        return matricula;
    }
}
