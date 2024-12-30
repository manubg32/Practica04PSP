package model;

public class Asignatura
{

    private Integer cod;
    private String nombre;
    private Double nota;
    private Integer idAlumn; //FK Alumno

    public Asignatura(Integer cod, String nombre, Double nota, Integer idAlumn) {
        this.cod = cod;
        this.nombre = nombre;
        this.nota = nota;
        this.idAlumn = idAlumn;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Integer getIdAlumn() {
        return idAlumn;
    }

    public void setIdAlumn(Integer idAlumn) {
        this.idAlumn = idAlumn;
    }
}
