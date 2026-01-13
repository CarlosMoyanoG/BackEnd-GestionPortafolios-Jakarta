package ec.edu.ups.ppw.GestorProyectos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_Disponibilidad")
public class Disponibilidad {

    @Id
    @Column(name = "dis_id")
    private int id;

    @Column(name = "dis_progId")
    private int programadorId;

    @Column(name = "dis_tipo")
    private String tipo; // 'recurrente' | 'bloqueo' | 'puntual'

    @Column(name = "dis_modalidad")
    private String modalidad; // 'virtual' | 'presencial'

    @Column(name = "dis_diaSemana")
    private Integer diaSemana;

    @Column(name = "dis_fecha")
    private String fecha;

    @Column(name = "dis_horaInicio")
    private String horaInicio;

    @Column(name = "dis_horaFin")
    private String horaFin;

    @Column(name = "dis_hora")
    private String hora;

    public Disponibilidad() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}