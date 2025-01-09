package model;

public class Asignatura
{
	private static int n = 0;

    private Integer cod;
    private String nombre;
    private Double nota;
    private Integer idAlumn; //FK Alumno

    public Asignatura(String nombre, Double nota, Integer idAlumn) {
        this.cod = getCod();
        this.nombre = nombre;
        this.nota = nota;
        this.idAlumn = idAlumn;
    }

    public Integer getCod() {
        return n++;
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
