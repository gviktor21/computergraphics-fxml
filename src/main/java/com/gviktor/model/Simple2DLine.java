package com.gviktor.model;

public class Simple2DLine {
    private Point point_a;
    private Point point_b;

    public Simple2DLine(Point point_a, Point point_b) {
        this.point_a = point_a;
        this.point_b = point_b;
    }

    public Point getPoint_a() {
        return point_a;
    }

    public void setPoint_a(Point point_a) {
        this.point_a = point_a;
    }

    public Point getPoint_b() {
        return point_b;
    }

    public void setPoint_b(Point point_b) {
        this.point_b = point_b;
    }
}
