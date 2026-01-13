package ec.edu.ups.ppw.GestorProyectos.bussines;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.DAO.DisponibilidadDAO;
import ec.edu.ups.ppw.GestorProyectos.modelo.Disponibilidad;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionDisponibilidades {

    @Inject
    private DisponibilidadDAO daoDisponibilidad;

    public List<Disponibilidad> getDisponibilidades() {
        return daoDisponibilidad.getAll();
    }

    public Disponibilidad getDisponibilidadPorId(int id) {
        return daoDisponibilidad.readDisponibilidad(id);
    }

    public void crearDisponibilidad(Disponibilidad disponibilidad) {
        daoDisponibilidad.insertDisponibilidad(disponibilidad);
    }

    public void actualizarDisponibilidad(Disponibilidad disponibilidad) {
        daoDisponibilidad.updateDisponibilidad(disponibilidad);
    }

    public void eliminarDisponibilidad(int id) {
        daoDisponibilidad.deleteDisponibilidad(id);
    }
}
