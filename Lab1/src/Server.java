import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        BufferedReader in;
        BufferedWriter out;
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Сервер ждет клиента...");
        String name = "Ilya";
        Socket clientSocket = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        try{

            System.out.println("Новое соединение: " + clientSocket.getInetAddress().toString());
            int request = 0;
            while (true) {
                String clientMes = in.readLine();
                if (clientMes.equals(name)) {
                    out.write("OK");
                    out.flush();
                    System.out.println("Отправлено: " + request);
                    break;
                }
                out.write(clientMes + "NO!\n");
                out.flush();
                System.out.println("отправлено клиенту: " + clientMes + "NO!");

            }
            System.out.println("клиент отключился");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
