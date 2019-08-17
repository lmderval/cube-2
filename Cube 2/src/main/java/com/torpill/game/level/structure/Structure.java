package com.torpill.game.level.structure;

import com.torpill.game.block.Block;

public abstract class Structure {
	
	protected Structure(Block data[][]) {

		this.data = data;
	}
	
	public Block[][] getData() {
		
		return this.data;
	}
	
	public void add(Block block, int x, int y, int width, int height, boolean decoration) {

		for (int bx = x; bx < x + width; bx++) {

			if (bx >= this.data.length) {

				continue;
			}

			for (int by = y; by < y + height; by++) {

				if (by >= this.data[bx].length) {

					continue;
				}

				if (decoration) {

					this.data[bx][by] = block.decoration;

				} else {

					this.data[bx][by] = block;
				}
			}
		}
	}
	
	private Block data[][];
}
