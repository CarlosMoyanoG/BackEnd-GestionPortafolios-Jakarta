package ec.edu.ups.ppw.GestorProyectos.bussines;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.DAO.ProgramadorDAO;
import ec.edu.ups.ppw.GestorProyectos.modelo.Programador;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionProgramadores {

    @Inject
    private ProgramadorDAO daoProgramador;

    public List<Programador> getProgramadores() {
        return daoProgramador.getAll();
    }

    public Programador getProgramadorPorId(int id) {
        return daoProgramador.readProgramador(id);
    }

    public void crearProgramador(Programador programador) {
        daoProgramador.insertProgramador(programador);
    }

    public void actualizarProgramador(Programador programador) {
        daoProgramador.updateProgramador(programador);
    }

    public void eliminarProgramador(int id) {
        daoProgramador.deleteProgramador(id);
    }
}
