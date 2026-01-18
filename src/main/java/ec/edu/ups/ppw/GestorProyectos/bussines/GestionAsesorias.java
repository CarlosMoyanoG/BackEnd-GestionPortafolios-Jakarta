package ec.edu.ups.ppw.GestorProyectos.bussines;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.DAO.AsesoriaDAO;
import ec.edu.ups.ppw.GestorProyectos.modelo.Asesoria;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionAsesorias {
	
	@Inject
	private AsesoriaDAO daoAsesoria;
	
	public List<Asesoria> getAsesoria(){
		return daoAsesoria.getAll();
	}
	
	public Asesoria getAsesoriaPorId(int id) {
        return daoAsesoria.readAsesoria(id);
    }
	
    public void crearAsesoria(Asesoria asesoria) {
        daoAsesoria.insertAsesoria(asesoria);
    }

    public void actualizarAsesoria(Asesoria asesoria) {
        daoAsesoria.updateAsesoria(asesoria);
    }

    public void eliminarAsesoria(int id) {
        daoAsesoria.deleteAsesoria(id);
    }
}
