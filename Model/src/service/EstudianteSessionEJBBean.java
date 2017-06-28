package service;

import entities.Estudiante;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "EstudianteSessionEJB", mappedName = "planilla-Model-EstudianteSessionEJB")
public class EstudianteSessionEJBBean implements EstudianteSessionEJBLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public EstudianteSessionEJBBean() {
    }

    public Estudiante persistEstudiante(Estudiante estudiante) {
        em.persist(estudiante);
        return estudiante;
    }

    public Estudiante mergeEstudiante(Estudiante estudiante) {
        return em.merge(estudiante);
    }

    public void removeEstudiante(Estudiante estudiante) {
        estudiante = em.find(Estudiante.class, estudiante.getId());
        em.remove(estudiante);
    }
    public List<Estudiante> getEstudianteByCriteria(Estudiante.TipoIdentificacion paramTipoIdentificacion,String paramNumeroIdentificacion, String paramNombres, String paramApellidos) throws Exception {
        StringBuilder ejbql= new StringBuilder();
        ejbql.append("select o from Estudiante o where o.id=o.id");
        if(paramTipoIdentificacion!=null){
            ejbql.append(" and o.tipoIdentificacion=:tipoIdentificacion");
        }
        
        if(paramNumeroIdentificacion!=null){
            ejbql.append(" and UPPER(o.numeroIdentificacion) LIKE :numeroIdentificacion");
        }
        
        if(paramNombres!=null){
            ejbql.append(" and UPPER(o.nombres) LIKE :nombres");    
        }
        
        if(paramApellidos!=null){
            ejbql.append(" and UPPER(o.apellidos) LIKE :apellidos");    
        }
        ejbql.append(" order by o.apellidos, o.nombres");
        
        Query query = this.em.createQuery(ejbql.toString());
        
        if(paramTipoIdentificacion!=null){
            query.setParameter("tipoIdentificacion", paramTipoIdentificacion);    
        }
        
        if(paramNumeroIdentificacion!=null){
            query.setParameter("numeroIdentificacion","%"+paramNumeroIdentificacion.trim().toUpperCase()+"%");    
        }
        
        if(paramNombres!=null){
            query.setParameter("nombres", "%"+ paramNombres.toUpperCase().trim()+"%");    
        }
        
        if(paramApellidos!=null){
            query.setParameter("apellidos", "%"+paramApellidos.toUpperCase().trim()+"%");    
        }
        List<Estudiante> estudianteList=query.getResultList();
        if(estudianteList.isEmpty()){
            throw new Exception("No se han encontrado estudiantes según los parámetros de búsqueda establecidos ");
        }
        
        return estudianteList;
    }
}
