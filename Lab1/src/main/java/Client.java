import java.io.*;
import java.net.Socket;
import java.util.List;


public class Client {

    public static void main(String[] args) throws IOException {
        BufferedReader in;
        BufferedWriter out;
        ReaderClass reader = new ReaderClass();
        List<String> response = reader.readMyFile();
        int ind = 0;

        Socket socket = new Socket("localhost", 8080);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        try {
            while (ind < response.size()) {
                out.write(response.get(ind) + "\n");
                out.flush();
                System.out.println("отправлено серверу: " + response.get(ind));
                String inMes = in.readLine();
                System.out.println("прислал сервер: " + inMes);
                if (inMes.equals("OK")) {
                    System.out.println("Ответ получен все ок)");
                    break;
                }
                ind++;


            }
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
