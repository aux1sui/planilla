package entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name="Seguimiento_Gen", sequenceName="SEQ_SEGUIMIENTO",allocationSize=1)
public class Seguimiento implements Serializable {
    private static final long serialVersionUID = -8152040666960462546L;
    
    @NotNull
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Seguimiento_Gen")
    @Column(nullable = false)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    private Long nota;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_MATRICULA")
    private Matricula matricula1;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }

    public Matricula getMatricula1() {
        return matricula1;
    }

    public void setMatricula1(Matricula matricula1) {
        this.matricula1 = matricula1;
    }
}
