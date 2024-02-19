package com.torpill.game.level.structure;

import com.torpill.game.block.Block;
import com.torpill.game.block.Blocks;

public class StructureBridge extends Structure {

	protected StructureBridge(Block[][] data) {

		super(data);
	}

	public static Structure create(int width, int height) {

		if (width <= 0 || height < 0) {
			
			return null;
		}
		
		int w = width * 10;
		int h = height + 4;
		Block[][] data = new Block[w][h];

		for (int i = 0; i < w; i++) {

			for (int j = 0; j < h; j++) {

				data[i][j] = Blocks.air;
			}
		}

		Structure bridge = new StructureBridge(data);

		for (int i = 0; i < width; i++) {

			bridge.add(Blocks.stone, i * 10, h - 3, 10, 3, false);
			bridge.add(Blocks.stone, i * 10 + 3, h - 4, 4, 1, false);
			bridge.add(Blocks.stone, i * 10 + 4, 0, 2, height, false);
		}

		return bridge;
	}
}
