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
        if (getColour() == PlayerColour.WHITE){
            playerMultiplier = -1;
        }else if (getColour() == PlayerColour.BLACK){
            playerMultiplier = 1;
        }

        Coordinates inFront = from.plus(playerMultiplier,0);
        if (moveLimits(inFront) && board.get(inFront) == null){ //if anything in front of piece or empty space, no move
            possibleMoves.add(new Move(from,inFront)); //default move
            //add extra moves from here

            if (from.getRow() == (int)(3.5 - 2.5 * playerMultiplier)){ //first double move
                Coordinates newPos = from.plus(2*playerMultiplier, 0);
                if (moveLimits(newPos) && board.get(newPos)== null){ //checks two spaces ahead
                    possibleMoves.add(new Move(from,newPos));
                }
            }
        }
        return possibleMoves;
    }
    private boolean moveLimits(Coordinates a){
        if (a.getRow() > 7 || a.getRow() < 0){
            return false;
        }
        return true;
    }
}
