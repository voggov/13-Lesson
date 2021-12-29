import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        int count = 0;
        String[] str1 = {"Wasap", "Name", "Ilya", "STOP", "NeIlya", "Divan", "STOP"};
        Socket clientSocket = new Socket("localhost", 8080);
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();
        while (count < str1.length) {
            outputStream.write(str1[count].getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            byte[] data = new byte[128];
            int readBytes = inputStream.read(data);
            System.out.println("Ответ:" + new String(data, 0, readBytes));
            count++;
        }
        clientSocket.close();
    }

}
