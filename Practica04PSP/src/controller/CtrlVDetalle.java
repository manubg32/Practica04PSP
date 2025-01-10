package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Asignatura;
import view.PnlEntrar;
import view.PnlVDetalle;

public class CtrlVDetalle {

	private List<Asignatura> asignaturas;
	private int pos = 0;

	public CtrlVDetalle() {
		// Obtenemos el ResultSet y convertimos los resultados en una lista de
		// Asignaturas
		asignaturas = obtenerAsignaturas(PnlEntrar.idAlumn);
	}

	public List<Asignatura> obtenerAsignaturas(int id) {
		String sql = "SELECT * FROM asignatura WHERE aluNumero = ?";

		List<Asignatura> asignaturas = new ArrayList<>();

		try {
			Conn.open();
			ResultSet rs = Conn.executeSTQuery(sql, id);
			while (rs.next()) {
				String nombre = rs.getString("nombre");
				Double nota = rs.getDouble("nota");
				Integer idAlumn = rs.getInt("aluNumero");
				Asignatura a = new Asignatura(nombre, nota, idAlumn);
				asignaturas.add(a);
			}
			Conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ha habido un error al obtener las asignaturas", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return asignaturas;
	}

	private List<Asignatura> conversion(ResultSet rs) throws SQLException {
		List<Asignatura> asignaturas = new ArrayList<>();

		while (rs.next()) {
			// Obtenemos los valores de las columnas del ResultSet
			String nombre = rs.getString("nombre");
			double nota = rs.getDouble("nota");

			// Creamos una instancia de Asignatura
			Asignatura asignatura = new Asignatura(nombre, nota, PnlEntrar.idAlumn);

			// Agregamos la instancia a la lista
			asignaturas.add(asignatura);
		}
		return asignaturas;
	}

	public void mostrarPrimero() {
		// Obtenemos la primera asignatura de la lista
		Asignatura primera = asignaturas.getFirst();

		// Colocamos el nombre en la etiqueta nombre
		PnlVDetalle.lblAsignatura.setText(primera.getNombre());

		// Colocamos la nota en el txtNota
		PnlVDetalle.txtNota.setText(primera.getNota().toString());

		// Ponemos la posicion a 0
		pos = 0;
	}

	public void mostrarUltimo() {
		// Obtenemos el ultimo registro de asignaturas
		Asignatura ultima = asignaturas.getLast();

		// Colocamos el nombre en la etiqueta nombre
		PnlVDetalle.lblAsignatura.setText(ultima.getNombre());

		// Colocamos la nota en el txtNota
		PnlVDetalle.txtNota.setText(ultima.getNota().toString());

		// Ponemos la posicion al tamaño de la lista
		pos = asignaturas.size() - 1;

	}

	public void mostrarSiguiente() {
		if (pos < asignaturas.size() - 1) {
			// Incrementamos la posicion y obtenemos el registro
			pos++;
			Asignatura a = asignaturas.get(pos);

			// Colocamos el nombre en la etiqueta nombre
			PnlVDetalle.lblAsignatura.setText(a.getNombre());

			// Colocamos la nota en el txtNota
			PnlVDetalle.txtNota.setText(a.getNota().toString());
		}
	}

	public void mostrarAnterior() {
		if (pos > 0) {
			// Decrementamos la posicion y obtenemos el registro
			pos--;
			Asignatura a = asignaturas.get(pos);

			// Colocamos el nombre en la etiqueta nombre
			PnlVDetalle.lblAsignatura.setText(a.getNombre());

			// Colocamos la nota en el txtNota
			PnlVDetalle.txtNota.setText(a.getNota().toString());
		}
	}

	public void guardarNota(String notaAntigua, JTextField txtNota) {
		try {
			// Intentamos parsear el txt a Double
			Double notaNueva = Double.parseDouble(PnlVDetalle.txtNota.getText());
			
			//Si la nota es diferente a la anterior se actualiza
			if (notaNueva != Double.parseDouble(notaAntigua)) {
				String sql = "UPDATE asignatura" + " SET nota = " + notaNueva + " WHERE aluNumero = " + PnlEntrar.idAlumn
						+ " AND nombre = '" + PnlVDetalle.lblAsignatura.getText() + "';";
				Conn.open();
				Conn.executeIDU(sql);
				JOptionPane.showMessageDialog(null, "Nota actualizada"); //Se indica al usuario
				Conn.close();
				
				//Actualizamos la de la lista también para que se muestre bien
				Asignatura a = asignaturas.get(pos);
				a.setNota(notaNueva);
			} else {
				JOptionPane.showMessageDialog(null, "El valor introducido es el mismo que el anterior");
			}
		} catch (SQLException e) {
			// Si hay algun error se le indica al usuario a través de un JOptionPane
			JOptionPane.showMessageDialog(null, "Ha habido un error al actualizar la nota " + e.getMessage());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El valor introducido no es válido (Ex: 7.5)");
		} finally {
			// Cerramos la conexión
			try {
				Conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ha habido un error al cerrar la conexion con la DB. ", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	// Comprobamos los botones de la vista
	public void comprobarBotonesVista(JButton btnPrimero, JButton btnAnterior, JButton btnSiguiente,
			JButton btnUltimo, JButton btnGuardar) {

		// Si la posicion es mayor o igual al tamaño de la lista, el boton de siguiente
		// y ultimo se desactivan
		if (pos >= asignaturas.size() - 1) {
			btnSiguiente.setEnabled(false);
			btnUltimo.setEnabled(false);
		} else {
			// Si no el boton de siguiente y ultimo se activan
			btnSiguiente.setEnabled(true);
			btnUltimo.setEnabled(true);
		}

		// Si la posicion es menor o igual a cero, el boton anterior y primero se
		// desactivan, si no, se activan
		if (pos <= 0) {
			btnAnterior.setEnabled(false);
			btnPrimero.setEnabled(false);
		} else {
			btnAnterior.setEnabled(true);
			btnPrimero.setEnabled(true);
		}
		
		//Desactivamos el btnGuardar
		btnGuardar.setEnabled(false);

	}

}
