package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class PnlAcercaDe extends JPanel {
    private JLabel lblAcercaDe;
    private JLabel lblAcercaAntonio;
    private JLabel lblAcercaManuel;
    private JLabel lblImgAntonio;
    private JLabel lblImgManuel;
    private Image imageAntonio;
    private Image imageManuel;
    private JPanel pnlAcercaDe;
    private JPanel pnlInformacion;
    private JPanel pnlBoton;
    private JButton btnAceptar;

    public PnlAcercaDe(JDialog dialog) {
        setLayout(new BorderLayout(0,0));

        initComponents();

        addListeners(dialog);
    }
    public void initComponents(){
    	
        //Panel principal
        pnlAcercaDe = new JPanel();
        pnlAcercaDe.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        pnlAcercaDe.setLayout(new BorderLayout(0,0));

        //Panel para la Informacion de Antonio
        JPanel pnlAcerca1 = new JPanel();
        pnlAcerca1.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));

        //Panel para la Informacion de Manuel
        JPanel pnlAcerca2 = new JPanel();
        pnlAcerca2.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));

        //Etiqueta con Hecho por:
        lblAcercaDe = new JLabel("Trabajo realizado por:");
        lblAcercaDe.setFont(new Font("Tahoma",Font.BOLD, 20));
        lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
        
        //Agregamos la etiqueta a la parte norte del panel principal
        pnlAcercaDe.add(lblAcercaDe, BorderLayout.NORTH);
        
        //Creamos un panel auxiliar que colocar en el centro del panel principal con la informacion de los colaboradores
        pnlInformacion = new JPanel();
        pnlInformacion.setLayout(new BorderLayout(0,0));
        
        //Colocamos el panel auxiliar en el centro del panel principal
        pnlAcercaDe.add(pnlInformacion, BorderLayout.CENTER);

        //Obtenemos la Imagen de GitHub de Antonio y una etiqueta con su informacion
        Image imagenAntonio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconAntonio.jpg"));
        imagenAntonio = imagenAntonio.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        lblImgAntonio = new JLabel(new ImageIcon(imagenAntonio));
        lblAcercaAntonio = new JLabel("Antonio Manuel Guerrero Pulgada");
        lblAcercaAntonio.setFont(new Font("Tahoma",Font.BOLD, 20));
        pnlAcerca1.add(lblImgAntonio);    // Imagen de Antonio
        pnlAcerca1.add(lblAcercaAntonio); // Texto de Antonio

        //Obtenemos la Imagen de GitHub de Manuel y una etiqueta con su informacion
        Image imagenManuel = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconManuel.png"));
        imagenManuel = imagenManuel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        lblImgManuel= new JLabel(new ImageIcon(imagenManuel));
        lblAcercaManuel = new JLabel("Manuel Borrero Guerrero");
        lblAcercaManuel.setFont(new Font("Tahoma",Font.BOLD, 20));
        pnlAcerca2.add(lblImgManuel);    // Imagen de Manuel
        pnlAcerca2.add(lblAcercaManuel); // Texto de Manuel
        
      //Agreagaos la informacion sobre Antonio y Manuel al panel Auxiliar
        pnlInformacion.add(pnlAcerca1, BorderLayout.NORTH);
        pnlInformacion.add(pnlAcerca2, BorderLayout.CENTER);

        //Creamos el ultimo panel para colocar el boton que cerrará el JDialog
        pnlBoton = new JPanel();
        pnlAcercaDe.add(pnlBoton, BorderLayout.WEST);
        btnAceptar = new JButton("Aceptar");
        pnlBoton.add(btnAceptar);

        //Colocamos en el sur del panel principal el panel con el boton
        pnlAcercaDe.add(pnlBoton, BorderLayout.SOUTH);

        add(pnlAcercaDe, BorderLayout.CENTER);
        
    }
    public void addListeners(JDialog dialog){
    	 btnAceptar.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		dialog.dispose();
         	}
         });
    }
}
