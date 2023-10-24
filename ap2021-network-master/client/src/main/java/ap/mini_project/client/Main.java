package ap.mini_project.client;

import ap.mini_project.client.listener.MainController;
import ap.mini_project.client.listener.network.SocketEventSender;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 5050);
        MainController controller = new MainController(new SocketEventSender(socket));
        controller.start();
    }
}
