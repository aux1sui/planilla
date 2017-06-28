package entities;

import java.io.Serializable;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name="Certificacion_Gen", sequenceName="SEQ_CERTIFICACION", allocationSize=1)
public class Certificacion implements Serializable {
    private static final long serialVersionUID = 3515551936024421799L;
    
    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Certificacion_Gen")
    @Column(nullable = false)
    private Long id;
    
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    
    
    @NotNull
    @Column(nullable = false)
    private Long notaFinal;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_MATRICULA")
    private Matricula matricula;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Long notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }
}
