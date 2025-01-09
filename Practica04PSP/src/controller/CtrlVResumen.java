package controller;

import model.Alumno;
import model.Asignatura;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CtrlVResumen {
    public CtrlVResumen(){

    }

    public Alumno obtenerAlumno(int id) {
        String sql = "SELECT * FROM alumno WHERE numero = ?";
        ResultSet rs = null;
        try {
            Conn.open();
            rs = Conn.executeSTQuery(sql,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String usuario = null;
        String contrasena = null ;
        Date fecha_nacimiento = null ;
        Calendar fecha = Calendar.getInstance();
        Double nota_media = null ;
        String imagen = null;
        try{
            while(rs.next()){
                usuario = rs.getString("usuario");
                contrasena = rs.getString("contrasena");
                fecha_nacimiento = rs.getDate("fecha_nacimiento");
                nota_media = rs.getDouble("nota_media");
                imagen = rs.getString("imagen");
                // Verificar si fecha_nacimiento es null
                if (fecha_nacimiento != null) {
                    // Si no es null, entonces usamos la fecha en el Calendar
                    fecha.setTime(fecha_nacimiento);
                } else {
                    // Si es null, puedes asignar una fecha predeterminada o manejar el caso
                    System.out.println("Fecha de nacimiento es nula.");
                    // Por ejemplo, puedes asignar una fecha predeterminada
                    fecha.setTime(new Date());  // Asigna la fecha actual como predeterminada
                }

            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return new Alumno(id,usuario,contrasena,fecha ,nota_media,imagen);
    }
    public List<Asignatura> obtenerAsignaturas(int id){
        String sql = "SELECT * FROM asignatura WHERE aluNumero = ?";

        List<Asignatura> asignaturas = new ArrayList<>();

        try{
            ResultSet rs = Conn.executeSTQuery(sql, id);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                Double nota = rs.getDouble("nota");
                Integer idAlumn = rs.getInt("aluNumero");
                Asignatura a = new Asignatura(nombre,nota,idAlumn);
                asignaturas.add(a);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return asignaturas;
    }
    public Double notaMedia(int id) {
        Double notaMedia = 0.0;
        Alumno a = obtenerAlumno(id);
        List<Asignatura> asignaturas = obtenerAsignaturas(id);

        Double sumaA= 0d;
        for (Asignatura a1 : asignaturas) {
           sumaA += a1.getNota();
        }
        notaMedia = sumaA / asignaturas.size();

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.'); // Aseguramos que el separador sea el punto

        DecimalFormat formato = new DecimalFormat("#.00", simbolos);

        String numeroFormat = formato.format(notaMedia);

        notaMedia = Double.parseDouble(numeroFormat);

        if(!notaMedia.equals(a.getAvg())){

            int respuesta = JOptionPane.showConfirmDialog(null, "La nota media es diferente (Nota media guardada: "+a.getAvg()+", Nota media calculada:"+notaMedia+" ). Se va a actualizar.", "Actualización", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if(respuesta == JOptionPane.YES_OPTION){
                String sql = "UPDATE alumno SET nota_media = ? WHERE numero = ?";


                try{
                    Conn.executeSTIDU(sql, notaMedia, id);
                }catch(SQLException e){
                    e.printStackTrace();
                }
            } else {
                notaMedia = a.getAvg();
            }

        }

        return notaMedia;
    }


}
