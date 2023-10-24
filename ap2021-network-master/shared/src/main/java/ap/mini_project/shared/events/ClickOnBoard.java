package ap.mini_project.shared.events;

import ap.mini_project.shared.response.Response;

public class ClickOnBoard extends Event {
    private final int x, y;

    public ClickOnBoard(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.clickOnBoard(x, y);
    }
}
