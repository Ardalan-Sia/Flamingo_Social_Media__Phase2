package ap.mini_project.shared.events;

import ap.mini_project.shared.response.Response;

public class GetPlayerList extends Event{
    @Override
    public Response visit(EventVisitor eventVisitor) {
       return eventVisitor.getPlayerList();
    }
}
