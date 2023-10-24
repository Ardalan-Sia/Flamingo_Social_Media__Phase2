package ap.mini_project.client.listener.network;

import ap.mini_project.client.listener.EventSender;
import ap.mini_project.shared.events.Event;
import ap.mini_project.shared.events.LoginEvent;
import ap.mini_project.shared.gson.Deserializer;
import ap.mini_project.shared.gson.Serializer;
import ap.mini_project.shared.response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketEventSender implements EventSender {
    private final Socket socket;
    private String authToken;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;

    public SocketEventSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new Deserializer<>())
                .registerTypeAdapter(Event.class, new Serializer<>())
                .create();
    }

    @Override
    public Response send(Event event) {
        if (!(event instanceof LoginEvent))
        event.setAuthToken(authToken);
        String eventString = gson.toJson(event, Event.class);
        printStream.println(eventString);
        String responseString = scanner.nextLine();
        System.out.println(responseString);
        Response response = gson.fromJson(responseString, Response.class);
        if (authToken == null)
        authToken = response.getAuthToken();
        System.out.println(authToken);
        return response;
    }

    @Override
    public void close() {
        try {
            printStream.close();
            scanner.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
