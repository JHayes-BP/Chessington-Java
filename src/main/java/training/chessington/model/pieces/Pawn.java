package training.chessington.model.pieces;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> possibleMoves = new ArrayList<>(); //create list of all possible moves
        //add all moves to the list and return
        if(getColour() == PlayerColour.WHITE){
            if (from.getRow() == 6){
                possibleMoves.add(new Move(from,from.plus(-2,0)));
            }
            possibleMoves.add(new Move(from,from.plus(-1 ,0 )));
        }else if (getColour() == PlayerColour.BLACK){
            if (from.getRow() == 1){
                possibleMoves.add(new Move(from,from.plus(2,0)));
            }
            possibleMoves.add(new Move(from,from.plus(1,0)));
        }
        return possibleMoves;
    }
}
