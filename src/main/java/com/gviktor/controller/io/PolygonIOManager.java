package com.gviktor.controller.io;

import com.gviktor.model.Point;
import com.gviktor.model.Point2D;
import com.gviktor.model.Polygon;
import com.gviktor.model.PolygonType;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PolygonIOManager {

    public final static String POLYGON_PATH="polygons/";


    public static void writePolygon(String filename, Polygon polygon) {
        Path path = FileSystems.getDefault().getPath(POLYGON_PATH + filename + ".poly");
        File file = path.toFile();
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            //writeName
            br.write("closable: ");
            br.write(String.valueOf(polygon.getPolygonType().equals(PolygonType.FIRST_SELF_CONTAINED)));
            br.newLine();
            br.write("closed: ");
            br.write(String.valueOf(polygon.isReady()));
            br.newLine();
            //write uniques
            br.write("VERTICES");
            br.write("\n");
            String verticesData = getPolyVerticesString(polygon);
            br.write(verticesData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String getPolyVerticesString(Polygon polygon){
        StringBuilder vertices = new StringBuilder();
        polygon.getVertexes().forEach(v -> vertices.append(v.getX()).append(";").append(v.getY()).append(System.lineSeparator()));
        return vertices.toString();
    }
    public  void deleteCollection(String name) throws IOException {
        Path path = FileSystems.getDefault().getPath(POLYGON_PATH + name+ ".poly");
        Files.delete(path);
    }

    public  static Polygon loadPolygon(String filename) throws FileNotFoundException {
        Path path1 = FileSystems.getDefault().getPath(POLYGON_PATH + filename + ".poly");
        File file = path1.toFile();
        Polygon polygon = new Polygon() {
            @Override
            public void updateVertexes() {

            }
        };
        try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {

            //LOAD metadata (closeable and closed)
            //closable
            String name = readMetadataValue(bfr);
            determinePolygonType(polygon, name);
            //closed
            name = readMetadataValue(bfr);
            polygon.setReady(Boolean.parseBoolean(name));

            //LOAD vertices
            String line= bfr.readLine();//The 'VERTICES' line
            List<Point> vertices = new ArrayList<>();
            while ((line = bfr.readLine()) != null) {
                line = line.trim();
                String[] parts = line.split(";");
                System.out.println(Float.parseFloat(parts[0])+" "+Float.parseFloat(parts[1]));
                Point point = new Point2D(Float.parseFloat(parts[0]),Float.parseFloat(parts[1]));
                vertices.add(point);
            }
            polygon.setVertexes(vertices);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return polygon;
    }

    private static String readMetadataValue(BufferedReader bf) throws IOException {
        String name = bf.readLine();
        int lastIndexOfPoint = name.lastIndexOf(": ");
        if (lastIndexOfPoint <= 0) {
            throw new RuntimeException();
        }
        name = name.substring(lastIndexOfPoint+1).trim();
        return name;
    }

    private static void determinePolygonType(Polygon polygon, String name) {
        boolean closable = Boolean.parseBoolean(name);
        if(closable){
            polygon.setPolygonType(PolygonType.FIRST_SELF_CONTAINED);
        }else{
            polygon.setPolygonType(PolygonType.FREE);
        }
    }

}
