package ap.mini_project.shared.events;

import ap.mini_project.shared.response.Response;

public class GetALiveGameEvent extends Event {
    private int id;
    public GetALiveGameEvent(int id) {
        this.id = id;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getALiveGame(id);
    }
}
