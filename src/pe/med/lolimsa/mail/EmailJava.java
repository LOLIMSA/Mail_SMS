package pe.med.lolimsa.mail;

import java.io.File;
import java.util.List;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * Clase EmailJava para envio de correo Electronicos
 *
 * @author: Tarazona Marrujo Elí Gamaliel - elitgamaliel@gmail.com
 * @version: 20/11/2018/A "Lolimsa - System JAADE S.A.C."
 */
public class EmailJava {

  public static String SERVER = "lolimsa.com.pe";
  public static int PORT = 465;
  public static String USER = "no.reply@lolimsa.com.pe";
  public static String PASS = "Tg2dq567H%";

  /**
   * enviarEmailSimple Envia un mensaje de correo electronico simple
   *
   * @param UserSystem - Nombre de usuario del sistema
   * @param Datetime - Fecha y Hora que se envia el mensaje
   * @param nombre - Nombre del emisor del mensaje
   * @param asunto - Asunto del Mensaje
   * @param mensaje - Cuerpo o contenido del mensaje
   * @param correos - Listado de correo a los cuales sera enviado el mensaje
   * @return True si el mensaje es enviado, False si el mensaje no es enviado
   */
  public boolean enviarEmailSimple(String UserSystem, String Datetime, String nombre, String asunto, String mensaje, List<String> correos) {
    try {
      //opcional aqui comprobar el contenido del mensaje
      if (mensaje.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No has introducido tu mensaje. Por favor ingrese el mensaje y vuelva a intentarlo.");
        return false;
      }
      //Formamos el cuerpo del mensaje
      String textMessage = "Solicitud de solicitud de soporte\n"
              + "---------------------------------------------------------------------------------------------------------\n"
              + "Nueva solicitud de soporte de _P1_\n"
              + "Expedido por _P2_ en _P3_\n"
              + "---------------------------------------------------------------------------------------------------------\n"
              + "\n"
              + "Mensaje: \n_P4_\n"
              + "\n"
              + "---------------------------------------------------------------------------------------------------------\n"
              + "Este correo electrónico fue enviado desde la aplicación. \n"
              + "Tenga en cuenta que este es un correo electrónico generado \n"
              + "automáticamente y sus respuestas no irán a ninguna parte."
              + "\n"
              + "© Todos los derechos reservados - Desarrollo gerencial Lolimsa.";

      textMessage = textMessage.replace("_P1_", UserSystem);
      textMessage = textMessage.replace("_P2_", nombre);
      textMessage = textMessage.replace("_P3_", Datetime);
      textMessage = textMessage.replace("_P4_", mensaje);

      // Crea el mensaje de correo electrónico.
      Email email = new SimpleEmail();
      //
      email.setHostName(SERVER);
      email.setSmtpPort(PORT);
      //Credenciales
      email.setAuthenticator(new DefaultAuthenticator(USER, PASS));
      email.setSSLOnConnect(true);
      //Asunto - Msg
      email.setSubject(asunto);
      email.setDebug(true);
      email.setStartTLSEnabled(true);
      email.setMsg(mensaje);
      //Enviar E-Mail
      //Correo Emisor
      email.setFrom(USER);
      //Correo(s) Receptor
      for (String correo : correos) {
        email.addTo(correo);
      }
      // Envia el correo
      email.send();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * enviarEmailMultiPart Envia un mensaje de correo electronico con archivos
   * adjuntos
   *
   * @param UserSystem - Nombre de usuario del sistema
   * @param Datetime - Fecha y Hora que se envia el mensaje
   * @param archivos - Listado de rutas de los archivos
   * @param nombre - Nombre del emisor del mensaje
   * @param asunto - Asunto del Mensaje
   * @param mensaje - Cuerpo o contenido del mensaje
   * @param correos - Listado de correo a los cuales sera enviado el mensaje
   * @return True si el mensaje es enviado, False si el mensaje no es enviado
   */
  public boolean enviarEmailMultiPart(String UserSystem, String Datetime, List<String> archivos, String nombre, String asunto, String mensaje, List<String> correos) {
    //Ruta del Archivo - 
    //String attachedFile = "";//Ejm: C:\\Users\\etarazona\\Documents\\pgadmin.log
    try {
      //opcional aqui comprobar el contenido del mensaje
      if (mensaje.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No has introducido tu mensaje. Por favor ingrese el mensaje y vuelva a intentarlo.");
        return false;
      }
      //Formamos el cuerpo del mensaje
      String textMessage = "Solicitud de solicitud de soporte\n"
              + "---------------------------------------------------------------------------------------------------------\n"
              + "Nueva solicitud de soporte de _P1_\n"
              + "Expedido por _P2_ en _P3_\n"
              + "---------------------------------------------------------------------------------------------------------\n"
              + "\n"
              + "Mensaje: \n_P4_\n"
              + "\n"
              + "---------------------------------------------------------------------------------------------------------\n"
              + "Este correo electrónico fue enviado desde la aplicación. \n"
              + "Tenga en cuenta que este es un correo electrónico generado \n"
              + "automáticamente y sus respuestas no irán a ninguna parte."
              + "\n"
              + "© Todos los derechos reservados - Desarrollo gerencial Lolimsa.";

      textMessage = textMessage.replace("_P1_", UserSystem);
      textMessage = textMessage.replace("_P2_", nombre);
      textMessage = textMessage.replace("_P3_", Datetime);
      textMessage = textMessage.replace("_P4_", mensaje);

      // Crea el mensaje de correo electrónico.
      MultiPartEmail email = new MultiPartEmail();
      email.setHostName(SERVER);
      email.setSmtpPort(PORT);

      email.setAuthenticator(new DefaultAuthenticator(USER, PASS));
      email.setSSLOnConnect(true);
      //Asunto - Msg
      email.setSubject(asunto);
      email.setDebug(true);
      email.setStartTLSEnabled(true);
      //Enviar E-Mail
      //Correo Emisor
      email.setFrom(USER);
      //attach file

      for (String archivo : archivos) {
        if (!archivo.trim().isEmpty()) {
          email.attach(new File(archivo));
        }
      }
      //Enviar E-Mail
      //Correo Emisor
      email.setMsg(textMessage);
      //Correo(s) Receptor
      for (String correo : correos) {
        email.addTo(correo);
      }
      // Envia el correo
      email.send();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * enviarEmailMultiPart Envia un mensaje de correo electronico con formato
   * html y archivos adjuntos
   *
   * @param UserSystem Nombre de usuario del sistema
   * @param Datetime Fecha y Hora que se envia el mensaje
   * @param nombre Nombre del emisor del mensaje
   * @param asunto Asunto del Mensaje
   * @param mensaje Cuerpo o contenido del mensaje
   * @param correos Listado de correo a los cuales sera enviado el mensaje
   * @return True si el mensaje es enviado, False si el mensaje no es enviado
   */
  public boolean enviarEmailMultiPartHtml(String UserSystem, String Datetime, String nombre, String asunto, String mensaje, List<String> correos) {
    //Ruta del Archivo - 
    //String attachedFile = "";//Ejm: C:\\Users\\etarazona\\Documents\\pgadmin.log
    try {
      //opcional aqui comprobar el contenido del mensaje
      if (mensaje.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No has introducido tu mensaje. Por favor ingrese el mensaje y vuelva a intentarlo.");
        return false;
      }
      //Formamos el cuerpo del mensaje
      String textMessage = "<!DOCTYPE html>"
              + "<html lang=\"es\">"
              + "<head>"
              + "    <meta charset=\"UTF-8\">"
              + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
              + "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">"
              + "    <title>Mail</title>"
              + "    <style type=\"text/css\">"
              + "        table{"
              + "            max-width: 600px;"
              + "            margin: 0 auto;"
              + "            border-collapse: collapse;"
              + "        }"
              + "        #div{"
              + "            position: absolute;"
              + "        }"
              + "        #cabecera{"
              + "            text-align: center;"
              + "        }"
              + "        #pie{"
              + "            text-align: center;"
              + "            padding: 0;"
              + "        }"
              + "        #img{"
              + "            display: block;"
              + "        }"
              + "        #troncodiv{"
              + "            color: #34495e;"
              + "            margin: 4% 10% 0%;"
              + "            text-align: justify;"
              + "            font-family: sans-serif;"
              + "        }"
              + "        #tronco{"
              + "            background: linear-gradient(90deg, #c3c4c9, #edeeef);"
              + "        }"
              + "        #botones{"
              + "            width: 100%;"
              + "            margin:20px 0;"
              + "            display: inline-block;"
              + "            text-align: center;"
              + "        }"
              + "        #asunto{"
              + "            color: #34495e;"
              + "            margin: 0 0 7px;"
              + "            text-align: center;"
              + "        }"
              + "        .troncofont{"
              + "            font-size: 15px;"
              + "        }"
              + "        #infodiv{"
              + "            margin: 2% 10% 2%;"
              + "            text-align: center;"
              + "            font-family: sans-serif;"
              + "        }"
              + "        #info{"
              + "            color: #34495e;"
              + "            font-size: 12px"
              + "        }"
              + "        #imgrs{"
              + "            width: 12%;"
              + "            height: 12%;"
              + "        }"
              + "    </style>"
              + "</head>"
              + "<body>"
              + "    <table>"
              + "    <tr>"
              + "      <td id=\"cabecera\">"
              + "        <div>"
              + "            <a href=\"https://www.lolimsa.com.pe\" target=\"_blank\">"
              + "                <img id=\"img\" src=\"https://i.postimg.cc/pTwn5Xv1/cabecera.png\""
              + "            /></a>"
              + "        </div>"
              + "      </td>"
              + "    </tr>"
              + "    <tr>"
              + "      <td id=\"tronco\">"
              + "        <div id=\"troncodiv\">"
              + "          <h2 id=\"asunto\">_P1_</h2>"
              + "          <p class=\"troncofont\">"
              + "            Nueva solicitud de <color=\"red\">_P2_</color></LOLIMSA><br />Expedido por: _P3_<br />Fecha: _P4_"
              + "          </p>"
              + "          <p class=\"troncofont\">Mensaje: <br>_P5_</p>"
              + "          <div id=\"botones\">"
              + "            <a href=\"https://www.facebook.com/Lolimsa/\" target=\"_blank\""
              + "              ><img id=\"imgrs\" src=\"https://i.postimg.cc/nLKKJmtc/facebook.png\""
              + "            /></a>"
              + "            <a href=\"https://www.linkedin.com/company/lolimsa/\" target=\"_blank\""
              + "              ><img id=\"imgrs\" src=\"https://i.postimg.cc/KzJnKyz6/linkedin.png\""
              + "            /></a>"
              + "            <a href=\"https://twitter.com/lolimsadigital\" target=\"_blank\""
              + "              ><img id=\"imgrs\" src=\"https://i.postimg.cc/9MWdFfWP/twitter.png\""
              + "            /></a>"
              + "            <a href=\"https://www.youtube.com/user/LolimsaPeru/\" target=\"_blank\""
              + "              ><img id=\"imgrs\" src=\"https://i.postimg.cc/3xmmXGNC/youtube.png\""
              + "            /></a>"
              + "          </div>"
              + "          <br />"
              + "        </div>"
              + "      </td>"
              + "    </tr>"
              + "    <tr>"
              + "      <td id=\"tronco\">"
              + "        <div id=\"infodiv\">"
              + "          <p id=\"info\">"
              + "            Este correo electrónico fue enviado desde la aplicación. Tenga en"
              + "            cuenta que este es un correo electrónico generado automáticamente"
              + "            y sus respuestas no irán a ninguna parte.<br />"
              + "            © Todos los derechos reservados - Desarrollo gerencial Lolimsa."
              + "            2018."
              + "          </p>"
              + "        </div>"
              + "      </td>"
              + "    </tr>"
              + "    <tr>"
              + "      <td id=\"pie\">"
              + "        <a href=\"https://www.lolimsa.com.pe\" target=\"_blank\">"
              + "          <img id=\"img\" src=\"https://i.postimg.cc/BbzHgc2N/pie.png\""
              + "        /></a>"
              + "      </td>"
              + "    </tr>"
              + "  </table>"
              + "</body>"
              + "</html>";

      textMessage = textMessage.replace("_P1_", asunto);
      textMessage = textMessage.replace("_P2_", UserSystem);
      textMessage = textMessage.replace("_P3_", nombre);
      textMessage = textMessage.replace("_P4_", Datetime);
      textMessage = textMessage.replace("_P5_", mensaje);

      // Crea el mensaje de correo electrónico.
      MultiPartEmail email = new MultiPartEmail();
      email.setHostName(SERVER);
      email.setSmtpPort(PORT);

      email.setAuthenticator(new DefaultAuthenticator(USER, PASS));
      email.setSSLOnConnect(true);
      //Asunto - Msg
      email.setSubject(asunto);
      email.setDebug(true);
      email.setStartTLSEnabled(true);
      //Enviar E-Mail
      //Correo Emisor
      email.setFrom(USER, "LOLIMSA");
      //Enviar E-Mail
      //Correo Emisor
      email.setContent(textMessage, "text/html");
      //Correo(s) Receptor
      for (String correo : correos) {
        email.addTo(correo);
      }
      // Envia el correo
      email.send();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   *
   * @return Retorna lo mensajes capturados de la interfaz
   * @throws NoSuchProviderException nspe
   * @throws MessagingException me
   */
  public Message[] leerEmailMutiPartHtml() throws NoSuchProviderException, MessagingException {
    Properties prop = new Properties();
    // Deshabilitamos TLS
    prop.setProperty("mail.pop3.starttls.enable", "false");

    // Hay que usar SSL
    prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    prop.setProperty("mail.pop3.socketFactory.fallback", "false");

    // Puerto 995 para conectarse.
    prop.setProperty("mail.pop3.port", "995");
    prop.setProperty("mail.pop3.socketFactory.port", "995");

    Session sesion = Session.getInstance(prop);
    Store store = sesion.getStore("pop3");
    store.connect(SERVER, USER, PASS);
    Folder folder = store.getFolder("INBOX");
    folder.open(Folder.READ_ONLY);

    Message[] mensajes = folder.getMessages();
    return mensajes;
  }
}
