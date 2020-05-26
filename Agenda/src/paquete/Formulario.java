package paquete;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Formulario {
    private String[] valoresEvento;
    private int[] fechaEvento;
    private int[] horaInicioEvento;
    private int[] horaFinalEvento;
    private ArrayList<String> personasEvento;
    private ArrayList<String> telefonoEvento;
    private Color colorDelEvento;

    public Formulario() {
        personasEvento = new ArrayList<>();
        telefonoEvento = new ArrayList<>();
    }

    public String[] getValoresEvento() {
        return valoresEvento;
    }

    public int[] getFechaEvento() {
        return fechaEvento;
    }

    public int[] getHoraInicioEvento() {
        return horaInicioEvento;
    }

    public int[] getHoraFinalEvento() {
        return horaFinalEvento;
    }

    public ArrayList<String> getPersonasEvento() {
        return personasEvento;
    }

    public ArrayList<String> getTelefonoEvento() {
        return telefonoEvento;
    }

    public Color getColorDelEvento() {
        return colorDelEvento;
    }

    public String getStringFecha(){
        return fechaEvento[0] + " de " + fechaEvento[1] + " del " + fechaEvento[2];
    }

    public String getStringHorario(){
        return horaInicioEvento[0] +  ":" + horaInicioEvento[1] + "am. - " + horaFinalEvento[0] + ":" + horaFinalEvento[1] + "pm.";
    }

    public void setHoraInicioEvento(int[] horaInicioEvento) {
        this.horaInicioEvento = horaInicioEvento;
    }

    public void setHoraFinalEvento(int[] horaFinalEvento) {
        this.horaFinalEvento = horaFinalEvento;
    }

    public void setValoresEvento(String[] valoresEvento) {
        this.valoresEvento = valoresEvento;
    }

    public void setFechaEvento(int[] fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public void setPersonasEvento(String personasEvento) {
        this.personasEvento.add(personasEvento);
    }

    public void setTelefonoEvento(String telefonoEvento) {
        this.telefonoEvento.add(telefonoEvento);
    }

    public void setColorDelEvento(Color colorDelEvento) {
        this.colorDelEvento = colorDelEvento;
    }

    @Override
    public String toString() {
        return "Formulario{" +
                "valoresEvento=" + Arrays.toString(valoresEvento) +
                ", fechaEvento=" + Arrays.toString(fechaEvento) +
                ", horaInicioEvento=" + Arrays.toString(horaInicioEvento) +
                ", horaFinalEvento=" + Arrays.toString(horaFinalEvento) +
                ", indexPersonas=" + personasEvento +
                ", indexTelefonos=" + telefonoEvento +
                ", colorDelEvento=" + colorDelEvento +
                '}';
    }
}
