package ec.edu.ups.ppw.GestorProyectos.modelo;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_Programador")
public class Programador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prog_id")
    private Long id;

    @Column(name = "pro_nombre")
    private String nombre;

    @Column(name = "pro_especialidad")
    private String especialidad;

    @Column(name = "pro_descripcion")
    private String descripcion;

    @Column(name = "pro_fotoUrl")
    private String fotoUrl;

    @Column(name = "pro_emailContacto")
    private String emailContacto;

    @Column(name = "pro_githubUrl")
    private String githubUrl;

    @Column(name = "pro_linkedinUrl")
    private String linkedinUrl;

    @Column(name = "pro_sitioWeb")
    private String sitioWeb;

    @Column(name = "pro_duenioUid")
    private String duenioUid;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Proyecto> proyectos = new ArrayList<>();

    public Programador() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getFotoUrl() { return fotoUrl; }
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; }

    public String getEmailContacto() { return emailContacto; }
    public void setEmailContacto(String emailContacto) { this.emailContacto = emailContacto; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }

    public String getLinkedinUrl() { return linkedinUrl; }
    public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }

    public String getSitioWeb() { return sitioWeb; }
    public void setSitioWeb(String sitioWeb) { this.sitioWeb = sitioWeb; }

    public String getDuenioUid() { return duenioUid; }
    public void setDuenioUid(String duenioUid) { this.duenioUid = duenioUid; }

    public List<Proyecto> getProyectos() { return proyectos; }
    public void setProyectos(List<Proyecto> proyectos) { this.proyectos = proyectos; }
}
