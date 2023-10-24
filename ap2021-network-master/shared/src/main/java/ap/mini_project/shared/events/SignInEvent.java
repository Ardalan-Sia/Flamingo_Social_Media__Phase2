package ap.mini_project.shared.events;

import ap.mini_project.shared.response.Response;

public class SignInEvent extends LoginEvent {

    public SignInEvent(String username, String password) {
        super(username , password);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.signIn(this);
    }


}
