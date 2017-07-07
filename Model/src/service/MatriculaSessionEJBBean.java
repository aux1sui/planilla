package service;

import entities.Curso;
import entities.Estudiante;
import entities.Matricula;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "MatriculaSessionEJB", mappedName = "planilla-Model-MatriculaSessionEJB")
public class MatriculaSessionEJBBean implements MatriculaSessionEJBLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public void persistMatriculas(List<Estudiante> listaEstudiante, Curso curso) {
        for (Estudiante estudiante : listaEstudiante) {
            Matricula matricula = new Matricula();
            matricula.setEstudiante(estudiante);
            matricula.setCurso(curso);
            em.persist(matricula);
        }
    }

    public Matricula mergeMatricula(Matricula matricula) {
        return em.merge(matricula);
    }

    public void removeMatricula(Matricula matricula) {
        matricula = em.find(Matricula.class, matricula.getId());
        em.remove(matricula);
    }

    public List<Matricula> getMatricula(Estudiante estudiante) throws Exception {
        String ejbql = "select o from Matricula o where o.estudiante=:estudiante";
        Query query = em.createQuery(ejbql);
        query.setParameter("estudiante", estudiante);
        List<Matricula> matriculaList = query.getResultList();
        if (matriculaList.isEmpty()) {
            throw new Exception("No hay cursos matriculados");
        }
        return matriculaList;
    }

    public List<Estudiante> getEstudiantesMatricula(Curso curso) throws Exception {
        String ejbql = "select o.estudiante from Matricula o where o.curso=:curso";
        Query query = em.createQuery(ejbql);
        query.setParameter("curso", curso);
        List<Estudiante> estudianteList = query.getResultList();
        if (estudianteList.isEmpty()) {
            throw new Exception("No hay estudiantes matriculados");
        }
        return estudianteList;
    }

    public Matricula persistMatricula(Matricula matricula) {
        em.persist(matricula);
        return matricula;
    }
}
