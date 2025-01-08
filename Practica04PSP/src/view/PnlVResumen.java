package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.toedter.calendar.JCalendar;
import javax.swing.JList;

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
	
	public PnlVResumen() {
		setLayout(new BorderLayout(0, 0));
				
		initComponents();
		
		addListeners();
		
	}

	private void addListeners() {
		
		//Le damos funcionalidad al btnNotaMedia
		btnNotaMedia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Double media = 7.2; //Valor de prueba, quitar luego
				//TODO
				
				//Una vez tengamos el cálculo de la media se lo asignamos a lblNotaMediaMostrada
				lblNotaMediaMostrada.setText(media.toString());
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
			
			//Cambiar estos dos elementos por los reales
			String password = "Password";
			String passwordAsteriscada = "********";
			
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
					String fd = df.format(seleccionada);
					
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
		String ruta = "user.png";
		ImageIcon icon = new ImageIcon(ruta);
		lblFoto = new JLabel(icon);
		add(lblFoto, BorderLayout.WEST);
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 0, 5));
		
		lblID = new JLabel("ID");
		panel.add(lblID);
		
		lblIdMostrado = new JLabel("ID mostrado");
		panel.add(lblIdMostrado);
		
		lblUsuario = new JLabel("Usuario");
		panel.add(lblUsuario);
		
		lblUsuarioMostrado = new JLabel("Usuario mostrado");
		panel.add(lblUsuarioMostrado);
		
		lblPassword = new JLabel("Contraseña");
		panel.add(lblPassword);
		
		lblPasswordMostrada = new JLabel("*****");
		panel.add(lblPasswordMostrada);
		
		lblFechaNacimiento = new JLabel("Fecha de Nacimiento");
		panel.add(lblFechaNacimiento);
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblFechaNacimientoMostrada = new JLabel("FechaNacimiento");
		panel_1.add(lblFechaNacimientoMostrada);
		
		btnCambiar = new JButton("Cambiar");
		panel_1.add(btnCambiar);
		
		lblNotaMedia = new JLabel("Nota media");
		panel.add(lblNotaMedia);
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		
		lblNotaMediaMostrada = new JLabel("Nota Media");
		lblNotaMediaMostrada.setVisible(false);
		panel_2.add(lblNotaMediaMostrada);
		
		btnNotaMedia = new JButton("Calcular Nota Media");
		panel_2.add(btnNotaMedia);
		
		list = new JList();
		add(list, BorderLayout.EAST);
	}
}
