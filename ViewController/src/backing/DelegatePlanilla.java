package backing;

import entities.Curso;
import entities.Estudiante;

import entities.Matricula;

import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import service.CursoSessionEJBBean;
import service.CursoSessionEJBLocal;
import service.EstudianteSessionEJBBean;
import service.EstudianteSessionEJBLocal;
import service.MatriculaSessionEJBLocal;

@ManagedBean(name = "planillaDelegate")
@ApplicationScoped
public class DelegatePlanilla {
    private @EJB
    EstudianteSessionEJBLocal estudianteSessionEJBLocal;
    private @EJB
    CursoSessionEJBLocal cursoSessionEJBLocal; 
    private @EJB
    MatriculaSessionEJBLocal matriculaSessionEJBLocal;
    
    Estudiante persistEstudiante(Estudiante estudiante){
        return this.estudianteSessionEJBLocal.persistEstudiante(estudiante);
    }
    

    Estudiante mergeEstudiante(Estudiante estudiante){
        return this.mergeEstudiante(estudiante);
    }

    void removeEstudiante(Estudiante estudiante){
        this.removeEstudiante(estudiante);
    }
    
    List<Estudiante> getEstudianteByCriteria(Estudiante.TipoIdentificacion paramTipoIdentificacion, String paramNumeroIdentificacion, String paramNombres, String paramApellidos) throws Exception{
        return this.estudianteSessionEJBLocal.getEstudianteByCriteria(paramTipoIdentificacion, paramNumeroIdentificacion, paramNombres, paramApellidos);
    }
    Curso persistCurso(Curso curso){
        return this.cursoSessionEJBLocal.persistCurso(curso);
    }

    Curso mergeCurso(Curso curso){
        return this.mergeCurso(curso);
    }

    void removeCurso(Curso curso){
        this.removeCurso(curso);
    }
    
    List<Curso> getCursoByCriteria(String paramNombre, String paramCodigo, Curso.Modalidad paramModalidad) throws Exception {
        return this.cursoSessionEJBLocal.getCursoByCriteria(paramNombre, paramCodigo, paramModalidad);
    }
    Matricula persistMatricula(Matricula matricula){
        return this.matriculaSessionEJBLocal.persistMatricula(matricula);
    }
    List<Matricula> getMatricula(Estudiante estudiante)throws Exception{
        return this.matriculaSessionEJBLocal.getMatricula(estudiante);
    }

    Matricula mergeMatricula(Matricula matricula){
        return this.mergeMatricula(matricula);
    }

    void removeMatricula(Matricula matricula){
        this.removeMatricula(matricula);
    }
    public List<Estudiante> getEstudiantesMatricula(Curso curso)throws Exception{
        return this.matriculaSessionEJBLocal.getEstudiantesMatricula(curso);
    }

    
    
}
