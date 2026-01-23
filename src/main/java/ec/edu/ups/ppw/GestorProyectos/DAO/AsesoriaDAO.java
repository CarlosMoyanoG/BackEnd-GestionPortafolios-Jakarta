package ec.edu.ups.ppw.GestorProyectos.DAO;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.modelo.Asesoria;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class AsesoriaDAO {

    @PersistenceContext(unitName = "GestorProyectosPersistenceUnit")
    private EntityManager em;

    public void insertAsesoria(Asesoria asesoria) {
        em.persist(asesoria);
    }

    public void updateAsesoria(Asesoria asesoria) {
        em.merge(asesoria);
    }

    public Asesoria readAsesoria(Long pk) {
        return em.find(Asesoria.class, pk);
    }

    public void deleteAsesoria(Long pk) {
        Asesoria asesoria = em.find(Asesoria.class, pk);
        if (asesoria != null) {
            em.remove(asesoria);
        }
    }

    public List<Asesoria> getAll() {
        String jpql = "SELECT a FROM Asesoria a";
        Query q = em.createQuery(jpql, Asesoria.class);
        return q.getResultList();
    }
}
