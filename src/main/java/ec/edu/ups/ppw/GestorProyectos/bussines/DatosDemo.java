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


        if (daoUsuario.readUsuario(1) == null) {
            Usuario u = new Usuario();
            u.setId(1);
            u.setEmail("carlosmoyano@gmail.com");
            u.setNombre("Carlos Moyano");
            u.setRol("Programador");
            daoUsuario.insertUsuario(u);
        }


        if (daoProgramador.readProgramador(10) == null) {
            Programador p = new Programador();
            p.setId(10);
            p.setNombre("Carlos Moyano");
            p.setEmailContacto("carlosmoyano@gmail.com");
            p.setEspecialidad("Full Stack");
            p.setGithubUrl("https://github.com/carlosmoyano");
            p.setLinkedinUrl("https://linkedin.com/in/carlosmoyano");
            p.setSitioWeb("https://carlosmoyano.dev");
            p.setFotoUrl("https://picsum.photos/200");
            p.setDescripcion("Programador para asesorías y proyectos.");
            p.setDuenioUid("firebase-uid-demo"); 
            daoProgramador.insertProgramador(p);
        }


        if (daoProyecto.readProyecto(100) == null) {
            Proyecto pr = new Proyecto();
            pr.setId(100);
            pr.setNombre("Gestor de Proyectos");
            pr.setDescripcion("Backend Java con JAX-RS y JPA.");
            pr.setRepoUrl("https://github.com/carlosmoyano/gestor-proyectos");
            pr.setDemoUrl("https://demo.example.com");
            pr.setSeccion("Portafolio");
            pr.setParticipacion("Backend");
            daoProyecto.insertProyecto(pr);
        }

        if (daoDisponibilidad.readDisponibilidad(200) == null) {
            Disponibilidad d = new Disponibilidad();
            d.setId(200);
            d.setProgramadorId(10);
            d.setDiaSemana(2); 
            d.setFecha("2026-01-13");
            d.setHora("10:00");
            d.setHoraInicio("10:00");
            d.setHoraFin("11:00");
            d.setModalidad("virtual");
            d.setTipo("asesoria");
            daoDisponibilidad.insertDisponibilidad(d);
        }


        if (daoAsesoria.readAsesoria(300) == null) {
            Asesoria a = new Asesoria();
            a.setId(300);
            a.setProgramadorId(10);
            a.setNombreCliente("Juan Perez");
            a.setEmailCliente("juan@mail.com");
            a.setFecha("2026-01-13");
            a.setHora("10:30");
            a.setDescripcionProyecto("Necesito ayuda con un CRUD en Java.");
            a.setEstado("pendiente");
            a.setMensajeRespuesta("");
            daoAsesoria.insertAsesoria(a);
        }
    }
}
