package controller;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexion con la BD en MySQL
 */
public class Conn {

    public static java.sql.Connection conn; //Instanciamos la conexion


    //Metodo que devuelve la conexion
    public static java.sql.Connection open() {
        String host = "jdbc:mysql://localhost/"; //declaramos el host
        String user = "root"; //Declaramos el usuario
        String pass = ""; //Declaramos la contraseña
        String bd = "psp04"; //Declaramos el nombre de la base de datos

        //Previamente he creado la base de datos "psp04" desde DBeaver ya que si no me decía que no existía
        //Establecemos la conexion conn MySQL desde XAMPP y creamos la base de datos conn una conexión a MySQL desde DBeaver

        try {
            conn = DriverManager.getConnection(host + bd, user, pass);
            //Si la conexion es exitosa avisamos al usuario
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            //Si existe cualquier error informamos al usuario
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return conn;
    }


    public static void close() {
        try {
            conn.close();
            System.out.println("Desconectado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
