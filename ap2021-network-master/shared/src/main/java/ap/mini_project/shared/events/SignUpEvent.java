package ap.mini_project.shared.events;

import ap.mini_project.shared.response.Response;

public class SignUpEvent extends LoginEvent{


    public SignUpEvent(String username, String password) {
       super(username , password);
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.signUp(this);
    }


}

