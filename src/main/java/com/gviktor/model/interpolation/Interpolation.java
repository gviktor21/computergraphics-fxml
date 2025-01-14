package com.gviktor.model.interpolation;

import com.gviktor.model.Point;

import java.util.List;

public abstract class Interpolation {
	protected List<Point> controlPoints;
	public void addControlPoint(Point ctrl) {
		controlPoints.add(ctrl);
	}
	
	public List<Point> getControlPoints() {
		return controlPoints;
	}

	public void setControlPoints(List<Point> controlPoints) {
		this.controlPoints = controlPoints;
	}

	public void clear() {
		controlPoints.removeAll(controlPoints);
	}
	public int numOfControlPoints() {
		return controlPoints.size();
	}
	public Point getControlPointOfIndex(int i) {
		// TODO Auto-generated method stub
		return controlPoints.get(i);
	}
	public abstract float computeCoordAtx(float t);

}