package com.gviktor.model;

public class Triangle {
 private Point a,b,c;

public Triangle(Point a, Point b, Point c) {
	this.a = a;
	this.b = b;
	this.c = c;
}

public Point getA() {
	return a;
}

public void setA(Point a) {
	this.a = a;
}

public Point getB() {
	return b;
}

public void setB(Point b) {
	this.b = b;
}

public Point getC() {
	return c;
}

public void setC(Point c) {
	this.c = c;
}

}
