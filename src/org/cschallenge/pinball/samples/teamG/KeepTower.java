package org.cschallenge.pinball.samples.teamG;

import org.cschallenge.pinball.engine.Heading;
import org.cschallenge.pinball.engine.ITower;
import org.cschallenge.pinball.engine.PinballEngine.TeamType;
import org.cschallenge.pinball.engine.Position;
import org.cschallenge.pinball.engine.TowerPosition;

public class KeepTower implements ITower {

	Position position;
	
	public KeepTower(Position position) {
		this.position = position;
	}
	
	@Override
	public TowerPosition initialize(int turn) {
		return new TowerPosition(position.getX(), position.getY(), 1);
	}

	@Override
	public Heading onCaptureBall(TeamType teamType, Heading heading) {
		return position.getHeading();
	}

	@Override
	public boolean extinguish(int turn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDetectBall(TeamType teamType, Position position) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getDetectionRadius() {
		return 0;
	}

}
