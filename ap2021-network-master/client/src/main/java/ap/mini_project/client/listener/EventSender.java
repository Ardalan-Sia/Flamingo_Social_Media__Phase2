package ap.mini_project.client.listener;

import ap.mini_project.shared.events.Event;
import ap.mini_project.shared.response.Response;

public interface EventSender {
    Response send(Event event);

    void close();
}
