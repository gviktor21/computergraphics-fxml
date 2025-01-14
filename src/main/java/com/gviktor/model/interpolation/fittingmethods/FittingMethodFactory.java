package com.gviktor.model.interpolation.fittingmethods;

import com.gviktor.model.Polygon;

public class FittingMethodFactory {

    private FittingMethodFactory(){}

    public static Polygon createCurve(CurveFittingMethod curveFittingMethod) {
        switch (curveFittingMethod){
            case BEZIER -> {return new BezierFitting(4);}
            case LAGRANGE -> { return new LagrangeFitting(5);}
        }
        return new LagrangeFitting(10);
    }
}
