package com.torpill.game.util;

import com.torpill.game.block.Block;
import com.torpill.game.block.Blocks;
import com.torpill.game.entity.Entity;

public class MovingHelper {

	public MovingHelper(Entity entity) {

		this.INCREMENT = 0.05;
		this.entity = entity;
	}

	public void move(Block[][] area) {

		if (this.entity.motionX != 0) {

			for (double x = 0; x < Math.abs(this.entity.motionX); x += this.INCREMENT) {

				double dx = MathHelper.round(this.entity.motionX / Math.abs(this.entity.motionX) * this.INCREMENT, 3);
				double dy = MathHelper.round(this.entity.motionY / Math.abs(this.entity.motionX) * this.INCREMENT, 3);

				if (this.checkNextPos(area, dx, 0)) this.entity.move(dx, 0);
				if (this.checkNextPos(area, 0, dy)) this.entity.move(0, dy);
			}

		} else {

			for (double y = 0; y < Math.abs(this.entity.motionY); y += this.INCREMENT) {

				double dx = MathHelper.round(this.entity.motionX / Math.abs(this.entity.motionY) * this.INCREMENT, 3);
				double dy = MathHelper.round(this.entity.motionY / Math.abs(this.entity.motionY) * this.INCREMENT, 3);

				if (this.checkNextPos(area, dx, 0)) this.entity.move(dx, 0);
				if (this.checkNextPos(area, 0, dy)) this.entity.move(0, dy);
			}
		}
	}

	public boolean checkNextPos(Block[][] area, double dx, double dy) {

		double nx = MathHelper.round(this.entity.getX() + dx, 2);
		double ny = MathHelper.round(this.entity.getY() + dy, 2);
		int ceilx = (int) Math.ceil(nx + this.entity.getWidth());
		int floorx = (int) Math.floor(nx);
		int ceily = (int) Math.ceil(ny + this.entity.getHeight());
		int floory = (int) Math.floor(ny);

		for (int i = floorx; i < ceilx; i++) {

			for (int j = floory; j < ceily; j++) {

				try {

					Block b = area[i][j];

					if (!b.isFluid() && b != Blocks.air && !b.isDecoration()) {

						return false;
					}

				} catch (ArrayIndexOutOfBoundsException e) {

					return false;
				}
			}
		}

		return true;
	}

	public boolean checkGround(Block[][] area) {

		double dy = MathHelper.round(this.entity.motionY / Math.abs(this.entity.motionY) * this.INCREMENT, 3);

		if (this.checkNextPos(area, 0, dy)) return false;

		return true;
	}

	public final double INCREMENT;
	private Entity entity;
}
