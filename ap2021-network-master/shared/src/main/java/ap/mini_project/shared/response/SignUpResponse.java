package ap.mini_project.shared.response;

public class SignUpResponse extends LoginResponse{



    public SignUpResponse(boolean success, String message ) {
        super(success , message);

    }
    @Override
    public void visit(ResponseVisitor responseVisitor) {
    responseVisitor.signUp(success , message);
    }
}
