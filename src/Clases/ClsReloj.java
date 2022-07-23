/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static java.lang.Thread.sleep;
import java.util.Calendar;
import javax.swing.JLabel;

/**
 *
 * @author Samuel David Ortiz
 */
public class ClsReloj extends Thread {

    private int hora;
    private int minuto;
    private int segundo;
    
    private int anio;
    private int mes;
    private int dia;
    
    boolean estado;
    JLabel lblReloj;

    public ClsReloj() {

    }

    public String hora() {
        Calendar calendario = Calendar.getInstance();
        setHora(calendario.get(Calendar.HOUR_OF_DAY));
        setMinuto(calendario.get(Calendar.MINUTE));
        setSegundo(calendario.get(Calendar.SECOND));
        
        setAnio(calendario.get(Calendar.YEAR));
        setMes(calendario.get(Calendar.MONTH));
        setDia(calendario.get(Calendar.DAY_OF_MONTH));
        
        String fecha;
        fecha = (getDia() <= 9 ? "0" + getDia() : getDia()) + "/" + (getMes() <= 9 ? "0" + getMes() : getMes()) + "/" + getAnio();
        
        //System.out.println("fecha = " + fecha);

        String hr;
        hr = null;

        hr = (getHora() <= 9 ? "0" + getHora() : getHora()) + ":" + (getMinuto() <= 9 ? "0" + getMinuto() : getMinuto()) + ":" + (getSegundo() <= 9
                ? "0" + getSegundo() : getSegundo());
        return fecha + " " + hr;
    }

    public void run() {
        estado = true;
        for (;;) {
            if (estado == true) {
                try {
                    sleep(1);
                    this.lblReloj.setText(hora());
                    //lblReloj.setFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("alarm clock.ttf").openStream()));
                } catch (Exception e) {

                }
            } else {
                break;
            }
        }
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    public JLabel getLblReloj() {
        return lblReloj;
    }

    public void setLblReloj(JLabel lblReloj) {
        this.lblReloj = lblReloj;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

}
