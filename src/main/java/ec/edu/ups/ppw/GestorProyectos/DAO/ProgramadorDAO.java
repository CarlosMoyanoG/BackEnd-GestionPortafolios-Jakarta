package ec.edu.ups.ppw.GestorProyectos.DAO;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.modelo.Programador;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ProgramadorDAO {
	
	@PersistenceContext
	private EntityManager em;
		
	public void insertProgramador(Programador programador) {
		em.persist(programador);
	}
	
	public void updateProgramador(Programador programador) {
		em.merge(programador);
	}
	
	public Programador readProgramador(int pk) {
		return em.find(Programador.class, pk);
	}
	
	public void deleteProgramador(int pk){
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
