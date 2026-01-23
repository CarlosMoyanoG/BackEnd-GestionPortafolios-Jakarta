package ec.edu.ups.ppw.GestorProyectos.modelo;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_Proyecto")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proy_id")
    private Long id;

    @Column(name = "proy_nombre")
    private String nombre;

    @Column(name = "proy_descripcion")
    private String descripcion;

    @Column(name = "proy_seccion")
    private String seccion;

    @Column(name = "proy_participacion")
    private String participacion;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tbl_Proyecto_Tecnologias", joinColumns = @JoinColumn(name = "proy_id"))
    @Column(name = "proy_tecnologia")
    private List<String> tecnologias = new ArrayList<>();

    @Column(name = "proy_repoUrl")
    private String repoUrl;

    @Column(name = "proy_demoUrl")
    private String demoUrl;

    public Proyecto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getSeccion() { return seccion; }
    public void setSeccion(String seccion) { this.seccion = seccion; }

    public String getParticipacion() { return participacion; }
    public void setParticipacion(String participacion) { this.participacion = participacion; }

    public List<String> getTecnologias() { return tecnologias; }
    public void setTecnologias(List<String> tecnologias) { this.tecnologias = tecnologias; }

    public String getRepoUrl() { return repoUrl; }
    public void setRepoUrl(String repoUrl) { this.repoUrl = repoUrl; }

    public String getDemoUrl() { return demoUrl; }
    public void setDemoUrl(String demoUrl) { this.demoUrl = demoUrl; }
}
