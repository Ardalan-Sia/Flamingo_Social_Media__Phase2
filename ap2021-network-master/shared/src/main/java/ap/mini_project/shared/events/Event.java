package ap.mini_project.shared.events;

import ap.mini_project.shared.response.Response;
public abstract class Event {
    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public abstract Response visit(EventVisitor eventVisitor);
}
