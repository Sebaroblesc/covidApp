package cl.inacap.covidapp.dto;

import java.io.Serializable;
import java.util.Date;

public class Paciente implements Serializable {
    private int id;
    private String rut;
    private String nombre;
    private String apellido;

    public int getSintomas() {
        return sintomas;
    }

    public int getTos() {
        return tos;
    }

    private String fechaExamen;
    private String areaTrabajo;
    private int sintomas;
    private int temperatura;
    private int tos;
    private int presion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(String fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public int isSintomas() {
        return sintomas;
    }

    public void setSintomas(int sintomas) {
        this.sintomas = sintomas;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int isTos() {
        return tos;
    }

    public void setTos(int tos) {
        this.tos = tos;
    }

    public int getPresion() {
        return presion;
    }

    public void setPresion(int presion) {
        this.presion = presion;
    }
}
