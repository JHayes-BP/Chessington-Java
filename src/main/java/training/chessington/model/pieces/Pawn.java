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


        Move inFront = new Move(from,from.plus(playerMultiplier,0));
        if (inFront.moveValidation(board,getColour()).equals("empty")){ //if anything in front of piece or empty space, no move
            possibleMoves.add(inFront); //default move
            firstMoveDoubleStep(from, board, possibleMoves); //double step first move
        }
        diagonalTake(from, board, possibleMoves); //diagonal take

        return possibleMoves;
    }

    private void diagonalTake(Coordinates from, Board board, List<Move> possibleMoves) {
        Move leftTake = new Move(from, from.plus(playerMultiplier,-1));
        if (leftTake.moveValidation(board,getColour()).equals("enemy")){
            possibleMoves.add(leftTake);
        }
        Move rightTake = new Move(from,from.plus(playerMultiplier,1));
        if (rightTake.moveValidation(board,getColour()).equals("enemy")){
            possibleMoves.add(rightTake);
        }
    }

    private void firstMoveDoubleStep(Coordinates from, Board board, List<Move> possibleMoves) {
        if (from.getRow() == (int)(3.5 - 2.5 * playerMultiplier)){ //first double move (checks row)
            Move doubleMove = new Move(from,from.plus(2*playerMultiplier,0));
            if (doubleMove.moveValidation(board,getColour()).equals("empty")){ //checks two spaces ahead
                possibleMoves.add(doubleMove);
            }
        }
    }
}
