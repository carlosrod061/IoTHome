package edu.utng.mx.iothome.model;

public class Alarma {

    private String nombreAlarma;
    private String valorSensor;
    private String encendidoAutomatico;
    private String probarAlarma;


    public Alarma(String nombreAlarma) {
        this.nombreAlarma = nombreAlarma;
    }

    public String getNombreAlarma() {
        return nombreAlarma;
    }

    public void setNombreAlarma(String nombreAlarma) {
        this.nombreAlarma = nombreAlarma;
    }

    public String getValorSensor() {
        return valorSensor;
    }

    public void setValorSensor(String valorSensor) {
        this.valorSensor = valorSensor;
    }

    public String getEncendidoAutomatico() {
        return encendidoAutomatico;
    }

    public void setEncendidoAutomatico(String encendidoAutomatico) {
        this.encendidoAutomatico = encendidoAutomatico;
    }

    public String getProbarAlarma() {
        return probarAlarma;
    }

    public void setProbarAlarma(String probarAlarma) {
        this.probarAlarma = probarAlarma;
    }

    @Override
    public String toString() { return nombreAlarma;}
}
