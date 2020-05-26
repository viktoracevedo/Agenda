package paquete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Agenda extends JFrame {

    private JMenu btnIzquierda;
    private JMenu btnDerecha;
    private JMenu btnConfiguracion;
    private JMenu btnMostrarMesSeleccionado;
    private JMenu btnMostrarEventos;
    //private JMenu vacio;
    private JMenu btnAgregarEvento;
    private Calendario calendario;
    private DlogMostrarFormulario eventosVentana;
    private View[] arrayViews;
    private JDialog formularioVentana;
    private JMenuBar barra;
    public Agenda() throws HeadlessException {
        this.setTitle("JAgenda");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        initCalendario();

        //initViews();

        initMostrarFormularios();

        initFormulario();

        initListeners();
    }

    private void initMostrarFormularios() {
        eventosVentana = new DlogMostrarFormulario(this, true);
        JMenu vacio = new JMenu();
        vacio.setPreferredSize(new Dimension(150, 30));
        barra.add(vacio);
        btnMostrarEventos = new JMenu("Mostrar Eventos");
        barra.add(btnMostrarEventos);

    }

    private void initFormulario() {
        formularioVentana = new DlogFormulario(this, true, calendario);
        btnAgregarEvento = new JMenu("Agregar Evento");
        barra.add(btnAgregarEvento);

    }

    private void initViews() {
        arrayViews = new View[12];
        JScrollPane scrollVentana = new JScrollPane();
        JPanel pnlDefinitivo = new JPanel();
        //pnlDefinitivo.setLayout(new GridLayout(12,1,10,10));
        for (int i = 0; i < arrayViews.length; i++) {
            arrayViews[i] = new View();
            pnlDefinitivo.add(arrayViews[i]);
        }
            scrollVentana.setViewportView(pnlDefinitivo);
        this.add(scrollVentana, BorderLayout.CENTER);
    }

    private void initCalendario() {
        calendario = new Calendario();

        btnMostrarMesSeleccionado = new JMenu();
        btnMostrarMesSeleccionado.setPreferredSize(new Dimension(110, 30));
        btnMostrarMesSeleccionado.setText(calendario.getNameMesSeleccionado());
        btnMostrarMesSeleccionado.add(calendario.getTablaMesSeleccionado());

        btnIzquierda = new JMenu("<");

        btnDerecha = new JMenu(">");

        btnConfiguracion = new JMenu("...");
        btnConfiguracion.setPreferredSize(new Dimension(30,30));

        barra = new JMenuBar();
        barra.add(btnConfiguracion);
        barra.add(btnIzquierda);
        barra.add(btnMostrarMesSeleccionado);
        barra.add(btnDerecha);
        this.setJMenuBar(barra);

    }


    private void initListeners() {
        btnIzquierda.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnMostrarMesSeleccionado.removeAll();
                calendario.moverAlIzquierda();
                btnMostrarMesSeleccionado.add(calendario.getTablaMesSeleccionado());
                btnMostrarMesSeleccionado.setText(calendario.getNameMesSeleccionado());
            }
        });
        btnDerecha.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnMostrarMesSeleccionado.removeAll();
                calendario.moverAlDerecha();
                btnMostrarMesSeleccionado.add(calendario.getTablaMesSeleccionado());
                btnMostrarMesSeleccionado.setText(calendario.getNameMesSeleccionado());
            }
        });
        btnAgregarEvento.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                formularioVentana.setVisible(true);
            }
        });
        btnMostrarEventos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(eventosVentana.isVacio()){
                   JOptionPane.showMessageDialog(null, "NO TIENES EVENTOS", "WARNING", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    eventosVentana.setVisible(true);

                }
            }
        });
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        agenda.setVisible(true);
    }
}
