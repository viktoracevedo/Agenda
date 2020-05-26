package paquete;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ModeloCelda extends DefaultTableCellRenderer {
    String diaAComparar;
    Color color;

    public ModeloCelda(String diaAComparar, Color color) {
        this.diaAComparar = diaAComparar;
        this.color = color;
    }

    public ModeloCelda() {
        this.diaAComparar = "x";
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        if(diaAComparar.equals((String) table.getValueAt(row, column))){
           this.setBackground(color);
        }else{
           this.setBackground(Color.white);

        }

        return this;
    }

}
