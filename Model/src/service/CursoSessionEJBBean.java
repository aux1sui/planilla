package service;

import entities.Curso;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "CursoSessionEJB", mappedName = "planilla-Model-CursoSessionEJB")
public class CursoSessionEJBBean implements CursoSessionEJBLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public CursoSessionEJBBean() {
    }

    public Curso persistCurso(Curso curso) {
        em.persist(curso);
        return curso;
    }

    public Curso mergeCurso(Curso curso) {
        return em.merge(curso);
    }

    public void removeCurso(Curso curso) {
        curso = em.find(Curso.class, curso.getId());
        em.remove(curso);
    }
    public List<Curso> getCursoByCriteria(String paramNombre, String paramCodigo, Curso.Modalidad paramModalidad) throws Exception {
        
        StringBuilder ejbql = new StringBuilder();
        ejbql.append("select o from Curso o where o.id=o.id");
        if(paramNombre!=null){
            ejbql.append(" and UPPER(o.nombre) LIKE :nombre");
        }
        if(paramCodigo!=null){
            ejbql.append(" and o.codigo like :codigo");
        }
        if(paramModalidad!=null){
            ejbql.append(" and o.modalidad=:modalidad");
        }
        ejbql.append(" order by o.nombre");
        
        Query query= em.createQuery(ejbql.toString());
        if(paramNombre!=null){
            query.setParameter("nombre", "%"+paramNombre.trim().toUpperCase()+"%");
        }
        if(paramCodigo!=null){
            query.setParameter("codigo", "%"+paramCodigo.trim()+"%");
        }
        if(paramModalidad!=null){
            query.setParameter("modalidad", paramModalidad);
        }
        
        List<Curso> cursoList = query.getResultList();
        if(cursoList.isEmpty()){
            throw new Exception("No se han encontrado cursos según los parámetros de búsqueda establecidos ");   
        }
        return cursoList;
    }
}
