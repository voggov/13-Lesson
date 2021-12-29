

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Сервер ждет клиента...");
        ReaderClass reader = new ReaderClass();
        reader.writeInfoIntoMyFile();
        List<String> response = reader.readMyFile();
        int ind = 0;
        String name = "Ilya";

        try (Socket clientSocket = serverSocket.accept();
             InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            System.out.println("Новое соединение: " + clientSocket.getInetAddress().toString());
            int request = 0;
            while ((inputStream.read()) != -1) {
                if (inputStream.read() == ind) {
                    request = -1;
                    outputStream.write(request);
                    System.out.println("Отправлено: " + request);
                    outputStream.flush();
                    break;
                }
                outputStream.write(0);
                System.out.println("отправлено клиенту: " + 0);
                outputStream.flush();
            }
            System.out.println("клиент отключился");
        }
    }
}
