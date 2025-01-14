package com.gviktor.model.interpolation;

import com.gviktor.model.Point;

import java.util.ArrayList;

public class Bezier extends Interpolation {
// It's an approximation but i don't want to create a different class for it
	public Bezier() {
		this.controlPoints = new ArrayList<Point>();
	}
	float Bi(float t,int i) {
		int n =this.numOfControlPoints()-1;
		return (float) ((float) (binomial(n, i)*Math.pow(t,i))*Math.pow(1-t,n-i));
	}
	@Override
	public float computeCoordAtx(float x) {
		int n= numOfControlPoints();
		float fx=0.0f;
		if (n >=2) {
			for (int i=0; i< n ;i++ ) {
				float yi  = controlPoints.get(i).getY();
				fx+=Bi(x,i)*yi;
			}
		}
		return fx;
	}
	private static long binomial(int n, int k)
    {
        if (k>n-k)
            k=n-k;

        long b=1;
        for (int i=1, m=n; i<=k; i++, m--)
            b=b*m/i;
        return b;
    }
}
