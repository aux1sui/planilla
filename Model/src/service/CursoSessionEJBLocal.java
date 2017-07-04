package service;

import entities.Curso;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CursoSessionEJBLocal {
    Curso persistCurso(Curso curso);

    Curso mergeCurso(Curso curso);

    void removeCurso(Curso curso);
    
    List<Curso> getCursoByCriteria(String paramNombre, String paramCodigo, Curso.Modalidad paramModalidad) throws Exception ;
}
