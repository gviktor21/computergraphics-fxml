package com.gviktor.model;

import java.util.ArrayList;
import java.util.List;

import static com.gviktor.util.CoordinateComputer.pixelSize;

public abstract class Polygon {

	private PolygonType polygonType;
	private boolean ready;
	public static final int difference = 20;

	protected List<Point> vertexes;

	public Polygon() {
		vertexes = new ArrayList<Point>();
		polygonType = PolygonType.FREE;
	}

	public Polygon(PolygonType polygonType) {
		vertexes = new ArrayList<Point>();
		this.polygonType = polygonType;
	}

	private boolean validatePolygonClosure(Point vertex) {
		if (vertexes.size() < 2) {
			return false;
		}
		float dx = vertex.getX() - vertexes.get(0).getX();
		float dy = vertex.getY() - vertexes.get(0).getY();
		System.out.println("dx*dy: " + dx * dx + dy * dy + " < difference*pixelSize*pixelSize " + difference * pixelSize * pixelSize);
		if (vertexes.size() > 0 && dx * dx + dy * dy < difference * pixelSize * pixelSize) {
			ready = true;
			return true;
		}
		return false;
	}

	public void addVertex(Point a) {
		if (polygonType == PolygonType.FIRST_SELF_CONTAINED && validatePolygonClosure(a)) {
			System.out.println("Polygon is closed");
			return;
		}
		if (!ready) {
			this.vertexes.add(a);
		}
	}

	public void clear() {
		this.vertexes.removeAll(vertexes);
	}

	public int size() {
		return vertexes.size();
	}

	public Point getVertexOfIndex(int index) {
		return vertexes.get(index);
	}

	public boolean isReady() {
		return ready;
	}

	public PolygonType getPolygonType() {
		return polygonType;
	}

	public void setPolygonType(PolygonType polygonType) {
		this.polygonType = polygonType;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public List<Point> getVertexes() {
		return vertexes;
	}

	public void setVertexes(List<Point> vertexes) {
		this.vertexes = vertexes;
	}

	public void clockwiseOrder(){
		vertexes.sort(Point2D.CLOCKWISE_COMPARATOR);
	}

	public abstract void updateVertexes();
}
