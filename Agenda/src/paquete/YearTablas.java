package paquete;

public class YearTablas {
    private TablaMes[] meses;
    private int ultimoMes;


    public YearTablas(int seguimiento, String direccion) {
        //System.out.println(Calendario.anioSeleccionado +  " " +direccion);
        meses = new TablaMes[12];
        if(direccion.equals("futuro")){
            for (int i = 0; i < meses.length; i++) {
                if (i == 0) {
                    meses[i] = new TablaMes(i, seguimiento, direccion);
                } else {
                    meses[i] = new TablaMes(i, meses[i - 1].getDiasDespues(), direccion);
                }
            }
            ultimoMes = meses[11].getDiasDespues();
        }else{
            for (int i = 11; i >= 0; i--) {
                if(i == 11){
                    meses[i] = new TablaMes(i, seguimiento, direccion);
                }else{
                    meses[i] = new TablaMes(i, meses[i + 1].getDiasDespues(), direccion);
                }
            }
            ultimoMes = meses[0].getDiasDespues();
        }
    }

    public int getUltimoMes() {
        return ultimoMes;
    }

    public TablaMes getMesAElegir(int mesElegido) {
        return meses[mesElegido];
    }
}
