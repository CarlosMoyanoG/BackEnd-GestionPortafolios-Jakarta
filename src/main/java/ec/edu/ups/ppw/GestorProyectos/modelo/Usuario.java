package ec.edu.ups.ppw.GestorProyectos.modelo;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name  = "tbl_Usuario")
public class Usuario {
    
    @Id
    @Column(name = "usu_id")
    int id;
    
    @Column(name = "usu_opcionesRol")
    ArrayList<String> opcionesRol = new ArrayList<>();
    
    @Column(name = "usu_nombre")
    String nombre;
    
    @Column(name = "usu_mail")
    String email;
    
    @Column(name = "usu_rol")
    String rol;
    
    @Column(name = "usu_progId")
    int programadorId;
    
    @Column(name = "usu_fotoUrl")
    String fotoUrl;
    
    public Usuario() {
        opcionesRol.add("visitante");
        opcionesRol.add("admin");
        opcionesRol.add("programador");
    }

    public ArrayList<String> getOpcionesRol() {
        return opcionesRol;
    }

    public void setOpcionesRol(ArrayList<String> opcionesRol) {
        this.opcionesRol = opcionesRol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getProgramadorId() {
        return programadorId;
    }

    public void setProgramadorId(int programadorId) {
        this.programadorId = programadorId;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}
