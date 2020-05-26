package paquete;

public class Dia {
    private int diaPerteneciente;
    private int mesPerteneciente;
    private int anioPerteneciente;

    public Dia(int diaPerteneciente, int mesPerteneciente, int anioPerteneciente) {
        this.diaPerteneciente = diaPerteneciente;
        this.mesPerteneciente = mesPerteneciente;
        this.anioPerteneciente = anioPerteneciente;


    }

    public int getDiaPerteneciente() {
        return diaPerteneciente;
    }

    public void setDiaPerteneciente(int diaPerteneciente) {
        this.diaPerteneciente = diaPerteneciente;
    }

    public int getMesPerteneciente() {
        return mesPerteneciente;
    }

    public void setMesPerteneciente(int mesPerteneciente) {
        this.mesPerteneciente = mesPerteneciente;
    }

    public int getAnioPerteneciente() {
        return anioPerteneciente;
    }

    public void setAnioPerteneciente(int anioPerteneciente) {
        this.anioPerteneciente = anioPerteneciente;
    }

    @Override
    public String toString() {
        return  diaPerteneciente +
                " de " + mesPerteneciente +
                " del " + anioPerteneciente;
    }
}
