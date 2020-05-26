package paquete;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class DlogFormulario extends JDialog {
    public static final String[] TEXTOS = {"Agrega Un Titulo", "Fecha", "Agrega un Horario", "Agrega Un Lugar",
            "Agrega un Correo", "Agrega Personas","Agregar Telefonos","Agrega Una Descripcion","Agrega Un Color al Evento"};
    public static final int INDEX = 9;
    private JPanel[] arrayPanels;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JButton btnReset;

    private DlogCalendar calendaeVentana;
    private DlogChooseColor colorVentana;

    private Formulario data;
    private int[] fecha;
    private int[] horaInicio;
    private int[] horaFinal;
    private boolean todoElDia;
    private String[] valoresAceptados;

    private JTextField txtTitulo;
    private JButton btnFecha;
    private JLabel lblTiempo;
    private JTextField txtLugar;
    private JTextField txtCorreo;
    private JComboBox cmbPersona;
    private JComboBox cmbTelefono;
    private JTextArea txtAreaDescripcion;
    private JButton btnMarca;
    private JLabel lblFecha;
    private JPanel panelcoloreado;
    public DlogFormulario(Frame owner, boolean modal, Calendario calendar) {
        super(owner, modal);
        data = new Formulario();
        fecha = new int[3];
        horaInicio = new int[2];
        horaFinal = new int[2];
        todoElDia = false;
        this.setTitle("Agregar Evento");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        calendaeVentana = new DlogCalendar(this , "Calendario", true, calendar);
        colorVentana = new DlogChooseColor(this , true);

        arrayPanels = new JPanel[INDEX];
        valoresAceptados = new String[4];
        for (int i = 0; i < arrayPanels.length; i++) {
            arrayPanels[i] = new JPanel();
            arrayPanels[i].setBorder(BorderFactory.createTitledBorder(TEXTOS[i]));
            fecha[0] = TablaMes.Dia;
            fecha[1] = TablaMes.Mes;
            fecha[2] = TablaMes.Anio;
        }

        initBonotesDeControl();
        initAgregaUnTitulo();
        initFecha();
        initAgregaUnHorario();
        initAgregaUnLugar();
        initAgregaunCorreo();
        initAgregaPersonas();
        initAgregarTelefonos();
        initAgregaUnaDescripcion();
        initAgregaUnaMarcaAlEvento();

        //panelPrincipal.setLayout(new GridLayout(INDEX,1,5,10));
        JPanel panelSecundario = new JPanel();
        panelSecundario.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        panelSecundario.add(btnAceptar);
        panelSecundario.add(btnCancelar);
        panelSecundario.add(btnReset);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setPreferredSize(new Dimension(460,700));
        for (JPanel i : arrayPanels) {
            panelPrincipal.add(i);
        }
        JScrollPane scrollpanel = new JScrollPane();
        scrollpanel.setViewportView(panelPrincipal);

        this.setLayout(new BorderLayout());
        this.add(scrollpanel, BorderLayout.CENTER);
        this.add(panelSecundario, BorderLayout.SOUTH);

        this.addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                lblFecha.setText(TEXTOS[1] + ": " + calendaeVentana.getMensaje());

                panelcoloreado.setBackground(colorVentana.getColorMarcador());
            }
        });

        txtTitulo.addFocusListener(new FocusLostListener());
        //btnFecha.addFocusListener(new FocusLostListener());
        //lblTiempo.addFocusListener(new FocusLostListener());
        txtLugar.addFocusListener(new FocusLostListener());
        txtCorreo.addFocusListener(new FocusLostListener());
        cmbPersona.addFocusListener(new FocusLostListener());
        cmbTelefono.addFocusListener(new FocusLostListener());
        txtAreaDescripcion.addFocusListener(new FocusLostListener());
        btnMarca.addFocusListener(new FocusLostListener());
        //pack();
        repaint();
    }

    private void initBonotesDeControl() {
        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ButtonsListener());
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ButtonsListener());
        btnReset = new JButton("Reset");
        btnReset.addActionListener(new ButtonsListener());
    }

    private void initAgregaUnTitulo() {
        txtTitulo = new JTextField(TEXTOS[0]);
        txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        txtTitulo.setFont(new Font("Calibri Light" ,Font.BOLD, 20));
        txtTitulo.setForeground(Color.GRAY);
        txtTitulo.setPreferredSize(new Dimension(450,30));

        arrayPanels[0].add(txtTitulo);

    }

    private void initFecha() {
        btnFecha = new JButton();
        btnFecha.setFont(new Font("Calibri Light" ,Font.PLAIN, 10));
        btnFecha.setText("Calendario");
        lblFecha = new JLabel(TEXTOS[1]);
        lblFecha.setPreferredSize(new Dimension(150,30));
        JPanel miniPanel = new JPanel();
        miniPanel.add(btnFecha);
        miniPanel.add(lblFecha);
        btnFecha.addActionListener(new ButtonsListener());
        arrayPanels[1].add(miniPanel);
    }

    private void initAgregaUnHorario(){
        JSpinner spinHoraInicio = new JSpinner(new SpinnerNumberModel(12,0,12,1));
        JSpinner spinMinutosInicio = new JSpinner(new SpinnerNumberModel(0,0,59,1));
        JSpinner spinHoraFinal = new JSpinner(new SpinnerNumberModel(12,0,12,1));
        JSpinner spinMinutosFinal = new JSpinner(new SpinnerNumberModel(0,0,59,1));

        JCheckBox chkTodoElDia = new JCheckBox("Activar Todo el dia");
        chkTodoElDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkTodoElDia.isSelected()){
                    spinHoraInicio.setEnabled(false);
                    spinMinutosInicio.setEnabled(false);
                    spinHoraFinal.setEnabled(false);
                    spinMinutosFinal.setEnabled(false);
                    todoElDia = true;
                }else{
                    spinHoraInicio.setEnabled(true);
                    spinMinutosInicio.setEnabled(true);
                    spinHoraFinal.setEnabled(true);
                    spinMinutosFinal.setEnabled(true);
                    todoElDia = false;
                }
            }
        });
        lblTiempo = new JLabel("12:00am. - 12:00pm.");
        lblTiempo.setPreferredSize(new Dimension(120,20));
        JPanel panelTiempo = new JPanel();
        panelTiempo.setLayout(new FlowLayout());
        panelTiempo.setPreferredSize(new Dimension(300,50));
        panelTiempo.add(spinHoraInicio);
        panelTiempo.add(spinMinutosInicio);
        panelTiempo.add(spinHoraFinal);
        panelTiempo.add(spinMinutosFinal);
        panelTiempo.add(lblTiempo);
        panelTiempo.add(chkTodoElDia);
        arrayPanels[2].add(panelTiempo);

        class SpinersChangeListener implements ChangeListener{
            @Override
            public void stateChanged(ChangeEvent e) {
                String mensaje = "";
                if(e.getSource().equals(spinHoraInicio) || e.getSource().equals(spinMinutosInicio)){
                    mensaje += spinHoraInicio.getValue() + ":" + spinMinutosInicio.getValue() + "am. - ";
                    mensaje += spinHoraFinal.getValue() + ":" + spinMinutosFinal.getValue() + "pm.";

                }else if(e.getSource().equals(spinHoraFinal) || e.getSource().equals(spinMinutosFinal)){
                    mensaje += spinHoraInicio.getValue() + ":" + spinMinutosInicio.getValue() + "am. - ";
                    mensaje += spinHoraFinal.getValue() + ":" + spinMinutosFinal.getValue() + "pm.";
                }
                horaInicio[0] = (int) spinHoraInicio.getValue();
                horaInicio[1] = (int) spinMinutosInicio.getValue();
                horaFinal[0] = (int) spinHoraFinal.getValue();
                horaFinal[1] = (int) spinMinutosFinal.getValue();
                lblTiempo.setText(mensaje);
            }
        }
        spinHoraInicio.addChangeListener(new SpinersChangeListener());
        spinMinutosInicio.addChangeListener(new SpinersChangeListener());
        spinHoraFinal.addChangeListener(new SpinersChangeListener());
        spinMinutosFinal.addChangeListener(new SpinersChangeListener());
    }



    private void initAgregaUnLugar(){
        txtLugar = new JTextField(TEXTOS[3]);
        txtLugar.setHorizontalAlignment(SwingConstants.CENTER);
        txtLugar.setFont(new Font("Calibri Light" ,Font.PLAIN, 20));
        txtLugar.setForeground(Color.GRAY);
        txtLugar.setPreferredSize(new Dimension(400,30));
        arrayPanels[3].add(txtLugar);
    }

    private void initAgregaunCorreo(){
        txtCorreo = new JTextField(TEXTOS[4]);
        txtCorreo.setHorizontalAlignment(SwingConstants.CENTER);
        txtCorreo.setFont(new Font("Calibri Light" ,Font.PLAIN, 20));
        txtCorreo.setForeground(Color.GRAY);
        txtCorreo.setPreferredSize(new Dimension(200,30));
        arrayPanels[4].add(txtCorreo);
    }

    private void initAgregaPersonas(){
        cmbPersona = new JComboBox();
        cmbPersona.addItem("");
        cmbPersona.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cmbPersona.getSelectedItem().equals(e.getItem())){
                    cmbPersona.removeItem("");
                    //System.out.println("entro");
                    String item = e.getItem().toString();
                    cmbPersona.addItem(item);
                    cmbPersona.setSelectedItem("");
                }
            }
        });
        cmbPersona.setEditable(true);
        cmbPersona.setPreferredSize(new Dimension(200,30));
        arrayPanels[5].add(cmbPersona);
    }

    private void initAgregarTelefonos(){
        cmbTelefono = new JComboBox();
        cmbTelefono.addItem("");
        cmbTelefono.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cmbTelefono.getSelectedItem().equals(e.getItem())){
                    cmbTelefono.removeItem("");
                    //System.out.println("entro");
                    String item = e.getItem().toString();
                    cmbTelefono.addItem(item);
                    cmbTelefono.setSelectedItem("");
                }
            }
        });
        cmbTelefono.setEditable(true);
        cmbTelefono.setPreferredSize(new Dimension(200,30));
        arrayPanels[6].add(cmbTelefono);
    }

    private void initAgregaUnaDescripcion(){
        txtAreaDescripcion = new JTextArea(TEXTOS[7]);
        //txtAreaCorreo.setHorizontalAlignment(SwingConstants.CENTER);
        txtAreaDescripcion.setFont(new Font("Calibri Light" ,Font.PLAIN, 20));
        txtAreaDescripcion.setForeground(Color.GRAY);
        txtAreaDescripcion.setPreferredSize(new Dimension(400,100));
        JScrollPane scrollMini = new JScrollPane();
        scrollMini.setViewportView(txtAreaDescripcion);
        arrayPanels[7].add(scrollMini);
    }

    private void initAgregaUnaMarcaAlEvento(){
        btnMarca = new JButton(TEXTOS[8]);
        btnMarca.setPreferredSize(new Dimension(200,30));
        btnMarca.addActionListener(new ButtonsListener());
        panelcoloreado = new JPanel();
        panelcoloreado.setPreferredSize(new Dimension(50,50));
        panelcoloreado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        arrayPanels[8].add(panelcoloreado);
        arrayPanels[8].add(btnMarca);
    }

    private class ButtonsListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnAceptar)) {
                valoresAceptados[0] = txtTitulo.getText();
               valoresAceptados[1] = txtLugar.getText();
               valoresAceptados[2] = txtCorreo.getText();
               valoresAceptados[3] = txtAreaDescripcion.getText();
               data.setValoresEvento(valoresAceptados);
               data.setFechaEvento(fecha);
                if(todoElDia){
                    for (int i = 0; i < horaInicio.length; i++) {
                        horaInicio[i] = -1;
                        horaFinal[i] = -1;
                    }
                }
                data.setHoraInicioEvento(horaInicio);
                data.setHoraFinalEvento(horaFinal);
                try{
                    for (int i = 0; i < cmbPersona.getItemCount(); i++) {
                        if(!cmbPersona.getItemAt(i).equals("")){
                            data.setPersonasEvento((String) cmbPersona.getItemAt(i));
                        }
                    }
                    for (int i = 0; i < cmbTelefono.getItemCount(); i++) {
                        if(!cmbTelefono.getItemAt(i).equals("")){
                            data.setTelefonoEvento((String) cmbTelefono.getItemAt(i));
                        }
                    }

                }catch (NullPointerException ex){
                    ex.getStackTrace();
                }
                data.setColorDelEvento(panelcoloreado.getBackground());
                MostrarFormulario.agregarFormulario(data);
                DlogFormulario.this.setVisible(false);
            }else if(e.getSource().equals(btnCancelar)){
                DlogFormulario.this.setVisible(false);
            }else if(e.getSource().equals(btnReset)){

            }else if(e.getSource().equals(btnFecha)){
                calendaeVentana.setVisible(true);
            }else if(e.getSource().equals(btnMarca)){
                colorVentana.setVisible(true);
            }
        }
    }

    private class FocusLostListener extends FocusAdapter{
        @Override
        public void focusLost(FocusEvent e) {
            if (e.getSource().equals(txtTitulo)) {
               // System.out.println("hola");
            }else if(e.getSource().equals(txtLugar)){

            }else if(e.getSource().equals(txtCorreo)){

            }else if(e.getSource().equals(cmbPersona)){

            }else if(e.getSource().equals(cmbTelefono)){

            }else if(e.getSource().equals(txtAreaDescripcion)){

            }
        }
    }





}


