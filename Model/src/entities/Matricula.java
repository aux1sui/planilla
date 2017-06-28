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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="Matricula_Gen", sequenceName="SEQ_MATRICULA",allocationSize=1)
public class Matricula implements Serializable {
    private static final long serialVersionUID = 6465281025019295294L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Matricula_Gen")
    @Column(nullable = false)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaRegistro;
    
    
    @ManyToOne
    @JoinColumn(name = "ID_ESTUDIANTE")
    private Estudiante estudiante;
    
    @OneToMany(mappedBy = "matricula", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Certificacion> certificacionList;
    
    @OneToMany(mappedBy = "matricula1", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Seguimiento> seguimientoList;
    
    @ManyToOne
    @JoinColumn(name = "ID_CURSO")
    private Curso curso;



    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Certificacion> getCertificacionList() {
        return certificacionList;
    }

    public void setCertificacionList(List<Certificacion> certificacionList) {
        this.certificacionList = certificacionList;
    }

    public Certificacion addCertificacion(Certificacion certificacion) {
        getCertificacionList().add(certificacion);
        certificacion.setMatricula(this);
        return certificacion;
    }

    public Certificacion removeCertificacion(Certificacion certificacion) {
        getCertificacionList().remove(certificacion);
        certificacion.setMatricula(null);
        return certificacion;
    }

    public List<Seguimiento> getSeguimientoList() {
        return seguimientoList;
    }

    public void setSeguimientoList(List<Seguimiento> seguimientoList) {
        this.seguimientoList = seguimientoList;
    }

    public Seguimiento addSeguimiento(Seguimiento seguimiento) {
        getSeguimientoList().add(seguimiento);
        seguimiento.setMatricula1(this);
        return seguimiento;
    }

    public Seguimiento removeSeguimiento(Seguimiento seguimiento) {
        getSeguimientoList().remove(seguimiento);
        seguimiento.setMatricula1(null);
        return seguimiento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
