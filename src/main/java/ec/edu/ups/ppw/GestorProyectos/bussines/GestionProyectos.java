package ec.edu.ups.ppw.GestorProyectos.bussines;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.DAO.ProyectoDAO;
import ec.edu.ups.ppw.GestorProyectos.modelo.Proyecto;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionProyectos {

    @Inject
    private ProyectoDAO daoProyecto;

    public List<Proyecto> getProyectos() {
        return daoProyecto.getAll();
    }

    public Proyecto getProyectoPorId(int id) {
        return daoProyecto.readProyecto(id);
    }

    public void crearProyecto(Proyecto proyecto) {
        daoProyecto.insertProyecto(proyecto);
    }

    public void actualizarProyecto(Proyecto proyecto) {
        daoProyecto.updateProyecto(proyecto);
    }

    public void eliminarProyecto(int id) {
        daoProyecto.deleteProyecto(id);
    }
}
