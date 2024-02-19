package com.torpill.game.event;

import com.torpill.game.Game;

public abstract class CubeEvent {
	
	public CubeEvent(Game game) {

		this.game = game;
	}
	
	public void setCanceled(boolean canceled) {
		
		this.canceled = canceled;
		if (this.canceled) {
			
			this.result = Result.CANCELED;
		
		} else {
			
			this.result = Result.DEFAULT;
		}
	}
	
	public Result getResult() {
		
		return this.result;
	}
	
	public abstract void execute();
	
	public boolean isCanceled() {
		
		return this.canceled;
	}
	
	protected Game game;
	protected boolean canceled = false;
	protected Result result = Result.DEFAULT;
}
