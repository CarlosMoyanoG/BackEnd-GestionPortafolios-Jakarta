package ec.edu.ups.ppw.GestorProyectos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_Asesoria")
public class Asesoria {

    @Id
    @Column(name = "ase_id")
    private int id;

    @Column(name = "ase_progId")
    private int programadorId;

    @Column(name = "ase_nombreCliente")
    private String nombreCliente;

    @Column(name = "ase_emailCliente")
    private String emailCliente;

    @Column(name = "ase_fecha")
    private String fecha;

    @Column(name = "ase_hora")
    private String hora;

    @Column(name = "ase_descripcionProyecto")
    private String descripcionProyecto;

    @Column(name = "ase_estado")
    private String estado; // 'pendiente' | 'aprobada' | 'rechazada'

    @Column(name = "ase_mensajeRespuesta")
    private String mensajeRespuesta;

    public Asesoria() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgramadorId() {
        return programadorId;
    }

    public void setProgramadorId(int programadorId) {
        this.programadorId = programadorId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }
}