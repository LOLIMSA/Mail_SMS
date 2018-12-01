package pe.med.lolimsa.sms;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase SmsJava para envio de mensajes de Texto
 *
 * @author Tarazona Marrujo Elí Gamaliel - elitgamaliel@gmail.com
 * @version 21/11/2018/A "Lolimsa - System JAADE S.A.C."
 */
public class SmsJava {

  /**
   * enviarMensaje envia un SMS al un numero telefonico
   *
   * @param numero - numero al cual se enviara el mensaje
   * @param mensaje - el mensaje que se enviara el mensaje
   */
  public void enviarMensaje(String numero, String mensaje) {
    try {
      HttpResponse response = Unirest.post("https://api.infobip.com/sms/1/text/single")
              .header("authorization", "Basic TG9saW1zYTpMMGwxdzVhL3Az")
              .header("content-type", "application/json")
              .header("accept", "application/json")
              .body("{\"from\":\"LOLIMSA\",\"to\":\"" + numero + "\",\"text\":\"" + mensaje + "\"}")
              .asString();
      System.out.println("response: " + response);
    } catch (UnirestException ex) {
      Logger.getLogger(SmsJava.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
