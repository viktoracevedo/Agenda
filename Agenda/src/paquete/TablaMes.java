package paquete;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablaMes extends JPanel{
    public static final String[] SEMANA = {"Dom", "Lun", "Mar", "Mir", "Jue", "Vie", "Sab" };
    public static int[] DIAS_MESES; //= {31,(Agenda.anioSeleccionado%4) == 0 ? 29 : 28,31,30,31,30,31,31,30,31,30,31};
    public static int Dia;
    public static int Mes;
    public static int Anio;
    private int diasDespues;
    private DefaultTableModel modeloTabla;
    private String[][] renglones;
    private int row;
    private JTable tabla;
    private JTableHeader cabecera;
    private Dia[] arrayDias;
    private ModeloCelda modeloCelda;

    public TablaMes(int mes, int seguimiento, String direccion) {
        DIAS_MESES = new int[]{31, (Calendario.anioSeleccionado % 4) == 0 ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        arrayDias = new Dia[DIAS_MESES[mes]];
        for (int i = 0; i < arrayDias.length; i++) {
            arrayDias[i] = new Dia(i+1, mes, Calendario.anioSeleccionado);
        }
        row = ((int) (Math.ceil((DIAS_MESES[mes] + seguimiento) / 7.0))); //CHECAR ESTO
        renglones = new String[row][7];
        if(direccion.equals("futuro")){
            tablaFuturo(mes, seguimiento);
        }else if(direccion.equals("pasado")){
            tablaPasado(mes, seguimiento);
        }else{
            System.out.println("FATAL ERROR");
        }

        initModelos();

        this.setLayout(new BorderLayout());
        this.add(cabecera, BorderLayout.NORTH);
        this.add(tabla, BorderLayout.SOUTH);

        initListeners();
    }


    public TablaMes() {
        //CONTRUCTOR VACIO
    }

    private void initModelos() {
        modeloTabla = new DefaultTableModel();
        for (String i: SEMANA) {
            modeloTabla.addColumn(i);
        }
        for (String[] i : renglones) {
            modeloTabla.addRow(i);
        }
        tabla = new JTable(modeloTabla);

        modeloCelda = new ModeloCelda();
        for (int i = 0; i < 7; i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(modeloCelda);
        }
        //tabla.setDefaultRenderer(tabla.getColumnClass(0), modeloCelda);
        cabecera = tabla.getTableHeader();
    }

    private void tablaPasado(int mes, int seguimiento) {
        int numero = DIAS_MESES[mes];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 6; j >= 0 && numero >= 1; j--) {
                if (i != row - 1 || j < 7 - seguimiento) {
                    renglones[i][j] = numero + "";
                    numero--;
                }
            }
        }

        for (int i = 0; i < 7; i++) {
            if(renglones[0][i] != null){
                if(renglones[0][i].equals("1")){
                    diasDespues = 7 - i;
                    break;
                }
            }
        }
        if(diasDespues == 7){
            diasDespues = 0;
        }
    }

    private void tablaFuturo(int mes, int seguimiento) {
        int numero = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < 7 && numero <= DIAS_MESES[mes]; j++) {
                if (i != 0 || j >= seguimiento) {
                    renglones[i][j] = numero + "";
                    numero++;
                }
            }
        }

        switch ((row * 7) - (DIAS_MESES[mes] + seguimiento)){
            case 0: case 7:
                diasDespues = 0;
                break;
            case 1:
                diasDespues = 6;
                break;
            case 2:
                diasDespues = 5;
                break;
            case 3:
                diasDespues = 4;
                break;
            case 4:
                diasDespues = 3;
                break;
            case 5:
                diasDespues = 2;
                break;
            case 6:
                diasDespues = 1;
                break;
            default:
                System.out.println("ERROR en la clase TablaMes ");
        }
    }

    private void initListeners() {
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            int enX = tabla.rowAtPoint(e.getPoint());
            int enY = tabla.columnAtPoint(e.getPoint());
            if(tabla.getValueAt(enX, enY) != null){
                String aux = (String) tabla.getValueAt(enX, enY);
                int index = Integer.parseInt(aux);
                index--;
                Calendario.BuscarFecha(arrayDias[index].getAnioPerteneciente(), arrayDias[index].getMesPerteneciente(), arrayDias[index].getDiaPerteneciente(), Color.BLUE);
                TablaMes.Dia = arrayDias[index].getDiaPerteneciente();
                TablaMes.Mes = arrayDias[index].getMesPerteneciente();
                TablaMes.Anio = arrayDias[index].getAnioPerteneciente();
            }
            }
        });
    }



    public int getDiasDespues() {
        return diasDespues;
    }

    public JTable getTabla() {
        return tabla;
    }

    public ModeloCelda getModeloCelda() {
        return modeloCelda;
    }




    //        Calendar fecha = Calendar.getInstance();
//        int diaActual = fecha.get(Calendar.DAY_OF_MONTH);
//        int mes = fecha.get(Calendar.MONTH);
//        int year = fecha.get(Calendar.YEAR);
//        int diaSemana = fecha.get(Calendar.WEEK_OF_MONTH);
//        int diaSemanaMes = fecha.get(Calendar.DAY_OF_WEEK_IN_MONTH);
//        System.out.println("dia: " + diaActual + " mes: " + mes + " año: " + year + " diaSemana: " + diaSemana + " noseDiaSemana: " + diaSemanaMes);
//        System.out.println(fecha.getFirstDayOfWeek());

}





