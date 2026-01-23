package ec.edu.ups.ppw.GestorProyectos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long id;

    @Column(name = "usu_nombre")
    private String nombre;

    @Column(name = "usu_mail")
    private String email;

    @Column(name = "usu_rol")
    private String rol;

    @Column(name = "usu_progId")
    private Long programadorId;

    @Column(name = "usu_fotoUrl")
    private String fotoUrl;

    public Usuario() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Long getProgramadorId() { return programadorId; }
    public void setProgramadorId(Long programadorId) { this.programadorId = programadorId; }

    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }
}
