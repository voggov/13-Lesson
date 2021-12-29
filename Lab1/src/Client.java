
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Client {
    public static void main(String[] args) throws FileNotFoundException {
        ReaderClass reader = new ReaderClass();
        List<String> response = reader.readMyFile();
        int ind = 0;
        String name = "Ilya";

        try (Socket socket = new Socket("localhost", 8080);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            outputStream.write(ind);
            System.out.println("отправлено серверу: " + response.get(ind));
            while ((inputStream.read()) != -1) {
                System.out.println("прислал сервер: " + inputStream.read());
                if (inputStream.read() == 1) {
                    System.out.println("Ответ получен все ок)");
                    outputStream.write(-1);
                    outputStream.flush();
                    break;
                }
                outputStream.flush();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
