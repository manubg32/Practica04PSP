package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

public class CtrlEntrar {

	public boolean validarCredenciales(String usuario, char[] contrasena) {
        String consulta = "SELECT COUNT(*) FROM alumno WHERE usuario = ? AND contrasena = ?";
        try (Connection conn = Conn.open();
             PreparedStatement stmt = conn.prepareStatement(consulta)) {
            
            // Sustituir parámetros con valores seguros
            stmt.setString(1, usuario);
            
            // Convertir `char[]` a `String` temporalmente
            String contrasenaString = new String(contrasena);
            stmt.setString(2, contrasenaString);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Devuelve true si se encontró un usuario
                }
            } finally {
                // Limpiar el `String` temporal eliminando referencia
                contrasenaString = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Limpiar el arreglo de caracteres por seguridad
            Arrays.fill(contrasena, '\0');
        }
        return false;
    }

}
