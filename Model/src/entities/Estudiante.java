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
@SequenceGenerator(name="Estudiante_Gen", sequenceName="SEQ_ESTUDIANTE",allocationSize=1)
public class Estudiante implements Serializable {
    public enum TipoIdentificacion{
        TI("Tarjeta de identidad"),
        CE("Cédula de extranjeria"),
        CC("Cédula de ciudadania");
        public String label;
        TipoIdentificacion(String label){
            this.label=label;
        }
        public String getLabel() {
            return label;
        }
    
        
    }
    public enum Genero{
        M("Masculino"),
        F("Femenino");
        
        public String label;

        public String getLabel() {
            return label;
        }
        
        Genero(String label){
                this.label=label;
            }

    }
    public enum GrupoSanguineo{
        B_POSITIVO("B+"),
        B_NEGATIVO("B-"),
        A_POSITIVO("A+"),
        A_NEGATIVO("A-"),
        O_POSITIVO("O+"),
        O_NEGATIVO("O-");
        public String label;
        GrupoSanguineo(String label){
            this.label=label;
        }

        public String getLabel() {
            return label;
        }
    }
    
    private static final long serialVersionUID = 1850908992559465813L;
    
    @NotNull
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "Estudiante_Gen")
    @Column(nullable = false)
    private Long id;
    
    
    @NotNull
    @Column(nullable = false, length = 100)
    private String apellidos;
    
    @NotNull
    @Column(nullable = false, length = 100)
    private String correo;
    
    @NotNull
    @Column(nullable = false, length = 100)
    private String direccion;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaNacimiento;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Estudiante.Genero genero;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Estudiante.GrupoSanguineo grupoSanguineo;
    
    
    @NotNull
    @Column(nullable = false, length = 100)
    private String nombres;
    
    @NotNull
    @Column(nullable = false, length = 20)
    private String numeroIdentificacion;
    
    @NotNull
    @Column(nullable = false, length = 20)
    private String telefono;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Estudiante.TipoIdentificacion tipoIdentificacion;
    
    /* @NotNull
    @OneToMany(mappedBy = "estudiante", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Matricula> matriculaList; */


    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setGenero(Estudiante.Genero genero) {
        this.genero = genero;
    }

    public Estudiante.Genero getGenero() {
        return genero;
    }

    public void setGrupoSanguineo(Estudiante.GrupoSanguineo grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public Estudiante.GrupoSanguineo getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setTipoIdentificacion(Estudiante.TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Estudiante.TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }



    /* public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    } */

    /*  public Matricula addMatricula(Matricula matricula) {
        getMatriculaList().add(matricula);
        matricula.setEstudiante(this);
        return matricula;
    }

    public Matricula removeMatricula(Matricula matricula) {
        getMatriculaList().remove(matricula);
        matricula.setEstudiante(null);
        return matricula;
    } */
}
