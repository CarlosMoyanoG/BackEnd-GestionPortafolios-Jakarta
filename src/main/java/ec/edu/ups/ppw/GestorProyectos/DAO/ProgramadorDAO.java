package ec.edu.ups.ppw.GestorProyectos.DAO;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.modelo.Programador;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ProgramadorDAO {
	
	@PersistenceContext(unitName = "GestorProyectosPersistenceUnit")
	private EntityManager em;
		
	public void insertProgramador(Programador programador) {
		em.persist(programador);
	}
	
	public void updateProgramador(Programador programador) {
		em.merge(programador);
	}
	
	public Programador readProgramador(Long pk) {
		return em.find(Programador.class, pk);
	}
	
	public void deleteProgramador(Long pk){
		Programador prog = em.find(Programador.class, pk);
		
		if (prog != null) {
			em.remove(prog);
		}
	}
	
	public List<Programador> getAll() {
        String jpql = "SELECT p FROM Programador p";
        Query q = em.createQuery(jpql, Programador.class);
        return q.getResultList();
    }
}
