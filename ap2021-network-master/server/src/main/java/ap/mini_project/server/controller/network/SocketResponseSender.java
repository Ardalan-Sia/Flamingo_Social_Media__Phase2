package ap.mini_project.server.controller.network;

import ap.mini_project.server.Tools.RandomString;
import ap.mini_project.server.controller.ResponseSender;
import ap.mini_project.shared.events.Event;
import ap.mini_project.shared.gson.Deserializer;
import ap.mini_project.shared.gson.Serializer;
import ap.mini_project.shared.response.LoginResponse;
import ap.mini_project.shared.response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class SocketResponseSender implements ResponseSender {
    private final Socket socket;
    private final PrintStream printStream;
    private final Scanner scanner;
    private final Gson gson;
    private String authToken;

    public SocketResponseSender(Socket socket) throws IOException {
        this.socket = socket;
        this.scanner = new Scanner(socket.getInputStream());
        this.printStream = new PrintStream(socket.getOutputStream());
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Event.class, new Deserializer<>())
                .registerTypeAdapter(Response.class, new Serializer<>())
                .create();

    }

    @Override
    public Event getEvent() {
        String eventString = scanner.nextLine();
        Event event = gson.fromJson(eventString, Event.class);
        if (authToken != null) {
            System.out.println(event.getAuthToken());
            if (!event.getAuthToken().equals(authToken)) {
                throw new Error("unknown authToken");

            }
        }
        return event;
    }

    @Override
    public void sendResponse(Response response) {
        if (response instanceof LoginResponse && ((LoginResponse) response).isSuccess()) {
            System.out.println("Login");
            authToken = new RandomString().nextString();
            response.setAuthToken(authToken);
        }
        printStream.println(gson.toJson(response, Response.class));
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
