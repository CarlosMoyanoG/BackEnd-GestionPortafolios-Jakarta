package ec.edu.ups.ppw.GestorProyectos.bussines;

import ec.edu.ups.ppw.GestorProyectos.DAO.AsesoriaDAO;
import ec.edu.ups.ppw.GestorProyectos.DAO.DisponibilidadDAO;
import ec.edu.ups.ppw.GestorProyectos.DAO.ProgramadorDAO;
import ec.edu.ups.ppw.GestorProyectos.DAO.ProyectoDAO;
import ec.edu.ups.ppw.GestorProyectos.DAO.UsuarioDAO;
import ec.edu.ups.ppw.GestorProyectos.modelo.Asesoria;
import ec.edu.ups.ppw.GestorProyectos.modelo.Disponibilidad;
import ec.edu.ups.ppw.GestorProyectos.modelo.Programador;
import ec.edu.ups.ppw.GestorProyectos.modelo.Proyecto;
import ec.edu.ups.ppw.GestorProyectos.modelo.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class DatosDemo {

    @Inject private UsuarioDAO daoUsuario;
    @Inject private ProgramadorDAO daoProgramador;
    @Inject private ProyectoDAO daoProyecto;
    @Inject private DisponibilidadDAO daoDisponibilidad;
    @Inject private AsesoriaDAO daoAsesoria;

    @PostConstruct
    public void init() {
        System.out.println("Seeder: insertando datos de prueba...");


    }
}
