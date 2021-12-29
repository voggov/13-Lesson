import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Ожидание соединения...");

        try (Socket clientSocket = serverSocket.accept();
             InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            {
                while (true) {
                    byte[] buf = new byte[128];
                    int readBytes = inputStream.read(buf);
                    String line = new String(buf, 0, readBytes);
                    if (line.equals("STOP")){
                        outputStream.write(-1);
                        break;
                    }
                    System.out.println("Пришло: " + line);
                    line += "\\(от сервера)";
                    outputStream.write(line.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
