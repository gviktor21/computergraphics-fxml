package com.gviktor.model;

import java.util.Comparator;

public class Point2D extends Point{

	public static final Comparator<Point> CLOCKWISE_COMPARATOR  =
			Comparator.comparing(Point::getX)
			.thenComparing(Comparator.comparing(Point::getY).reversed());
	public Point2D(float x, float y){
		this.x = x; this.y = y;
	}
	public Point2D() {
		this.x = 0.00f; this.y = 0.00f;	
	}
}
