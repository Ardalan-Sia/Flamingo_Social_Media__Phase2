package ap.mini_project.shared.events;

import ap.mini_project.shared.response.Response;

public class GetPersonalInfo extends Event{
    @Override
    public Response visit(EventVisitor eventVisitor) {
       return eventVisitor.getPersonalInfo();
    }
}
