package pe.med.lolimsa.test;

import pe.med.lolimsa.sms.SmsJava;

/**
 * Clase SmsJava para testear el envio de mensajes
 *
 * @author Tarazona Marrujo Elí Gamaliel - elitgamaliel@gmail.com
 * @version 20/11/2018/A "Lolimsa - System JAADE S.A.C."
 * @see SmsJava
 */
public class TestSms {

  /**
   * Metodo que inicia el proceso de esta clase
   *
   * @param args - Argumentos del main
   */
  public static void main(String[] args) {
    SmsJava sms = new SmsJava();
    String numero = "0051965717387";
    String mensaje = "LOLIMSA, PRUEBAS MENSAJES PRARA QUE ME CREAS";
    //pasamos el numero de telefono y el mensaje a enviar
    sms.enviarMensaje(numero, mensaje);
  }
}
