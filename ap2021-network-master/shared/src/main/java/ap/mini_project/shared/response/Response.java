package ap.mini_project.shared.response;

public abstract class Response {
    private String authToken;
    public abstract void visit(ResponseVisitor responseVisitor);

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
