package com.torpill.game.event.entity;

import com.torpill.game.Game;
import com.torpill.game.block.Block;
import com.torpill.game.entity.Entity;
import com.torpill.game.event.CubeEvent;
import com.torpill.game.event.Result;
import com.torpill.game.util.damage.DamageSource;
import com.torpill.game.util.damage.FallingState;

public abstract class EntityEvent extends CubeEvent {

	public EntityEvent(Game game, Entity entity) {

		super(game);

		this.entity = entity;
	}

	public static class EntityLivingEvent extends EntityEvent {

		public EntityLivingEvent(Game game, Entity entity) {

			super(game, entity);
		}

		@Override
		public void execute() {

			this.result = Result.SUCCESS;
		}
	}

	public static class EntityFallingEvent extends EntityEvent {

		public EntityFallingEvent(Game game, Entity entity, Block block) {

			super(game, entity);

			this.block = block;
		}

		@Override
		public void execute() {

			DamageSource source = new DamageSource(new FallingState(this.block));
			this.entity.attackEntityFrom(source);
			
			this.result = Result.SUCCESS;
		}

		protected Block block;
	}

	public static class EntityDamagingEvent extends EntityEvent {

		public EntityDamagingEvent(Game game, Entity entity, DamageSource source) {

			super(game, entity);
			
			this.source = source;
		}

		@Override
		public void execute() {

			this.entity.attackEntityFrom(this.source);

			this.result = Result.SUCCESS;
		}
		
		protected DamageSource source;
	}

	protected Entity entity;
}
