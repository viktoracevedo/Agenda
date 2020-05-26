package paquete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlogCalendar extends JDialog {
    private JButton btnIzquierda;
    private JButton btnDerecha;
    private JButton btnAceptar;
    private JPanel panel;
    private Calendario calendar;
    private JLabel lbl;
    private JPanel panelcalendar;
    private String mensaje;

    public DlogCalendar(JDialog owner, String title, boolean modal, Calendario calendar) {
        super(owner, title, modal);
        mensaje = "";
        this.calendar = calendar;
        this.setSize(600, 200);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        btnIzquierda = new JButton("<");
        btnDerecha = new JButton(">");
        btnAceptar = new JButton("Aceptar");
        lbl = new JLabel(calendar.getNameMesSeleccionado());
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(btnIzquierda);
        panel.add(lbl);
        panel.add(btnDerecha);
        panel.add(btnAceptar);
        panelcalendar = new JPanel();
        panelcalendar.add(calendar.getTablaMesSeleccionado());
        panel.add(panelcalendar);
        this.add(panel);
        //pack();
        initListener();
    }

    public String getMensaje() {
        return mensaje;
    }

    private void initListener() {
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensaje = TablaMes.Dia + " de " +lbl.getText();
                DlogCalendar.this.setVisible(false);
            }
        });
        btnIzquierda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(4);
                calendar.moverAlIzquierda();
                panelcalendar.remove(0);
                panelcalendar.add(calendar.getTablaMesSeleccionado());
                panel.add(panelcalendar);
                lbl.setText(calendar.getNameMesSeleccionado());
            }
        });
        btnDerecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(4);
                calendar.moverAlDerecha();
                panelcalendar.remove(0);
                panelcalendar.add(calendar.getTablaMesSeleccionado());
                panel.add(panelcalendar);
                lbl.setText(calendar.getNameMesSeleccionado());
            }
        });
    }
}
