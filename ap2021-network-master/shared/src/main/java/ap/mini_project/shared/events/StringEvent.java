package ap.mini_project.shared.events;

import ap.mini_project.shared.response.Response;

public class StringEvent extends Event{
    private final String event;
    public StringEvent(String event) {
        this.event =event;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getStringEvent(event);
    }
}
