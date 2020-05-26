package paquete;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

public class Calendario{
    public static final int INICIALIZAR_CALENDARIO = 3;
    public static final String[] MESES_DEL_ANIO= {"Enero","Febrero", "Marzo", "Abril",
                                                "Mayo", "Junio", "Julio", "Agosto",
                                                "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    public static final Calendar FECHA_ACTUAL = Calendar.getInstance();
    public static int anioSeleccionado;
    private static ArrayList<YearTablas> listYearsFuturo;
    private static ArrayList<YearTablas> listYearsPasado;
    private int valorTabla, indexFuturo, indexPasado;

    private String nameMesSeleccionado;
    private TablaMes tablaMesSeleccionado;
    private JButton bntIzquierda;
    private JButton bntDerecha;
    public Calendario() {
        Calendario.anioSeleccionado = FECHA_ACTUAL.get(Calendar.YEAR);
        bntIzquierda = new JButton();
        bntDerecha = new JButton();
        listYearsFuturo = new ArrayList<>();
        listYearsFuturo.add(new YearTablas(INICIALIZAR_CALENDARIO, "futuro"));
        indexFuturo = 0;
        Calendario.anioSeleccionado--;
        listYearsPasado = new ArrayList<>();
        listYearsPasado.add(new YearTablas(INICIALIZAR_CALENDARIO + 1, "pasado"));
        Calendario.anioSeleccionado++;
        indexPasado = -1;

        valorTabla = FECHA_ACTUAL.get(Calendar.MONTH);
        nameMesSeleccionado = MESES_DEL_ANIO[FECHA_ACTUAL.get(Calendar.MONTH)] + " " + Calendario.anioSeleccionado;
        IrAlFuturo();
        IrAlPasado();
        BuscarFecha(FECHA_ACTUAL.get(Calendar.YEAR), FECHA_ACTUAL.get(Calendar.MONTH), FECHA_ACTUAL.get(Calendar.DAY_OF_MONTH), Color.PINK );
        initListeners();
    }


    private void initListeners() {
        bntIzquierda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverAlIzquierda();
            }
        });
        bntDerecha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverAlDerecha();
            }
        });
    }

    public static void  BuscarFecha(int anio, int mes, int dia, Color color) {
        TablaMes tablaMes;
        String fecha = "error";
        if(anio >= 2020){
            anio = anio - 2020;
            tablaMes = listYearsFuturo.get(anio).getMesAElegir(mes);
        }else{
            anio = 2020 - anio;
            tablaMes = listYearsPasado.get(anio).getMesAElegir(mes);
        }
            JTable tabla = tablaMes.getTabla();
            ModeloCelda celda = new ModeloCelda(dia + "", color);
            for (int i = 0; i < 7; i++) {
                //System.out.println("entre");
                tabla.getColumnModel().getColumn(i).setCellRenderer(celda);
            }
    }

    private void IrAlFuturo() {
        if(Calendario.anioSeleccionado >= 2020 && valorTabla == 12){
            Calendario.anioSeleccionado++;
            valorTabla = 0;
            try{
                tablaMesSeleccionado = listYearsFuturo.get(indexFuturo+1).getMesAElegir(valorTabla);
            }catch (IndexOutOfBoundsException ex){
                listYearsFuturo.add(new YearTablas(listYearsFuturo.get(indexFuturo).getUltimoMes(), "futuro"));
                tablaMesSeleccionado = listYearsFuturo.get(indexFuturo+1).getMesAElegir(valorTabla);
            }finally {
                indexFuturo++;
            }
        }else if(Calendario.anioSeleccionado >= 2021 && valorTabla == -1){
            Calendario.anioSeleccionado--;
            indexFuturo--;
            valorTabla = 11;
            tablaMesSeleccionado = listYearsFuturo.get(indexFuturo).getMesAElegir(valorTabla);
        }else if(Calendario.anioSeleccionado >= 2020 && valorTabla != -1){
            tablaMesSeleccionado = listYearsFuturo.get(indexFuturo).getMesAElegir(valorTabla);
        }
    }
    private void IrAlPasado() {
        if(Calendario.anioSeleccionado <= 2020 && valorTabla == -1){
            Calendario.anioSeleccionado--;
            valorTabla = 11;
            indexPasado++;
            indexFuturo = 0;
            try{
                tablaMesSeleccionado = listYearsPasado.get(indexPasado).getMesAElegir(valorTabla);
            }catch (IndexOutOfBoundsException ex) {
                System.out.println("hola");
                listYearsPasado.add(new YearTablas(listYearsPasado.get(indexPasado-1).getUltimoMes(), "pasado"));
                tablaMesSeleccionado = listYearsPasado.get(indexPasado).getMesAElegir(valorTabla);
            }
        }else if(Calendario.anioSeleccionado <= 2019 && valorTabla == 12) {
            valorTabla = 0;
            if(Calendario.anioSeleccionado == 2019){
                indexPasado = -1;
                tablaMesSeleccionado = listYearsFuturo.get(indexFuturo).getMesAElegir(valorTabla);
            }else{
                indexPasado--;
                tablaMesSeleccionado = listYearsPasado.get(indexPasado).getMesAElegir(valorTabla);
            }
            Calendario.anioSeleccionado++;
        }else if(Calendario.anioSeleccionado <= 2019){
            tablaMesSeleccionado = listYearsPasado.get(indexPasado).getMesAElegir(valorTabla);
        }
    }

    public String getNameMesSeleccionado() {
        return nameMesSeleccionado;
    }

    public TablaMes getTablaMesSeleccionado() {
        return tablaMesSeleccionado;
    }

    public void moverAlIzquierda(){
        valorTabla--;
        IrAlFuturo();
        IrAlPasado();
        nameMesSeleccionado = MESES_DEL_ANIO[valorTabla] + " " + Calendario.anioSeleccionado;
    }

    public void moverAlDerecha(){
        valorTabla++;
        IrAlFuturo();
        IrAlPasado();
        nameMesSeleccionado = MESES_DEL_ANIO[valorTabla] + " " + Calendario.anioSeleccionado;
    }



}

