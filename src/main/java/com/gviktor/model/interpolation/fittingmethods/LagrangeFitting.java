package com.gviktor.model.interpolation.fittingmethods;

import com.gviktor.model.Point;
import com.gviktor.model.Point2D;
import com.gviktor.model.Polygon;
import com.gviktor.model.interpolation.Bezier;
import com.gviktor.model.interpolation.Interpolation;
import com.gviktor.model.interpolation.LagrangeInterpolation;

import java.util.ArrayList;
import java.util.List;

public class LagrangeFitting extends Polygon {

    private int numOfBetweenVertexes;
    private Interpolation interpolx;
    private Interpolation interpoly;
    private float numOfControlPoints = 0.0f;// t increme
    private List<Point> controlPoints;

    public LagrangeFitting(int numOfBetweenVertexes, List<Point> controlPoints) {
        this(numOfBetweenVertexes);
        this.controlPoints = controlPoints;
    }
    public LagrangeFitting(int numOfBetweenVertexes) {
        this.numOfBetweenVertexes=numOfBetweenVertexes;
        vertexes = new ArrayList<Point>();
        controlPoints = new ArrayList<Point>();
        setFitting();
    }

    private void setFitting() {
        interpolx= new LagrangeInterpolation();
        interpoly= new LagrangeInterpolation();
    }

    public void addControlPointT(Point a) {
        controlPoints.add(a);
        interpoly.addControlPoint(new Point2D(numOfControlPoints, a.getY()));
        interpolx.addControlPoint(new Point2D(numOfControlPoints, a.getX()));
        numOfControlPoints += 1.0f;
    }

    @Override
    public void addVertex(Point vertex){
        super.addVertex(vertex);
        addControlPointT(vertex);
    }
    public int getNumOfInnerVertexes() {
        return numOfBetweenVertexes;
    }

    public void setNumOfInnerVertexes(int numOfInnerVertexes) {
        this.numOfBetweenVertexes = numOfInnerVertexes;
    }

    public void computeVertexesWithT() {
        super.clear();
        int numOfCtrlPoints = this.controlPoints.size();
        Point a = controlPoints.get(0);
        vertexes.add(a);
        for (int i = 1; i < numOfCtrlPoints; i++) {
            Point b = controlPoints.get(i);
            computeInnerVertexesBetweenControlPointsWithT(a, b, i - 1);
            vertexes.add(b);
            a = b;
        }
    }

    private void computeInnerVertexesBetweenControlPointsWithT(Point a, Point b, int t) {
        float increment = computeIncrementWithT();
        float currentT = computeCurrentT(t, increment) + increment;
        for (int i = 0; i < numOfBetweenVertexes - 1; i++) {
            Point innerPoint;
            float x = interpolx.computeCoordAtx(currentT);
            float y = interpoly.computeCoordAtx(currentT);
            innerPoint = new Point2D(x, y);
            vertexes.add(innerPoint);
            currentT += increment;
        }
    }

    private float computeCurrentT(int t, float increment) {
        return t;
    }

    private float computeIncrementWithT() {
        return 0.1f;
    }

    @Override
    public void clear() {
        super.clear();
        controlPoints.clear();
        this.numOfControlPoints =0.0f;
    }

    @Override
    public void updateVertexes() {
        computeVertexesWithT();
    }
}
