package view;

import javax.swing.*;
import java.awt.*;

public class PnlAcercaDe extends JPanel {
    private JLabel lblAcercaDe;
    private JLabel lblAcercaAntonio;
    private JLabel lblAcercaManuel;
    private JLabel lblImgAntonio;
    private JLabel lblImgManuel;
    private Image imageAntonio;
    private Image imageManuel;
    private JPanel pnlAcercaDe;

    public PnlAcercaDe() {
        setLayout(new BorderLayout(0,0));

        initComponents();

        addListeners();
    }
    public void initComponents(){
        //Panel
        pnlAcercaDe = new JPanel();
        pnlAcercaDe.setLayout(new BorderLayout(0,0));

        JPanel pnlAcerca1 = new JPanel();
        pnlAcerca1.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));

        JPanel pnlAcerca2 = new JPanel();
        pnlAcerca2.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));

        lblAcercaDe = new JLabel("Hecho por:");
        lblAcercaDe.setFont(new Font("Tahoma",Font.BOLD, 20));
        lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
        pnlAcercaDe.add(lblAcercaDe, BorderLayout.NORTH);

        Image imagenAntonio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconAntonio.jpg"));
        imagenAntonio = imagenAntonio.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        lblImgAntonio = new JLabel(new ImageIcon(imagenAntonio));
        lblAcercaAntonio = new JLabel("Antonio Manuel Guerrero Pulgada");
        lblAcercaAntonio.setFont(new Font("Tahoma",Font.BOLD, 20));
        pnlAcerca1.add(lblImgAntonio);    // Imagen de Antonio
        pnlAcerca1.add(lblAcercaAntonio); // Texto de Antonio

        Image imagenManuel = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconAntonio.jpg"));
        imagenManuel = imagenManuel.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        lblImgManuel= new JLabel(new ImageIcon(imagenManuel));
        lblAcercaManuel = new JLabel("Manuel Borrero Guerrero");
        lblAcercaManuel.setFont(new Font("Tahoma",Font.BOLD, 20));
        pnlAcerca2.add(lblImgManuel);    // Imagen de Antonio
        pnlAcerca2.add(lblAcercaManuel); // Texto de Antonio


        pnlAcercaDe.add(pnlAcerca1, BorderLayout.SOUTH);
        pnlAcercaDe.add(pnlAcerca2, BorderLayout.CENTER);

        add(pnlAcercaDe, BorderLayout.CENTER);
    }
    public void addListeners(){

    }
}
