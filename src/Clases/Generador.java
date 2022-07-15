/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel David Ortiz
 */
public class Generador extends Thread {

    int numero;
    ArrayList numeros = new ArrayList();
    ArrayList estrellas = new ArrayList();

    private int hora;
    private int minuto;
    private int segundo;
    //private String horaIni;

    public void run() {
        int min = 1;
        int max = 10000000;

        System.out.println("Hora Inicio: " + horaIni());

        for (int i = 1; i <= 1000000; i++) {
            numero = (int) (Math.random() * (min - max + 1) + max);
            if (numeros.contains(numero)) {
                i--;
            } else {
                numeros.add(numero);
                try ( PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get("./aleatorios.txt")))) {
                    for (Object n : numeros) {
                        //System.out.println(n + "");
                        pw.println(n + ",");
                    }
                    pw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
                };
            }
        }

        JOptionPane.showMessageDialog(null, "Se finalizaron de generar lo números aleatorios");

    }

    public String horaIni() {
        Calendar calendario = Calendar.getInstance();
        setHora(calendario.get(Calendar.HOUR_OF_DAY));
        setMinuto(calendario.get(Calendar.MINUTE));
        setSegundo(calendario.get(Calendar.SECOND));

        String hr;
        hr = null;

        hr = (getHora() <= 9 ? "0" + getHora() : getHora()) + ":" + (getMinuto() <= 9 ? "0" + getMinuto() : getMinuto()) + ":" + (getSegundo() <= 9 ? "0" + getSegundo() : getSegundo());
        return hr;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        (new Thread(new Generador())).start();
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

    /*public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }*/
}
