package view;

import controller.Conn;
import db.Database;

import javax.xml.crypto.Data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

	static Connection conn = null;

    public static void main (String[]args) throws IOException {

    	
    	
        try{
            conn = Conn.open();
            crearTablas();
            insertarDatos();
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

    private static void insertarDatos() throws SQLException, IOException {
		String datosAlumno1 = "INSERT INTO Alumno(numero, usuario, contrasena, fecha_nacimiento, nota_media, imagen)"
							+ "VALUES (1, 'Antonio', 'password123', 2001/08/21, 9.8, " + Database.getImageBytes("iconAntonio.jpg") + ");";
		String datosAlumno2 = "INSERT INTO Alumno(numero, usuario, contrasena, fecha_nacimiento, nota_media, imagen)"
							+ "INSERT INTO Alumno(2, 'Manuel', 'contasena123', 2002/09/26, 8.76, " + Database.getImageBytes("iconManuel.png") + ");";
		
		Database.executeIDU(datosAlumno1);
		Database.executeIDU(datosAlumno2);
		
		String datosAsignatura1 = "INSERT INTO Asignatura(codigo, nombre, nota, aluNumero)"
								+ "VALUES (1, 'Matemáticas', 7.6, 1);";	
		String datosAsignatura2 = "INSERT INTO Asignatura(codigo, nombre, nota, aluNumero)"
								+ "VALUES (2, 'Matemáticas', 8.75, 2);";
		String datosAsignatura3 = "INSERT INTO Asignatura(codigo, nombre, nota, aluNumero)"
								+ "VALUES (3, 'Física', 5.6, 1);";	
		String datosAsignatura4 = "INSERT INTO Asignatura(codigo, nombre, nota, aluNumero)"
								+ "VALUES (4, 'Biología', 4.95, 2);";	
		String datosAsignatura5 = "INSERT INTO Asignatura(codigo, nombre, nota, aluNumero)"
								+ "VALUES (5, 'Plástica', 3.75, 1);";	
		String datosAsignatura6 = "INSERT INTO Asignatura(codigo, nombre, nota, aluNumero)"
								+ "VALUES (6, 'Conocimiento del Medio', 7.02, 2);";
		String datosAsignatura7 = "INSERT INTO Asignatura(codigo, nombre, nota, aluNumero)"
								+ "VALUES (7, 'Lengua', 5.0, 1);";	
		String datosAsignatura8 = "INSERT INTO Asignatura(codigo, nombre, nota, aluNumero)"
								+ "VALUES (8, 'Inglés', 10.0, 2);";
		
		Database.executeIDU(datosAsignatura1);
		Database.executeIDU(datosAsignatura2);
		Database.executeIDU(datosAsignatura3);
		Database.executeIDU(datosAsignatura4);
		Database.executeIDU(datosAsignatura5);
		Database.executeIDU(datosAsignatura6);
		Database.executeIDU(datosAsignatura7);
		Database.executeIDU(datosAsignatura8);
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
