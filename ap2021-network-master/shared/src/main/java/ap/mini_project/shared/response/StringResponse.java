package ap.mini_project.shared.response;

public class StringResponse extends Response{
    private final String response;
    public StringResponse(String response) {
        this.response = response;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.getStringResponse(response);
    }
}
