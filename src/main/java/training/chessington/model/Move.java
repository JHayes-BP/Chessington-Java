package training.chessington.model;

import java.util.Objects;

public final class Move {
    private final Coordinates from;
    private final Coordinates to;

    public Move(Coordinates from, Coordinates to) {
        this.from = from;
        this.to = to;
    }

    public Coordinates getFrom() {
        return from;
    }

    public Coordinates getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(from, move.from) &&
                Objects.equals(to, move.to);
    }

    public String moveValidation(Board board, PlayerColour colour){
        Coordinates destination = getTo();
        if (moveLimits(destination) && board.get(destination) == null){
            return "empty";
        }else if (moveLimits(destination) && board.get(destination) != null && board.get(destination).getColour() != colour){
            return "enemy";
        }
        return "blocked";
    }
    private boolean moveLimits(Coordinates a){
        if (a.getRow() > 7 || a.getRow() < 0 || a.getCol() > 7  || a.getCol() < 0){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "from " + from + " to " + to;
    }
}
