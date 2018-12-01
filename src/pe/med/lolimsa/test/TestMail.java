package pe.med.lolimsa.test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import pe.med.lolimsa.mail.EmailJava;

/**
 * Clase Mail para probar cada metodo de la clase EmailJava
 *
 * @author Tarazona Marrujo Elí Gamaliel - elitgamaliel@gmail.com
 * @version 20/11/2018/A "Lolimsa - System JAADE S.A.C."
 * @see EmailJava
 */
public class TestMail {

  /**
   * Metodo que inicia el proceso de esta clase
   *
   * @param args - Argumentos del main
   */
  public static void main(String[] args) {
    EmailJava mail = new EmailJava();
    //Lista Archivos
    List<String> archivos = new ArrayList<>();
    archivos.add("C:\\Users\\etarazona\\Pictures\\socket.png");
    archivos.add("C:\\Users\\etarazona\\Pictures\\loli.jpg");
    //Lista correos
    List<String> correos = new ArrayList<>();
    //correos.add("elitgamaliel@outlook.com");
    //correos.add("etarazona@lolimsa.com.pe");
    correos.add("elitgamaliel@gmail.com");
    //correos.add("dangles@lolimsa.com.pe");
    //correos.add("aydrogo@lolimsa.com.pe");
    //correos.add("josealonso_2903@hotmail.com");
    //correos.add("despinoza@lolimsa.com.pe");
    //correos.add("cgomez@lolimsa.com.pe");
    //correos.add("japinto@lolimsa.com.pe");
    //correos.add("rliendo@lolimsa.com.pe");
    //correos.add("alopez@lolimsa.com.pe");
    //usuario del Sistema
    String UserSystem = "PC-INVEST07";
    //FECHA Y HORA
    String Datetime = "2018-11-30 16:40:00";
    String nombre = "KATHERIN";
    String asunto = "POS";
    String mensaje = "SOPORTE ";

    /**
     * Al ser esto una libreria para lolimsa los correos de esta libreria
     * saldran desde no.reply@lolimsa.com.pe para cambiar modificar lo
     * parametros staticos abajo
     *
     * @ EmailJava.SERVER = "lolimsa.com.pe";
     * @ EmailJava.PORT = 465;
     * @ EmailJava.USER = "no.reply@lolimsa.com.pe";
     * @ EmailJava.PASS = "Tg2dq567H%";
     */
    //mail.enviarEmailSimple(UserSystem, Datetime, nombre, asunto, mensaje, correos);
    //mail.enviarEmailMultiPart(UserSystem, Datetime, archivos, nombre", asunto, mensaje, correos);
    //mail.enviarEmailMultiPartHtml(UserSystem, Datetime, nombre, asunto, mensaje, correos);
    try {
      Message[] mensajes = mail.leerEmailMutiPartHtml();
      String nuevo = null;
      for (int i = 0; i < mensajes.length; i++) {
        Message m = mensajes[i];
        System.out.println("From:" + mensajes[i].getFrom().toString());
        System.out.println("Subject:" + mensajes[i].getSubject() + "\n");
      }
    } catch (MessagingException ex) {
      Logger.getLogger(TestMail.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

}
