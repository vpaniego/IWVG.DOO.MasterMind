package masterMind.solucion.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import masterMind.solucion.models.Code;

public class Board {

    private HashSet<Code> secretCode;

    Board(int numPlayers, int numberOfGuesses) {
        assert numPlayers > 0;
        assert numberOfGuesses > 0;
        secretCode = new Code().getSecretCode();
    }

    public HashSet<Code> getSecretCode(){
        return this.secretCode;
    }

     boolean existMasterMind() {
		/*assert color != Color.NONE;
		Set<Coordinate> coordinateSet = coordinates.get(color);
		if (coordinateSet.size() != Coordinate.DIMENSION) {
			return false;
		}
		Coordinate[] coordinateArray = coordinateSet
				.toArray(new Coordinate[0]);
		Direction direction = coordinateArray[0].direction(coordinateArray[1]);
		if (direction == Direction.NON_EXISTENT) {
			return false;
		}
		for (int i = 1; i < Coordinate.DIMENSION - 1; i++) {
			if (coordinateArray[i].direction(coordinateArray[i + 1]) != direction) {
				return false;
			}
		}*/
        return true;
    }

    void clear() {
        assert secretCode != null;
        this.secretCode.clear();
    }

}
