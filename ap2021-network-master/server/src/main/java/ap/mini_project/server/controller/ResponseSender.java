package ap.mini_project.server.controller;

import ap.mini_project.shared.events.Event;
import ap.mini_project.shared.response.Response;

public interface ResponseSender {
    Event getEvent();

    void sendResponse(Response response);

    void close();
}
