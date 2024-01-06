import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PruebaAutoSign {

    public static void main(String[] args) {
        String urlServicio = "https://wsbanjercito.doc2sign.com/Doc2SignLite.svc?wsdl";

        try {
            URL url = new URL(urlServicio);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Content-Type", "application/json");

            // Obtener la respuesta
            int codigoRespuesta = conexion.getResponseCode();

            if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(conexion.getInputStream()));
                String linea;
                StringBuilder respuesta = new StringBuilder();

                while ((linea = entrada.readLine()) != null) {
                    respuesta.append(linea);
                }
                entrada.close();

                // Mostrar la respuesta
                System.out.println("La URL respondió exitosamente:");
                System.out.println(respuesta.toString());
            } else {
                System.out.println("Hubo un problema al conectarse a la URL. Código de respuesta: " + codigoRespuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}