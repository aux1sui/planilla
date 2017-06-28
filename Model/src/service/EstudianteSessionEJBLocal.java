package service;

import entities.Estudiante;

import java.util.List;

import javax.ejb.Local;

@Local
public interface EstudianteSessionEJBLocal {
    Estudiante persistEstudiante(Estudiante estudiante);

    Estudiante mergeEstudiante(Estudiante estudiante);

    void removeEstudiante(Estudiante estudiante);
    
    List<Estudiante> getEstudianteByCriteria(Estudiante.TipoIdentificacion paramTipoIdentificacion,String paramNumeroIdentificacion, String paramNombres, String paramApellidos) throws Exception;
}
