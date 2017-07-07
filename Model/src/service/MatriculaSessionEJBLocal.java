package service;

import entities.Curso;
import entities.Estudiante;
import entities.Matricula;

import java.util.List;

import javax.ejb.Local;

@Local
public interface MatriculaSessionEJBLocal {
    Matricula persistMatricula(Matricula matricula);

    Matricula mergeMatricula(Matricula matricula);

    void removeMatricula(Matricula matricula);
    
    List<Matricula> getMatricula(Estudiante estudiante)throws Exception;
    
    List<Estudiante> getEstudiantesMatricula(Curso curso)throws Exception;
}
