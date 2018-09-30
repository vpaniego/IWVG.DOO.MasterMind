package masterMind.solucion.controllers.local;

import masterMind.solucion.models.Game;
import masterMind.solucion..utils.ClosedInterval;

class LocalColocateControllerBuilder {

	private LocalColocateController[][] colocateControllerArray;

	private Game game;

	LocalColocateControllerBuilder(Game game) {
		this.game = game;
		colocateControllerArray = new LocalColocateController[game.getNumPlayers()][2];
	}

	void build(int users) {
		assert new ClosedInterval(0, game.getNumPlayers()).includes(users);

	}

	LocalColocateController getColocateController() {
		int player = game.take().ordinal();
		if (!game.complete()) {
			return colocateControllerArray[player][0];
		} else {
			return colocateControllerArray[player][1];
		}
	}
}
