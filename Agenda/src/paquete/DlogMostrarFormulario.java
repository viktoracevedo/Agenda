package paquete;

import javax.swing.*;
import java.awt.*;


public class DlogMostrarFormulario extends JDialog {
    private MostrarFormulario show;
    private boolean vacio;



    public DlogMostrarFormulario(Frame owner, boolean modal) {
        super(owner, modal);
        this.setLocationRelativeTo(null);
        this.setSize(600,600);
        this.setTitle("Eventos Previamente Agregados");
        int i = 0;
        vacio = false;
        JPanel panel = new JPanel();
        while (true){
            try {
                show = new MostrarFormulario(i);
                panel.add(show);
                i++;

            }catch (Exception ex){
                //System.out.println("entro");
                this.setVisible(false);
                vacio = true;
                break;
            }
            vacio = false;
            System.out.println("bucle");
        }
        JScrollPane scrollVentana = new JScrollPane();
        scrollVentana.setViewportView(panel);
        this.add(scrollVentana);
    }

    public boolean isVacio() {
        return vacio;
    }
}
