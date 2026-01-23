package ec.edu.ups.ppw.GestorProyectos.DAO;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.modelo.Proyecto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ProyectoDAO {

    @PersistenceContext(unitName = "GestorProyectosPersistenceUnit")
    private EntityManager em;

    public void insertProyecto(Proyecto proyecto) {
        em.persist(proyecto);
    }

    public void updateProyecto(Proyecto proyecto) {
        em.merge(proyecto);
    }

    public Proyecto readProyecto(Long pk) {
        return em.find(Proyecto.class, pk);
    }

    public void deleteProyecto(Long pk) {
        Proyecto proyecto = em.find(Proyecto.class, pk);
        if (proyecto != null) {
            em.remove(proyecto);
        }
    }

    public List<Proyecto> getAll() {
        String jpql = "SELECT p FROM Proyecto p";
        Query q = em.createQuery(jpql, Proyecto.class);
        return q.getResultList();
    }
}
