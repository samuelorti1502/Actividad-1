package Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;
import javax.swing.JOptionPane;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Samuel David Ortiz
 */
public class ClsUsuario {

    private String usuario;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String contraseña1;
    private String contraseña2;
    private String fechaNac;
    
    private ImageIcon Img;
    private Icon icono;
    private JLabel label;

    public String claveMurci(String texto) {
        texto = texto.replace('m', '0');
        texto = texto.replace('M', '0');
        texto = texto.replace('u', '1');
        texto = texto.replace('U', '1');
        texto = texto.replace('r', '2');
        texto = texto.replace('R', '2');
        texto = texto.replace('c', '3');
        texto = texto.replace('C', '3');
        texto = texto.replace('i', '4');
        texto = texto.replace('I', '4');
        texto = texto.replace('e', '5');
        texto = texto.replace('E', '5');
        texto = texto.replace('l', '6');
        texto = texto.replace('L', '6');
        texto = texto.replace('a', '7');
        texto = texto.replace('A', '7');
        texto = texto.replace('g', '8');
        texto = texto.replace('G', '8');
        texto = texto.replace('o', '9');
        texto = texto.replace('O', '9');
        return texto;
    }
    
    public ClsUsuario(){
        
    }
    
    public ClsUsuario(JLabel label){
        this.label = label;
    }

    public void nuevoUsuario(String codigo, String nombre1, String nombre2, String apellido1, String apellido2, String fechaNac, String usuario,
            String pass1, String pass2, String url) throws WriterException {
        //String usuario, String email, String contraseña1,  String contraseña2){
        
        this.codigo = codigo;
        
        try {
            File file = new File("usuarios.txt");

            FileWriter archivo = new FileWriter(file.getAbsoluteFile(), true);

            String texto = codigo + "," + nombre1 + "," + nombre2 + "," + apellido1 + "," + apellido2 + "," + fechaNac + "," + 
                    claveMurci(usuario) + "," + claveMurci(pass1) + "," + claveMurci(pass2) + "," + url;

            PrintWriter imprimir = new PrintWriter(archivo);
            imprimir.println(texto);

            archivo.close();
        } catch (IOException ex) {
            //Logger.getLogger(UsuarioJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void paintimages() {
        //URL url = this.getClass().getResource("src/images/" + image + ".png");
        //Img = new ImageIcon(getClass().getResource(image + ".png"));
        Img = new ImageIcon("./src/Images/QR/" + this.codigo + ".png");
        System.out.println("./src/Images/QR/" + this.codigo + ".png");
        icono = new ImageIcon(Img.getImage().getScaledInstance(70, 75,
                Image.SCALE_DEFAULT));
        label.setIcon(icono);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContraseña1() {
        return contraseña1;
    }

    public void setContraseña1(String contraseña1) {
        this.contraseña1 = contraseña1;
    }

    public String getContraseña2() {
        return contraseña2;
    }

    public void setContraseña2(String contraseña2) {
        this.contraseña2 = contraseña2;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

}
