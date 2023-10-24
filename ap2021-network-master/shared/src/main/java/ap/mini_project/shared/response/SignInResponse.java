package ap.mini_project.shared.response;

public class SignInResponse extends LoginResponse{

    public SignInResponse(boolean success, String message ) {
        super(success  , message);
    }


    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.signIn(success , message);
    }
}
