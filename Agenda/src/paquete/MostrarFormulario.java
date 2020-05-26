package paquete;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostrarFormulario extends JPanel {
    public static ArrayList<Formulario> listFormularios;
    public static final String[] PIES = {"TITULO DEL EVENTO","FECHA DEL EVENTO","HORARIO DEL EVENTO","LUGAR DEL EVENTO", "CORREO",
            "PERSONAS", "TELEFONOS","DESCRIPCCION", "COLOR DE LA ETIQUETA"};

    public MostrarFormulario(int index) {
        listFormularios = new ArrayList<>();
        JPanel[] panels = new JPanel[9];
        JLabel[] lblmostrar = new JLabel[4];
        for (int i = 0; i < lblmostrar.length; i++) {
            lblmostrar[i] = new JLabel();
        }
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel();
            panels[i].setBorder(BorderFactory.createTitledBorder(PIES[i]));
        }

        lblmostrar[0].setText(listFormularios.get(index).getValoresEvento()[0]);
        lblmostrar[1].setText(listFormularios.get(index).getStringFecha());
        lblmostrar[2].setText(listFormularios.get(index).getStringHorario());
        lblmostrar[3].setText(listFormularios.get(index).getValoresEvento()[1]);
        lblmostrar[4].setText(listFormularios.get(index).getValoresEvento()[2]);
        for (int i = 0; i < lblmostrar.length; i++) {
            panels[i].add(lblmostrar[i]);
        }
        for (String i:listFormularios.get(index).getPersonasEvento()) {
            panels[5].add(new JLabel(i));
        }
        JScrollPane scrollPersona = new JScrollPane();
        scrollPersona.setViewportView(panels[5]);
        for (String i:listFormularios.get(index).getTelefonoEvento()) {
            panels[6].add(new JLabel(i));
        }
        JScrollPane scrollTelefono = new JScrollPane();
        scrollTelefono.setViewportView(panels[6]);

        JTextArea txtDescripcion = new JTextArea(listFormularios.get(index).getValoresEvento()[3]);
        panels[7].add(txtDescripcion);

        JPanel panelColor = new JPanel();
        panelColor.setBackground(listFormularios.get(index).getColorDelEvento());
        panels[8].add(panelColor);

        for (JPanel i: panels) {
            this.add(i);
        }

    }

    public static void agregarFormulario(Formulario formulario){
        listFormularios.add(formulario);
    }

//    public static int getTamanioListFormulario(){
//
//    }
}


