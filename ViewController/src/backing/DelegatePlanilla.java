package backing;

import entities.Curso;
import entities.Estudiante;

import java.util.List;

import javax.ejb.EJB;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import service.CursoSessionEJBBean;
import service.CursoSessionEJBLocal;
import service.EstudianteSessionEJBBean;
import service.EstudianteSessionEJBLocal;

@ManagedBean(name = "planillaDelegate")
@ApplicationScoped
public class DelegatePlanilla {
    private @EJB
    EstudianteSessionEJBLocal estudianteSessionEJBLocal;
    CursoSessionEJBLocal cursoSessionEJBLocal; 
    
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

    
    
}
