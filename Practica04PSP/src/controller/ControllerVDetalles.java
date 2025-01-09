package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.Database;
import model.Asignatura;
import view.PnlEntrar;
import view.PnlVDetalle;

public class ControllerVDetalles {
	
    private List<Asignatura> asignaturas;
    private int pos = 0;

    public ControllerVDetalles() {
        try {
            // Obtenemos el ResultSet y convertimos los resultados en una lista de Asignaturas
            ResultSet rs = obtenerAsignaturas();
            if (rs != null) {
                asignaturas = conversion(rs);
            } else {
                asignaturas = new ArrayList<>();
            }
        } catch (SQLException e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            asignaturas = new ArrayList<>();
        }
    }

    private ResultSet obtenerAsignaturas() {
        try {
            // Abrimos la conexión
            Conn.open();
            // Devolvemos un RS con todas las asignaturas
            return Database.executeQuery("SELECT * FROM Asignatura "
                                       + "WHERE aluNumero = (SELECT numero FROM Alumno "
                                       + "WHERE usuario = '" + PnlEntrar.user + "');");
        } catch (SQLException e) {
            // Si hay algún error se le indica al usuario a través de un JOptionPane
            JOptionPane.showMessageDialog(null, "No existen asignaturas para mostrar");
            return null;
        } finally {
            // Cerramos la conexión
            Conn.close();
        }
    }

    private List<Asignatura> conversion(ResultSet rs) throws SQLException {
        List<Asignatura> asignaturas = new ArrayList<>();

        while (rs.next()) {
            // Obtenemos los valores de las columnas del ResultSet
            String nombre = rs.getString("nombre");
            double nota = rs.getDouble("nota");

            // Creamos una instancia de Asignatura
            Asignatura asignatura = new Asignatura(Asignatura.n++, nombre, nota, PnlEntrar.idAlumn);

            // Agregamos la instancia a la lista
            asignaturas.add(asignatura);
        }
        return asignaturas;
    }
    
    public void mostrarPrimero() {
		//Obtenemos la primera asignatura de la lista
		Asignatura primera = asignaturas.getFirst();
		
		//Colocamos el nombre en la etiqueta nombre
		PnlVDetalle.lblAsignatura.setText(primera.getNombre());
		
		//Colocamos la nota en el txtNota
		PnlVDetalle.txtNota.setText(primera.getNota().toString());
		
		//Ponemos la posicion a 0
		pos = 0;
	}
	
	public void mostrarUltimo() {
		//Obtenemos el ultimo registro de asignaturas
		Asignatura ultima = asignaturas.getLast();
		
		//Colocamos el nombre en la etiqueta nombre
		PnlVDetalle.lblAsignatura.setText(ultima.getNombre());
		
		//Colocamos la nota en el txtNota
		PnlVDetalle.txtNota.setText(ultima.getNota().toString());
		
		//Ponemos la posicion al tamaño de la lista
		pos = asignaturas.size()-1;
		
	}
	
	public void mostrarSiguiente() {
		if (pos < asignaturas.size()-1) {
			//Incrementamos la posicion y obtenemos el registro
			pos++;
			Asignatura a = asignaturas.get(pos);
			
			//Colocamos el nombre en la etiqueta nombre
			PnlVDetalle.lblAsignatura.setText(a.getNombre());
			
			//Colocamos la nota en el txtNota
			PnlVDetalle.txtNota.setText(a.getNota().toString());
		}
	}
	
	public void mostrarAnterior() {
		if (pos > 0) {
			//Decrementamos la posicion y obtenemos el registro
			pos--;
			Asignatura a = asignaturas.get(pos);
			
			//Colocamos el nombre en la etiqueta nombre
			PnlVDetalle.lblAsignatura.setText(a.getNombre());
			
			//Colocamos la nota en el txtNota
			PnlVDetalle.txtNota.setText(a.getNota().toString());
		}
	}

	
	
	
	public void guardarNota() {
		try {
			Double nota = Double.parseDouble(PnlVDetalle.txtNota.getText());
			String sql = "UPDATE Asignaturas"
						+ "SET nota = " + nota
						+ "WHERE aluNumero = " + PnlEntrar.idAlumn
						+ "AND nombre = '" + PnlVDetalle.lblAsignatura.getText() + "';";
			
			Database.executeIDU(sql);
		} catch (SQLException e) {
			//Si hay algun error se le indica al usuario a través de un JOptionPane
			JOptionPane.showMessageDialog(null, "No existen asignaturas para mostrar");
		} finally {
			//Cerramos la conexión
			Conn.close();
		}
		
	}
    
}
