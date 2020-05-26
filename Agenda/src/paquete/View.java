package paquete;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private JLabel[] lblEventos;
    private JPanel[] pnlEventos;
    private int tamanio;
    public View() throws HeadlessException {
        initDatos();
        initDisenio();

    }

    private void initDatos() {
        tamanio = 8;
        lblEventos = new JLabel[tamanio];
        pnlEventos = new JPanel[tamanio];
        lblEventos[0] = new JLabel("hola");
        lblEventos[1] = new JLabel("como");
        lblEventos[2] = new JLabel("estas");
        lblEventos[3] = new JLabel("yo muy bien");
        lblEventos[4] = new JLabel("yo muy bien");
        lblEventos[5] = new JLabel("yo muy bien");
        lblEventos[6] = new JLabel("yo muy bien");
        lblEventos[7] = new JLabel("yo muy bien");
        for (int i = 0; i < pnlEventos.length; i++) {
            pnlEventos[i] = new JPanel();
            pnlEventos[i].setBorder(BorderFactory.createLoweredSoftBevelBorder());
            pnlEventos[i].add(lblEventos[i]);
        }


    }

    private void initDisenio() {
        JPanel panelNorte = new JPanel();
        panelNorte.setBorder(BorderFactory.createRaisedBevelBorder());
        panelNorte.setLayout(new FlowLayout());
        JLabel lblimagen = new JLabel(new ImageIcon(getClass().getResource("/imagenes/d.jpg")));//D
        panelNorte.add(lblimagen);

        JPanel miniPanelIzquierdo = new JPanel();
        miniPanelIzquierdo.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        miniPanelIzquierdo.setLayout(new FlowLayout());
        miniPanelIzquierdo.add(new JLabel("holakdedledkedkedkedke"));

        JPanel miniPanelEventos = new JPanel();
        miniPanelEventos.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        miniPanelEventos.setLayout(new GridLayout(tamanio,1, 10,10));
        for (JPanel i : pnlEventos) {
            miniPanelEventos.add(i);
        }
        JScrollPane scrollPaneEventos = new JScrollPane();
        scrollPaneEventos.setViewportView(miniPanelEventos);


        JPanel panelSur = new JPanel();
        panelSur.setBorder(BorderFactory.createRaisedBevelBorder());
        panelSur.setLayout(new BorderLayout(5,10));
        panelSur.add(miniPanelIzquierdo, BorderLayout.WEST);
        panelSur.add(scrollPaneEventos, BorderLayout.CENTER);


        this.setLayout(new BorderLayout(5,5));
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        this.setPreferredSize(new Dimension(500,400));
        this.add(panelNorte,BorderLayout.NORTH);
        this.add(panelSur, BorderLayout.CENTER);

    }


}
