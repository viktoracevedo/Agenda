package paquete;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlogChooseColor extends JDialog {
    private JPanel Panel;
    private JSpinner spinR;
    private JSpinner spinG;
    private JSpinner spinB;
    private JSlider sliderR;
    private JSlider sliderG;
    private JSlider sliderB;
    private JButton btnAceptar;
    private JPanel panelColoreado;

    private Color colorMarcador;

    public DlogChooseColor(JDialog owner, boolean modal) {
        super(owner, modal);
        this.setTitle("Escoge el Color");
        this.setLocationRelativeTo(null);
        //this.setSize(500,300);
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DlogChooseColor.this.setVisible(false);
            }
        });
        spinR = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        spinG = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        spinB = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
        sliderR = new JSlider(JSlider.HORIZONTAL,0,255,0);
        sliderG = new JSlider(JSlider.HORIZONTAL,0,255,0);
        sliderB = new JSlider(JSlider.HORIZONTAL,0,255,0);
        spinR.addChangeListener(new ColorChangeListener());
        spinG.addChangeListener(new ColorChangeListener());
        spinB.addChangeListener(new ColorChangeListener());
        sliderR.addChangeListener(new ColorChangeListener());
        sliderG.addChangeListener(new ColorChangeListener());
        sliderB.addChangeListener(new ColorChangeListener());

        panelColoreado = new JPanel();
        colorMarcador = panelColoreado.getBackground();
        panelColoreado.setPreferredSize(new Dimension(120,120));
        panelColoreado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel panelBotones = new JPanel();
        //panelBotones.setPreferredSize(new Dimension(100,100));
        panelBotones.setBorder(BorderFactory.createRaisedBevelBorder());
        panelBotones.add(spinR);
        panelBotones.add(sliderR);
        panelBotones.add(spinG);
        panelBotones.add(sliderG);
        panelBotones.add(spinB);
        panelBotones.add(sliderB);
        panelBotones.add(btnAceptar);
        JPanel panelPrincipal = new JPanel();
        //panelBotones.setLayout(new BorderLayout());
        panelBotones.setPreferredSize(new Dimension(300,120));
        panelPrincipal.add(panelColoreado, BorderLayout.EAST);
        panelPrincipal.add(panelBotones, BorderLayout.WEST);
        this.add(panelPrincipal);
        pack();
        repaint();
    }

    public Color getColorMarcador() {
        return colorMarcador;
    }

    private class ColorChangeListener implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {
            if(e.getSource().equals(spinR)){
                sliderR.setValue((Integer) spinR.getValue());
            }else if(e.getSource().equals(spinG)){
                sliderG.setValue((Integer) spinG.getValue());
            }else if(e.getSource().equals(spinB)){
                sliderB.setValue((Integer) spinB.getValue());
            }else if(e.getSource().equals(sliderR)){
                spinR.setValue(sliderR.getValue());
            }else if(e.getSource().equals(sliderG)){
                spinG.setValue(sliderG.getValue());
            }else if(e.getSource().equals(sliderB)){
                spinB.setValue(sliderB.getValue());
            }
            panelColoreado.setBackground(new Color(sliderR.getValue(), sliderG.getValue() ,sliderB.getValue()));
            colorMarcador = panelColoreado.getBackground();
        }
    }


}