package model;

import java.util.Calendar;

public class Alumno {

    private Integer id;
    private String user;
    private String password;
    private Calendar birthday;
    private Double avg;
    private String idFoto;

    public Alumno(Integer num, String user, String password, Calendar birthday, Double avg, String idFoto) {
        this.id = num; //PK
        this.user = user;
        this.password = password;
        this.birthday = birthday;
        this.avg = avg; //Media de notas
        this.idFoto = idFoto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String idFoto) {
        this.idFoto = idFoto;
    }
}
