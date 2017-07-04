package service;

import entities.Matricula;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "MatriculaSessionEJB", mappedName = "planilla-Model-MatriculaSessionEJB")
public class MatriculaSessionEJBBean implements MatriculaSessionEJBLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public MatriculaSessionEJBBean() {
    }

    public Matricula persistMatricula(Matricula matricula) {
        em.persist(matricula);
        return matricula;
    }

    public Matricula mergeMatricula(Matricula matricula) {
        return em.merge(matricula);
    }

    public void removeMatricula(Matricula matricula) {
        matricula = em.find(Matricula.class, matricula.getId());
        em.remove(matricula);
    }
}
