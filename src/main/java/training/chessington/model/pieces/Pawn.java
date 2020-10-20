package training.chessington.model.pieces;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    private int playerMultiplier = 0;

    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }
    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {

        List<Move> possibleMoves = new ArrayList<>(); //create list of all possible moves
        //add all moves to the list and return

        playerMultiplier = (getColour() == PlayerColour.BLACK ? 1 : -1);

        Coordinates inFront = from.plus(playerMultiplier,0);
        if (moveValidation(inFront,board).equals("empty")){ //if anything in front of piece or empty space, no move
            possibleMoves.add(new Move(from,inFront)); //default move

            firstMoveDoubleStep(from, board, possibleMoves); //double step first move
        }
        diagonalTake(from, board, possibleMoves); //diagonal take

        return possibleMoves;
    }

    private void diagonalTake(Coordinates from, Board board, List<Move> possibleMoves) {
        Coordinates leftTake = from.plus(playerMultiplier,-1);
        if (moveValidation(leftTake, board).equals("enemy")){
            possibleMoves.add(new Move(from,leftTake));
        }
        Coordinates rightTake = from.plus(playerMultiplier,1);
        if (moveValidation(rightTake, board).equals("enemy")){
            possibleMoves.add(new Move(from,rightTake));
        }
    }

    private void firstMoveDoubleStep(Coordinates from, Board board, List<Move> possibleMoves) {
        if (from.getRow() == (int)(3.5 - 2.5 * playerMultiplier)){ //first double move (checks row)
            Coordinates doubleMove = from.plus(2*playerMultiplier, 0);
            if (moveValidation(doubleMove, board).equals("empty")){ //checks two spaces ahead
                possibleMoves.add(new Move(from,doubleMove));
            }
        }
    }

    private String moveValidation(Coordinates a,Board board){
        if (moveLimits(a) && board.get(a) == null){
            return "empty";
        }else if (moveLimits(a) && board.get(a) != null && board.get(a).getColour() != getColour()){
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
}
