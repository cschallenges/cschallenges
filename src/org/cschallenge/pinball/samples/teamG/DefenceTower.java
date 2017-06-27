package org.cschallenge.pinball.samples.teamG;

import org.cschallenge.pinball.engine.Heading;
import org.cschallenge.pinball.engine.ITower;
import org.cschallenge.pinball.engine.PinballEngine.TeamType;
import org.cschallenge.pinball.engine.Position;
import org.cschallenge.pinball.engine.TowerPosition;

public class DefenceTower implements ITower{

	private int x;
	private int y;
	private Heading heading;
	private int turnWhenCreated;
	public DefenceTower(Position position, Heading heading) {
		this.x = position.getX();
		this.y = position.getY();
		this.heading = heading;
	}
	
	@Override
	public TowerPosition initialize(int turn) {
		turnWhenCreated = turn;
		return new TowerPosition(x, y, 1);
	}

	@Override
	public Heading onCaptureBall(TeamType teamType, Heading heading) {
		return this.heading;
	}

	@Override
	public void onDetectBall(TeamType teamType, Position position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean extinguish(int turn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getDetectionRadius() {
		return 5;
	}

}
