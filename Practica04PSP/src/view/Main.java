package view;

import controller.Conn;
import db.Database;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

	static Connection conn = null;

    public static void main (String[]args) {

    	
    	
        try{
            conn = Conn.open();
            crearTablas();
            System.out.println("Creado con exito");
            Conn.close();
        }catch(SQLException e){
            System.out.println(e);
        } finally {
        	
        }
        try {
            new FrmMenuPrincipal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Este metodo es por si no tenemos tablas creadas las crea
    private static void crearTablas() throws SQLException {
        String crearTablaAlumno = "CREATE TABLE IF NOT EXISTS Alumno ("
                + "numero INT PRIMARY KEY, "
                + "usuario VARCHAR(255) NOT NULL, "
                + "contrasena VARCHAR(255) NOT NULL, "
                + "fecha_nacimiento DATE NOT NULL, "
                + "nota_media FLOAT NOT NULL, "
                + "imagen BLOB)";
        String crearTablaAsignatura = "CREATE TABLE IF NOT EXISTS Asignatura ("
                + "codigo INT PRIMARY KEY, "
                + "nombre VARCHAR(255) NOT NULL, "
                + "nota FLOAT NOT NULL, "
                + "aluNumero INT, "
                + "FOREIGN KEY (aluNumero) REFERENCES Alumno(numero))";

        Database.executeCreate(crearTablaAlumno, conn);
        Database.executeCreate(crearTablaAsignatura, conn);
    }
}
