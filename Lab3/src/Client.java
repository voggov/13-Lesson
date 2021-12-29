import java.io.*;
import java.net.Socket;



class ClientListener {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private String addr;
    private int port;
    private String nickname;


    public ClientListener(String addr, int port) {
        this.addr = addr;
        this.port = port;
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            System.err.println("Ошибка подключения");
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pressNickname();
            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    private void pressNickname() {
        System.out.print("Введите ник: ");
        try {
            nickname = inputUser.readLine();
            out.write("Здравствуй " + nickname + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = in.readLine();
                    if (str.equals("stop")) {
                        break;
                    }
                    System.out.println(str);
                }
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
    }

    public class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                String userWord;
                try{
                    userWord = inputUser.readLine();
                    if (userWord.equals("stop")) {
                        out.write("stop" + "\n");
                        break;
                    } else {
                        out.write(nickname + ": " + userWord + "\n");
                    }
                    out.flush();
                } catch (IOException e) {
                   e.printStackTrace();

                }

            }
        }
    }
}

public class Client {

    public static String ipAddr = "localhost";
    public static int port = 8080;


    public static void main(String[] args) {
        new ClientListener(ipAddr, port);
    }
}