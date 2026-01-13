package ec.edu.ups.ppw.GestorProyectos.bussines;

import ec.edu.ups.ppw.GestorProyectos.DAO.UsuarioDAO;
import ec.edu.ups.ppw.GestorProyectos.modelo.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Singleton
@Startup
public class Demo {
	
	@Inject
	private UsuarioDAO daoUsuario;
	
	@PostConstruct
	public void init() {
		
		System.out.print("Hola Inicio");
		Usuario u = new Usuario();
		u.setId(1);
		u.setEmail("carlosmoyano@gmail.com");
		u.setNombre("Carlos Moyano");
		u.setRol("Programador");
		
		daoUsuario.insertUsuario(u);
	}
}
