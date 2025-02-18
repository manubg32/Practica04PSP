package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import controller.CtrlVResumen;
import model.Alumno;
import model.Asignatura;

public class PnlVResumen extends JPanel {

	private JLabel lblFoto;
	private JLabel lblID;
	private JLabel lblIdMostrado;
	private JLabel lblUsuario;
	private JLabel lblUsuarioMostrado;
	private JLabel lblPassword;
	private JLabel lblPasswordMostrada;
	private JLabel lblFechaNacimiento;
	private JLabel lblNotaMedia;
	private JPanel panel_1;
	private JLabel lblFechaNacimientoMostrada;
	private JButton btnCambiar;

	private JPanel panel;
	private JPanel panel_2;
	private JLabel lblNotaMediaMostrada;
	private JButton btnNotaMedia;
	private JList list;

	private CtrlVResumen ctrlResumen = new CtrlVResumen();
	private Double notaMedia = 0d;
	public PnlVResumen() {
		setLayout(new BorderLayout(0, 0));
		initComponents();
		addListeners();

        ctrlResumen.obtenerAlumno(PnlEntrar.idAlumn);
        ctrlResumen.obtenerAsignaturas(PnlEntrar.idAlumn);
    }

	private void addListeners() {

		//Le damos funcionalidad al btnNotaMedia
		btnNotaMedia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
				notaMedia = ctrlResumen.notaMedia(PnlEntrar.idAlumn);
                //Una vez tengamos el cálculo de la media se lo asignamos a lblNotaMediaMostrada
				lblNotaMediaMostrada.setText(notaMedia.toString());
				lblNotaMediaMostrada.setHorizontalAlignment(SwingConstants.CENTER);

				//Quitamos el elemento btnNotaMedia y agregamos el lblNotaMediaMostrada
				btnNotaMedia.setVisible(false);
				lblNotaMediaMostrada.setVisible(true);

				//Actualizamos el panel
				panel.revalidate();
				panel.repaint();
			}
		});

		//Cuando pasemos el raton sobre la contraseña asteriscada, esta se mostrará
		lblPasswordMostrada.addMouseListener(new MouseAdapter() {
			Alumno a = ctrlResumen.obtenerAlumno(PnlEntrar.idAlumn);;
			//Cambiar estos dos elementos por los reales
			String password = a.getPassword();
			String passwordAsteriscada = getStringAsteriscos(password);

			@Override
			public void mousePressed(MouseEvent e) {
				lblPasswordMostrada.setText(password);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				lblPasswordMostrada.setText(passwordAsteriscada);
			}
		});

		//Cuando clickemos sobre el btnCambiar, se nos abrirá un datePickerDialog para seleccionar la fecha
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Creamos el JDialog
				JDialog dialog = new JDialog();
				dialog.setSize(300, 250);
				dialog.getContentPane().setLayout(new BorderLayout());

				//Instanciamos un JCalendar y un boton para seleccionar
				JCalendar calendar = new JCalendar();
				JButton boton = new JButton("Seleccionar");

				//Al boton le damos funcionalidad
				boton.addActionListener(event -> {

					//Cogemos la fecha que esté marcada en el JCalendar
					Date seleccionada = calendar.getDate();

					//Le damos formato
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String fd = df.format(ctrlResumen.updateFecha(seleccionada, PnlEntrar.idAlumn));

					//Y se la ponemos como texto al Label
					lblFechaNacimientoMostrada.setText(fd);
					dialog.dispose();
				});

				dialog.getContentPane().add(calendar, BorderLayout.CENTER);
				dialog.getContentPane().add(boton, BorderLayout.SOUTH);

				dialog.setLocationRelativeTo(btnCambiar);
				dialog.setVisible(true);

			}
		});

	}

	private void initComponents() {

		Alumno a = ctrlResumen.obtenerAlumno(PnlEntrar.idAlumn);

		String ruta = a.getIdFoto();
		ImageIcon icon = new ImageIcon(ruta);
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		
		lblFoto = new JLabel(scaledIcon);
		add(lblFoto, BorderLayout.WEST);

		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 0, 5));

		lblID = new JLabel("ID");
		panel.add(lblID);

		lblIdMostrado = new JLabel(a.getId().toString());
		panel.add(lblIdMostrado);

		lblUsuario = new JLabel("Usuario");
		panel.add(lblUsuario);

		lblUsuarioMostrado = new JLabel(a.getUser());
		panel.add(lblUsuarioMostrado);

		lblPassword = new JLabel("Contraseña");
		panel.add(lblPassword);

		lblPasswordMostrada = new JLabel(getStringAsteriscos(a.getPassword()));
		panel.add(lblPasswordMostrada);

		lblFechaNacimiento = new JLabel("Fecha de Nacimiento");
		panel.add(lblFechaNacimiento);

		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Formato para mostrar la fecha.
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaDate = a.getBirthday().getTime();
		String fechaFormateada = df.format(fechaDate);

		lblFechaNacimientoMostrada = new JLabel(fechaFormateada);
		panel_1.add(lblFechaNacimientoMostrada);

		btnCambiar = new JButton("Cambiar");
		panel_1.add(btnCambiar);

		lblNotaMedia = new JLabel("Nota media");
		panel.add(lblNotaMedia);

		panel_2 = new JPanel();
		panel.add(panel_2);

		lblNotaMediaMostrada = new JLabel(notaMedia.toString());
		lblNotaMediaMostrada.setVisible(false);
		panel_2.add(lblNotaMediaMostrada);

		btnNotaMedia = new JButton("Calcular");
		panel_2.add(btnNotaMedia);

		// Creamos la lista para el JList y añadimos las asignaturas del alumno.
		DefaultListModel listModel = new DefaultListModel();
		List<Asignatura> listaAS = ctrlResumen.obtenerAsignaturas(PnlEntrar.idAlumn);
		for (Asignatura asignatura : listaAS) {
			listModel.addElement(asignatura);
		}

		JList<Asignatura> list = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setPreferredSize(new Dimension(200,50));

		add(scrollPane, BorderLayout.EAST);


	}

	// Este metodo es para colocar asteriscos dependiendo de cuantos caracteres tenga la contraseña
	private String getStringAsteriscos(String password){
		String asteriscos = "";
		for(int i=0; i<password.length(); i++){
			asteriscos +="*";
		}
		return asteriscos;
	}
}
