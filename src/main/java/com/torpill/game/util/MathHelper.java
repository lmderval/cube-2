package com.torpill.game.util;


public class MathHelper {

	public static double round(double value, int n) {
		
		return Math.round(value * Math.pow(10, n)) / Math.pow(10, n);
	}
}
