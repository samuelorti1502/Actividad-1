package Clases;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class ClsCamara extends Thread {

    private boolean result;
    private boolean nEntro;
    private String error;

    private JLabel jLabel;
    private int cameraIndex;
    private JButton jButton;

    private int i;
    VideoCapture video;
    private Mat matImage640x480;

    public ClsCamara(JLabel _jLabel) {
        result = false;
        error = "";
        jLabel = _jLabel;
        cameraIndex = 0;
        i = 0;
    }

    public ClsCamara() {
    }

    public void SetJLabel(JLabel _jLabel) {
        jLabel = _jLabel;
    }

    public void SetCameraIndex(int _cameraIndex) {
        cameraIndex = _cameraIndex;
    }

    @Override
    public void run() {
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            video = new VideoCapture(0, Videoio.CAP_DSHOW);

            Mat f = new Mat();
            matImage640x480 = new Mat();
            while (true) {
                video.read(f);
                showResults2(f);
            }
            //resultado = true;
        } catch (Exception e) {
            error = "AbrirCamara(). " + e.getMessage();
        }

        //return resultado;
    }

    public void showResult(Mat img) {
        Imgproc.resize(img.clone(), this.matImage640x480, new Size(640, 480));
        Imgproc.resize(img, img, new Size(jLabel.getWidth(), jLabel.getHeight()));

        MatOfByte m = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, m);
        byte[] byteArray = m.toArray();
        BufferedImage bufImage = null;

        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
            jLabel.setIcon(new ImageIcon(bufImage));

            /*System.out.println("i: " + i++);
            Image imagenPrueba = new ImageIcon(bufImage).getImage();
            Graphics2D g2 = bufImage.createGraphics();
            g2.drawImage(imagenPrueba, 0, 0, null);
            g2.dispose();
            ImageIO.write(bufImage, "jpg", new File(i + ".jpg"));*/
            //JFrame frame = new JFrame();
            //frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
            //frame.pack();
            //frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showResults2(Mat img) {
        CascadeClassifier faceDetector = new CascadeClassifier("extlib/haarcascade_frontalface_alt.xml");
        MatOfRect faceDetections = new MatOfRect();

        if (video.isOpened()) {
            while (true) {
                try {
                    //Thread.sleep(100);
                    video.read(img);
                    Imgproc.resize(img.clone(), this.matImage640x480, new Size(640, 480));
                    Imgproc.resize(img, img, new Size(jLabel.getWidth(), jLabel.getHeight()));
                    if (!img.empty()) {
                        faceDetector.detectMultiScale(img, faceDetections);

                        setnEntro(false);
                        //jButton.setEnabled(false);

                        for (Rect rect : faceDetections.toArray()) {
                            setnEntro(true);
                            //jButton.setEnabled(true);
                            Imgproc.rectangle(img,
                                    new Point(rect.x - 10, rect.y - 50),
                                    new Point(rect.x + rect.width * 1.2, rect.y + rect.height * 1.4),
                                    new Scalar(0, 255, 0));
                            if (isnEntro()) {
                                //Mat region = new Mat(img, rect);
                                //Imgcodecs.imwrite("Imagen.jpg", region);
                                setnEntro(false);
                            }

                        }

                        jLabel.setIcon(new ImageIcon(convertir(img)));
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ClsCamara.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private Image convertir(Mat imagen) {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", imagen, matOfByte);

        byte[] byteArray = matOfByte.toArray();
        BufferedImage bufImage = null;

        try {

            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Image) bufImage;
    }

    public ImageIcon GetImage640x480() {
        ImageIcon imageIcon = null;

        MatOfByte m = new MatOfByte();
        Imgcodecs.imencode(".jpg", this.matImage640x480, m);
        byte[] byteArray = m.toArray();
        BufferedImage bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
            imageIcon = new ImageIcon(bufImage);

            Image imagenPrueba = new ImageIcon(bufImage).getImage();
            Graphics2D g2 = bufImage.createGraphics();
            g2.drawImage(imagenPrueba, 0, 0, null);
            g2.dispose();
            ImageIO.write(bufImage, "jpg", new File("Imagen640x480.jpg"));
        } catch (Exception e) {
            error = "GetImage640x480(). " + e.getMessage();
        }
        return imageIcon;
    }

    public void DetenerVideo() {
        video.release();
    }

    public boolean isnEntro() {
        return nEntro;
    }

    public void setnEntro(boolean nEntro) {
        this.nEntro = nEntro;
    }

    public JButton getjButton() {
        return jButton;
    }

    public void setjButton(JButton jButton) {
        this.jButton = jButton;
    }
}
