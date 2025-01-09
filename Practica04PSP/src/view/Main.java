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
            borrarDatos();
            insertarDatos();
            System.out.println("Creado con exito");
			try {
				new FrmMenuPrincipal();

			} catch (Exception e) {
				e.printStackTrace();
			}
        }catch(SQLException e){
            System.out.println(e);
        }



    }

    private static void borrarDatos() throws SQLException {
		String borrarAlumnos = "DELETE FROM alumno WHERE numero in (1, 2);";
		String borrarAsignaturas = "DELETE FROM asignatura WHERE codigo in (1, 2, 3, 4, 5, 6, 7, 8);";
		
		Conn.executeIDU(borrarAsignaturas);
		Conn.executeIDU(borrarAlumnos);
	}

	private static void insertarDatos() throws SQLException, IOException {
		String datosAlumno1 = "INSERT INTO alumno(numero, usuario, contrasena, fecha_nacimiento, nota_media, imagen)"
							+ "VALUES (1, 'Antonio', 'password123', '2001-08-21', 9.8, 'iconAntonio.jpg');";
		String datosAlumno2 = "INSERT INTO alumno(numero, usuario, contrasena, fecha_nacimiento, nota_media, imagen)"
							+ "VALUES(2, 'Manuel', 'contrasena123', '2002-09-26', 8.76, 'iconManuel.png');";
		
		Conn.executeIDU(datosAlumno1);
		Conn.executeIDU(datosAlumno2);
		
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
		
		Conn.executeIDU(datosAsignatura1);
		Conn.executeIDU(datosAsignatura2);
		Conn.executeIDU(datosAsignatura3);
		Conn.executeIDU(datosAsignatura4);
		Conn.executeIDU(datosAsignatura5);
		Conn.executeIDU(datosAsignatura6);
		Conn.executeIDU(datosAsignatura7);
		Conn.executeIDU(datosAsignatura8);
	}

	//Este metodo es por si no tenemos tablas creadas las crea
    private static void crearTablas() throws SQLException {
        String crearTablaAlumno = "CREATE TABLE IF NOT EXISTS Alumno ("
                + "numero INT PRIMARY KEY, "
                + "usuario VARCHAR(255) NOT NULL, "
                + "contrasena VARCHAR(255) NOT NULL, "
                + "fecha_nacimiento DATE NOT NULL, "
                + "nota_media FLOAT NOT NULL, "
                + "imagen VARCHAR(255))";
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
