package ec.edu.ups.ppw.GestorProyectos.DAO;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.modelo.Disponibilidad;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class DisponibilidadDAO {

    @PersistenceContext
    private EntityManager em;

    public void insertDisponibilidad(Disponibilidad disponibilidad) {
        em.persist(disponibilidad);
    }

    public void updateDisponibilidad(Disponibilidad disponibilidad) {
        em.merge(disponibilidad);
    }

    public Disponibilidad readDisponibilidad(int pk) {
        return em.find(Disponibilidad.class, pk);
    }

    public void deleteDisponibilidad(int pk) {
        Disponibilidad disponibilidad = em.find(Disponibilidad.class, pk);
        if (disponibilidad != null) {
            em.remove(disponibilidad);
        }
    }

    public List<Disponibilidad> getAll() {
        String jpql = "SELECT d FROM Disponibilidad d";
        Query q = em.createQuery(jpql, Disponibilidad.class);
        return q.getResultList();
    }
}