package com.gviktor.model;


import com.gviktor.util.CoordinateComputer;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

public class DrawingTriangle {
     private List<Simple2DLine> lines;

    public DrawingTriangle() {
        this.lines = new ArrayList<>();
    }

    public void generatePoints(float width, float height,float q, float p){
        lines.clear();
        float xa,xb,ya,yb,xc,yc;
        float xa1,xb1,ya1,yb1,xc1,yc1;
        float sidequarter=width/4;
        float sideheight = height/4;
        xa=sidequarter;ya=sideheight;
        xb=(float) (sidequarter*2.0);yb=sideheight*3;
        xc=(float) (sidequarter*3.0);yc=sideheight;
//        float q=0.05f;
//        float p = 1-q;
        for(int i = 0; i < 50; i++) {
            Point pointa = new Point2D(xa,ya);
            Point pointb = new Point2D(xb,yb);
            Point pointc = new Point2D(xc,yc);
            lines.add(new Simple2DLine(pointa,pointb));
            lines.add(new Simple2DLine(pointb,pointc));
            lines.add(new Simple2DLine(pointa,pointc));
            xa1= p*xa+q*xb;
            ya1 = p*ya+q*yb;
            xb1= p*xb+q*xc;
            yb1 = p*yb+q*yc;
            xc1= p*xc+q*xa;
            yc1 = p*yc+q*ya;
            xa=xa1;ya=ya1;xb=xb1;yb=yb1;xc=xc1;yc=yc1;
        }
    }
    public void drawFigure(GraphicsContext graphicsContext){
        final int[] i = {0};
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (i[0] < lines.size()) {
                    Simple2DLine a = lines.get(i[0]);
                    graphicsContext.strokeLine(CoordinateComputer.logicalToDeviceX(a.getPoint_a().getX()), CoordinateComputer.logicalToDeviceY(a.getPoint_a().getY()), CoordinateComputer.logicalToDeviceX(a.getPoint_b().getX()), CoordinateComputer.logicalToDeviceY(a.getPoint_b().getY()));
                    i[0]++;
                }else {
                    this.stop();
                }
            }
        };
        animationTimer.start();
    }

}
