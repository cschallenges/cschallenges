package org.cschallenge.pinball.samples.teamG;

import org.cschallenge.pinball.engine.Heading;
import org.cschallenge.pinball.engine.IPlayer;
import org.cschallenge.pinball.engine.ITower;
import org.cschallenge.pinball.engine.PinballEngine.Team;
import org.cschallenge.pinball.engine.PinballEngine.TeamType;
import org.cschallenge.pinball.engine.PinballEngine.TowerQueue;
import org.cschallenge.pinball.engine.PinballEngine.Turn;
import org.cschallenge.pinball.engine.Position;
import org.cschallenge.pinball.samples.teamA.SearchTower;
import org.cschallenge.pinball.samples.teamA.ShotTower;
import org.cschallenge.pinball.samples.teamB.BlockTower;
import org.cschallenge.pinball.samples.teamB.RandomTower;

public class SamplePlayerG implements IPlayer {

	Team myTeam;
	@Override
	
	public void init(Team team) {
		myTeam = team;
	}

	private Position blockMissing;
	@Override
	
	public void onExpired(ITower tower) {
		if (tower instanceof BlockTower) {
			BlockTower blockTower = (BlockTower) tower;
			blockMissing = blockTower.getPosition();
		}
	}

	public int turnNumber;
	
	int m = 2;
	int n = 2;

	@Override
	public void startTurn(Turn turn, TowerQueue queue) {
		
		if (n < 36) {
			if (turn.turn % 3 == 0) {
				queue.addTower(new ShotTower(new Position(m, n), Heading.EAST));
				m += 6;
				if (m > 36) {
					m = 2;
					n += 6;
				}
				if(n>36){
					n = 2;
				}
			}
			
			
		}
		
		
		
		if (queue.size() == 0) {
//			if (turn.turn == 1){
//				queue.addTower(new SearchTower(24, 24));
//				queue.addTower(new SearchTower(11, 4));
//				queue.addTower(new SearchTower(4, 17));
//  	       	queue.addTower(new SearchTower(30, 30));
//				queue.addTower(new SearchTower(3, 7));
//     			queue.addTower(new SearchTower(35, 5));
//				queue.addTower(new SearchTower(8, 19));
//				queue.addTower(new SearchTower(9, 7));
//				
//				if (turn.turn == 1) {
//					int step = Position.BOARD_SIZE_SQUARES / 4;
//					for (int x = step; x < Position.BOARD_SIZE_SQUARES; x += step) {
//						for (int y = step; y < Position.BOARD_SIZE_SQUARES; y += step) {
//							Position positionY1 = new Position(x, y, Heading.NONE);
//							
//							
//								queue.addTower(new ShotTower(positionY1, Heading.EAST));
//							
//							
//							
//						}
//					}
//				}
			
		}
	}

	private boolean rowClear(int x, int y, Turn turn){

		for(int i= x; i<Position.BOARD_SIZE_SQUARES; i++){
			Position positionTopRow = new Position(i, y, Heading.NONE);
			if(turn.isOccupied(positionTopRow)){
				return false;
			}
		}
		return true;
	}
	
	private boolean isColumnClear(int column,int startingRow, Turn turn){
		
		for(int i= startingRow; i<Position.BOARD_SIZE_SQUARES; i++){
			Position positionColumn = new Position(column, i, Heading.NONE);
			if(turn.isOccupied(positionColumn)){
				return false;
			}
		}
		return true;
	}
	
	private int rightmostClearColumn(int x, int y, Turn turn) {
		
		for (int column = Position.BOARD_SIZE_SQUARES - 1; column > x; column--) {
			if (isColumnClear(column, y, turn)) {
				return column;
			}
		}
		return -1;
	}
	
	@Override
	public void onDetectBall(Turn turn, TowerQueue queue, TeamType teamType, Position position) {
		switch (teamType) 
		{
		case FOE:	
			if (turn.turn > turnNumber) 
			{
				position.advance(2);
				Position positionA = new Position(Position.BOARD_SIZE_SQUARES-1, position.getY(), Heading.NONE);
				if (!(turn.isOccupied(position) || turn.isOccupied(positionA)))
				{
					
					turnNumber = turn.turn+10;
					int column = rightmostClearColumn(position.getX(), position.getY(), turn);
					
					if(column==-1){
						queue.addTower(new ShotTower(position, Heading.SOUTH));
					}
					else if(position.getX()!=column){
							queue.addTower(new ShotTower(position, Heading.EAST));
						}
						
					    if (column != -1) 
						{
							Position positionB = new Position(column, position.getY());
							queue.addTower(new KeepTower(positionB));
							Position positionC = new Position(column, position.getY()+2);		
							queue.addTower(new KeepTower(positionC));
							Position positionD = new Position(column, position.getY()-1);
							queue.addTower(new KeepTower(positionD));
						}
				}
			}
					
	
		
		
		case FRIEND:
			if (turn.turn > turnNumber)
			{
				if (queue.size() == 0) {
					position.advance(1);
					Position position2 = new Position(position.getX(), Position.BOARD_SIZE_SQUARES-1, Heading.NONE);
					if (!(turn.isOccupied(position) || turn.isOccupied(position2))) {
						
						
						Position positionY1 = new Position(position.getX(), position.getY(), Heading.NONE); 
						queue.addTower(new ShotTower(positionY1, Heading.EAST));
						
						
						Position positionX1 = new Position(position.getX(), position.getY(), Heading.NONE);
						queue.addTower(new ShotTower(positionX1, Heading.NORTH));
						
						
						 
						turnNumber = turn.turn;
						if(rowClear(position.getX(),position2.getY(),turn)){
							Position position3 = new Position(position2.getX(),position2.getY(),Heading.NONE);
							queue.addTower(new ShotTower(position3, Heading.EAST));
							
							if(!rowClear(position.getX(),position2.getY()-1,turn)){
								Position position4 = new Position(position2.getX(),position2.getY()-2,Heading.NONE);
								queue.addTower(new ShotTower(position4, Heading.EAST));
							}
							else {
								queue.addTower(new ShotTower(position3, Heading.EAST));
							}
						}
					}
				}
			}
		}
	}
	
}


