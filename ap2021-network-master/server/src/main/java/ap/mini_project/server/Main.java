package ap.mini_project.server;

import ap.mini_project.server.controller.SocketManager;

public class Main {
    public static void main(String[] args) {
        SocketManager socketManager = new SocketManager();
        socketManager.start();
    }
}
