package ec.edu.ups.ppw.GestorProyectos.bussines;

import java.util.List;

import ec.edu.ups.ppw.GestorProyectos.DAO.UsuarioDAO;
import ec.edu.ups.ppw.GestorProyectos.modelo.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUsuarios {

    @Inject
    private UsuarioDAO daoUsuario;

    public List<Usuario> getUsuarios() {
        return daoUsuario.getAll();
    }

    public Usuario getUsuarioPorId(Long id) {
        return daoUsuario.readUsuario(id);
    }

    public void crearUsuario(Usuario usuario) {
        daoUsuario.insertUsuario(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        daoUsuario.updateUsuario(usuario);
    }

    public void eliminarUsuario(Long id) {
        daoUsuario.deleteUsuario(id);
    }
}
