/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Samuel David Ortiz
 */
public class ClsGenerador extends Thread {

    int numero;
    ArrayList numeros = new ArrayList();
    ArrayList estrellas = new ArrayList();

    private int hora;
    private int minuto;
    private int segundo;

    private String horaIni;
    private String horaFin;

    private int par = 0;
    private int impar = 0;
    private int primo = 0;
    private int noPrimo = 0;
    private int cifra1 = 0;
    private int cifra2 = 0;
    private int cifra3 = 0;
    private int cifra4 = 0;
    private int cifra5 = 0;
    private int cifra6 = 0;
    private int cifra7 = 0;
    private int cifra8 = 0;
    private int cifra9 = 0;
    private int cifra10 = 0;
    private int suma10 = 0;
    private int suma25 = 0;
    private int suma70 = 0;
    private int suma21 = 0;

    private JTextArea txtConsola;
    private JTextArea txtEstadisticas;
    
    private int min = 0;
    private int max = 0;
    private int cant = 0;

    public void run() {
        generador1();
    }

    public void generador1() {
        int min = 1;
        int max = 10000000;
        
        int cant = 100000;

        //System.out.println("Hora Inicio: " + horaIni());
        this.txtConsola.append("Hora Inicio: " + horaIni() + System.lineSeparator());

        for (int i = getMin(); i <= getCant(); i++) {
            numero = (int) (Math.random() * (getMin() - getMax() + 1) + getMax());
            if (numeros.contains(numero)) {
                i--;
            } else {
                numeros.add(numero);

                String cifras = Integer.toString(numero);

                if (numero % 2 == 0) {
                    setPar(getPar() + 1);
                    this.txtConsola.append(i + " - El numero " + numero + " es par, tiene " + cifras.length() + " cifras"
                            + " y la suma de sus cifras es " + sumaCifras(numero)
                            + " en letras " + Convertir(cifras, false) + System.lineSeparator());

                    if (sumaCifras(numero) == 21) {
                        this.txtConsola.append("La suma de las cifras del numero " + numero + " es 21" + System.lineSeparator());
                    }

                    //this.txtConsola.append(Convertir(cifras, true) + System.lineSeparator());
                    //System.out.println("Suma de cifras " + sumaCifras(numero));
                    scrollDown();
                } else {

                    setImpar(getImpar() + 1);

                    this.txtConsola.append(i + " - El numero " + numero + " es impar, tiene " + cifras.length() + " cifras"
                            + " y la suma de sus cifras es " + sumaCifras(numero) + System.lineSeparator());
                    scrollDown();
                }

                if (EsPrimo(numero)) {
                    this.txtConsola.append(i + " - El numero " + numero + " es primo" + System.lineSeparator());
                    setPrimo(getPrimo() + 1);
                    scrollDown();
                }

                switch (cifras.length()) {
                    case 1:
                        this.setCifra1(getCifra1() + 1);
                        break;
                    case 2:
                        this.setCifra2(getCifra2() + 1);
                        break;
                    case 3:
                        this.setCifra3(getCifra3() + 1);
                        break;
                    case 4:
                        this.setCifra4(getCifra4() + 1);
                        break;
                    case 5:
                        this.setCifra5(getCifra5() + 1);
                        break;
                    case 6:
                        this.setCifra6(getCifra6() + 1);
                        break;
                    case 7:
                        this.setCifra7(getCifra7() + 1);
                        break;
                    case 8:
                        this.setCifra8(getCifra8() + 1);
                        break;
                    case 9:
                        this.setCifra9(getCifra9() + 1);
                        break;
                    case 10:
                        this.setCifra10(getCifra10() + 1);
                        break;
                }

                switch (sumaCifras(numero)) {
                    case 10:
                        this.setSuma10(getSuma10() + 1);
                        break;
                    case 25:
                        this.setSuma25(getSuma25() + 1);
                        break;
                    case 70:
                        this.setSuma70(getSuma70() + 1);
                        break;
                    case 21:
                        this.setSuma21(getSuma21() + 1);
                        break;
                }

                //this.txtConsola.append(i + " - El numero " + numero + " contiene " + cifras.length() + System.lineSeparator());
                /*try ( PrintWriter pw = new PrintWriter(Files.newBufferedWriter(Paths.get("./aleatorios.txt")))) {
                    for (Object n : numeros) {
                        //System.out.println(i + "-" + n + "");
                        pw.println(n + ",");

                    }
                    pw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
                };*/
            }
        }

        //JOptionPane.showMessageDialog(null, "Se finalizaron de generar lo números aleatorios");
        //System.out.println("Hora Fin: " + horaIni());
        this.txtConsola.append("Hora Fin: " + horaIni() + System.lineSeparator());
        this.txtConsola.append("Se generaron " + numeros.size() + " números" + System.lineSeparator());
        this.txtConsola.append("Se generaron " + numeros.size() + " números positivos" + System.lineSeparator());

        this.getTxtEstadisticas().append("Cantidad de pares: " + getPar() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de impares: " + getImpar() + System.lineSeparator());
        
        this.getTxtEstadisticas().append("Cantidad de numeros primos: " + getPrimo() + System.lineSeparator());
        
        int noPrimos = cant - getPrimo();
        
        this.getTxtEstadisticas().append("Cantidad de numeros no primos: " + noPrimos + System.lineSeparator());
        
        this.getTxtEstadisticas().append("Cantidad de numeros de 1 cifra: " + getCifra1() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de 2 cifras: " + getCifra2() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de 3 cifras: " + getCifra3() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de 4 cifras: " + getCifra4() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de 5 cifras: " + getCifra5() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de 6 cifras: " + getCifra6() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de 7 cifras: " + getCifra7() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de 8 cifras: " + getCifra8() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de 9 cifras: " + getCifra9() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de 10 cifras: " + getCifra10() + System.lineSeparator());
        
        this.getTxtEstadisticas().append("Cantidad de numeros de cuyas cifras suman 10: " + getSuma10() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de cuyas cifras suman 25: " + getSuma25() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de cuyas cifras suman 70: " + getSuma70() + System.lineSeparator());
        this.getTxtEstadisticas().append("Cantidad de numeros de cuyas cifras suman 21: " + getSuma21() + System.lineSeparator());
        scrollDown();

        //return getPar();
    }

    public void generador2() {
        int pos;

        int min = 1;
        int max = 10000000;

        int nCartas = 10000;
        Stack< Integer> pCartas = new Stack< Integer>();

        this.txtConsola.append("Hora Inicio: " + horaIni() + System.lineSeparator());

        for (int i = 1; i < nCartas; i++) {
            pos = (int) Math.floor(Math.random() * (min - max + 1) + max);
            while (pCartas.contains(pos)) {
                pos = (int) Math.floor(Math.random() * (min - max + 1) + max);
            }
            pCartas.push(pos);
        }
        System.out.println("Núm. aleatorios sin repetición:");
        //System.out.println(pCartas.toString());
        while (!pCartas.isEmpty()) {
            int t = pCartas.pop();
            System.out.println(t);
        }

        this.txtConsola.append("Hora Fin: " + horaIni() + System.lineSeparator());
        this.txtConsola.append("Se generaron " + nCartas + " números" + System.lineSeparator());
        this.txtConsola.append("Se generaron " + nCartas + " números positivos" + System.lineSeparator());
    }

    public void generador3() {
        int min_val = 1;
        int max_val = 10000000;
        int cant = 1000000;

        this.txtConsola.append("Hora Inicio: " + horaIni() + System.lineSeparator());
        Random rand = new Random();
        IntStream stream = rand.ints(cant, min_val, max_val);
        stream.forEach(System.out::println);
        this.txtConsola.append("Hora Fin: " + horaIni() + System.lineSeparator());
    }

    public void generador4() {
        int count = 100;
        int start = 1;
        int end = 10000000;

        Random rng = new Random();

        int[] result = new int[count];
        int cur = 0;
        int remaining = end - start;
        for (int i = start; i < end && count > 0; i++) {
            double probability = rng.nextDouble();
            if (probability < ((double) count) / (double) remaining) {
                count--;
                result[cur++] = i;
            }
            remaining--;
        }
        System.out.println("result = " + result);
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

    public void scrollDown() {
        txtConsola.setCaretPosition(txtConsola.getText().length());
    }

    public static boolean EsPrimo(int numero) {
        try {
            int divisores = 0;
            int contador = 2;

            for (int i = 2; i < numero; i++) {
                //ES UN DIVISOR DE LA VARIABLE numero
                if ((numero % i) == 0) {
                    divisores++;
                }
                if (divisores > 0) {
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }

    public static int sumaCifras(int n) {
        if (n <= 9) {
            return n;
        } else {
            return ((n % 10) + sumaCifras(n / 10));
        }

    }

    public boolean esVeintiuno(int numero) {
        if (sumaCifras(numero) == 21) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        //(new Thread(new Generador())).start();
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

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public JTextArea getTxtConsola() {
        return txtConsola;
    }

    public void setTxtConsola(JTextArea txtConsola) {
        this.txtConsola = txtConsola;
    }

    // <editor-fold defaultstate="collapsed" desc="Numeros a letras">                          
    private final String[] UNIDADES = {"", "un ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ", "ocho ", "nueve "};
    private final String[] DECENAS = {"diez ", "once ", "doce ", "trece ", "catorce ", "quince ", "dieciseis ",
        "diecisiete ", "dieciocho ", "diecinueve", "veinte ", "treinta ", "cuarenta ",
        "cincuenta ", "sesenta ", "setenta ", "ochenta ", "noventa "};
    private final String[] CENTENAS = {"", "ciento ", "doscientos ", "trecientos ", "cuatrocientos ", "quinientos ", "seiscientos ",
        "setecientos ", "ochocientos ", "novecientos "};

    public String Convertir(String numero, boolean mayusculas) {
        String literal = "";
        String parte_decimal;
        //si el numero utiliza (.) en lugar de (,) -> se reemplaza
        numero = numero.replace(".", ",");
        //si el numero no tiene parte decimal, se le agrega ,00
        if (numero.indexOf(",") == -1) {
            numero = numero + ",00";
        }
        //se valida formato de entrada -> 0,00 y 999 999 999,00
        if (Pattern.matches("\\d{1,9},\\d{1,2}", numero)) {
            //se divide el numero 0000000,00 -> entero y decimal
            String Num[] = numero.split(",");
            //de da formato al numero decimal
            //parte_decimal = "y " + Num[1] + "/100 Soles.";
            //se convierte el numero a literal
            if (Integer.parseInt(Num[0]) == 0) {//si el valor es cero
                literal = "cero ";
            } else if (Integer.parseInt(Num[0]) > 999999) {//si es millon
                literal = getMillones(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 999) {//si es miles
                literal = getMiles(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 99) {//si es centena
                literal = getCentenas(Num[0]);
            } else if (Integer.parseInt(Num[0]) > 9) {//si es decena
                literal = getDecenas(Num[0]);
            } else {//sino unidades -> 9
                literal = getUnidades(Num[0]);
            }
            //devuelve el resultado en mayusculas o minusculas
            if (mayusculas) {
                return (literal /*+ parte_decimal*/).toUpperCase();
            } else {
                return (literal /*+ parte_decimal*/);
            }
        } else {//error, no se puede convertir
            return literal = null;
        }
    }

    /* funciones para convertir los numeros a literales */
    private String getUnidades(String numero) {// 1 - 9
        //si tuviera algun 0 antes se lo quita -> 09 = 9 o 009=9
        String num = numero.substring(numero.length() - 1);
        return UNIDADES[Integer.parseInt(num)];
    }

    private String getDecenas(String num) {// 99                        
        int n = Integer.parseInt(num);
        if (n < 10) {//para casos como -> 01 - 09
            return getUnidades(num);
        } else if (n > 19) {//para 20...99
            String u = getUnidades(num);
            if (u.equals("")) { //para 20,30,40,50,60,70,80,90
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8];
            } else {
                return DECENAS[Integer.parseInt(num.substring(0, 1)) + 8] + "y " + u;
            }
        } else {//numeros entre 11 y 19
            return DECENAS[n - 10];
        }
    }

    private String getCentenas(String num) {// 999 o 099
        if (Integer.parseInt(num) > 99) {//es centena
            if (Integer.parseInt(num) == 100) {//caso especial
                return " cien ";
            } else {
                return CENTENAS[Integer.parseInt(num.substring(0, 1))] + getDecenas(num.substring(1));
            }
        } else {//por Ej. 099 
            //se quita el 0 antes de convertir a decenas
            return getDecenas(Integer.parseInt(num) + "");
        }
    }

    private String getMiles(String numero) {// 999 999
        //obtiene las centenas
        String c = numero.substring(numero.length() - 3);
        //obtiene los miles
        String m = numero.substring(0, numero.length() - 3);
        String n = "";
        //se comprueba que miles tenga valor entero
        if (Integer.parseInt(m) > 0) {
            n = getCentenas(m);
            return n + "mil " + getCentenas(c);
        } else {
            return "" + getCentenas(c);
        }

    }

    private String getMillones(String numero) { //000 000 000        
        //se obtiene los miles
        String miles = numero.substring(numero.length() - 6);
        //se obtiene los millones
        String millon = numero.substring(0, numero.length() - 6);
        String n = "";
        if (millon.length() > 1) {
            n = getCentenas(millon) + "millones ";
        } else {
            n = getUnidades(millon) + "millon ";
        }
        return n + getMiles(miles);
    }// </editor-fold> 

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public int getImpar() {
        return impar;
    }

    public void setImpar(int impar) {
        this.impar = impar;
    }

    public int getPrimo() {
        return primo;
    }

    public void setPrimo(int primo) {
        this.primo = primo;
    }

    public int getNoPrimo() {
        return noPrimo;
    }

    public void setNoPrimo(int noPrimo) {
        this.noPrimo = noPrimo;
    }

    public JTextArea getTxtEstadisticas() {
        return txtEstadisticas;
    }

    public void setTxtEstadisticas(JTextArea txtEstadisticas) {
        this.txtEstadisticas = txtEstadisticas;
    }

    public int getCifra1() {
        return cifra1;
    }

    public void setCifra1(int cifra1) {
        this.cifra1 = cifra1;
    }

    public int getCifra2() {
        return cifra2;
    }

    public void setCifra2(int cifra2) {
        this.cifra2 = cifra2;
    }

    public int getCifra3() {
        return cifra3;
    }

    public void setCifra3(int cifra3) {
        this.cifra3 = cifra3;
    }

    public int getCifra4() {
        return cifra4;
    }

    public void setCifra4(int cifra4) {
        this.cifra4 = cifra4;
    }

    public int getCifra5() {
        return cifra5;
    }

    public void setCifra5(int cifra5) {
        this.cifra5 = cifra5;
    }

    public int getCifra6() {
        return cifra6;
    }

    public void setCifra6(int cifra6) {
        this.cifra6 = cifra6;
    }

    public int getCifra7() {
        return cifra7;
    }

    public void setCifra7(int cifra7) {
        this.cifra7 = cifra7;
    }

    public int getCifra8() {
        return cifra8;
    }

    public void setCifra8(int cifra8) {
        this.cifra8 = cifra8;
    }

    public int getCifra9() {
        return cifra9;
    }

    public void setCifra9(int cifra9) {
        this.cifra9 = cifra9;
    }

    public int getCifra10() {
        return cifra10;
    }

    public void setCifra10(int cifra10) {
        this.cifra10 = cifra10;
    }

    public int getSuma10() {
        return suma10;
    }

    public void setSuma10(int suma10) {
        this.suma10 = suma10;
    }

    public int getSuma25() {
        return suma25;
    }

    public void setSuma25(int suma25) {
        this.suma25 = suma25;
    }

    public int getSuma70() {
        return suma70;
    }

    public void setSuma70(int suma70) {
        this.suma70 = suma70;
    }

    public int getSuma21() {
        return suma21;
    }

    public void setSuma21(int suma21) {
        this.suma21 = suma21;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

}
