package ap.mini_project.shared.events;

import ap.mini_project.shared.response.Response;

public class LoginEvent extends Event{

    protected String username;
    protected String password;

    public LoginEvent(String username , String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return null;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
