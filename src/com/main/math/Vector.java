package com.main.math;

public class Vector {
	
	public int x,y;
	
	public Vector(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector sub(Vector o) {
		return new Vector(this.x - o.x,o.y - this.y);
	}
	
	public double angle() {
		return Math.PI - Math.atan2(y,x);
	}
	
}
