package ap.mini_project.shared.response;

import ap.mini_project.shared.model.Board;

public class BoardResponse extends Response {
    public BoardResponse(Board board) {
        this.board = board;
    }

    Board board;

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.visitBoard(board);
    }
}
