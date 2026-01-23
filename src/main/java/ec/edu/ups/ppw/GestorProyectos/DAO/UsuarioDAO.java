package ec.edu.ups.ppw.GestorProyectos.DAO;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.modelo.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UsuarioDAO {

    @PersistenceContext(unitName = "GestorProyectosPersistenceUnit")
    private EntityManager em;

    public void insertUsuario(Usuario usuario) {
        em.persist(usuario);
    }

    public void updateUsuario(Usuario usuario) {
        em.merge(usuario);
    }

    public Usuario readUsuario(Long pk) {
        return em.find(Usuario.class, pk);
    }

    public void deleteUsuario(Long pk) {
        Usuario usuario = em.find(Usuario.class, pk);
        if (usuario != null) {
            em.remove(usuario);
        }
    }

    public List<Usuario> getAll() {
        String jpql = "SELECT u FROM Usuario u";
        Query q = em.createQuery(jpql, Usuario.class);
        return q.getResultList();
    }
}
