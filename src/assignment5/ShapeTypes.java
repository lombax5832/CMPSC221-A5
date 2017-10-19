/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author TempAdmin2
 */
public class ShapeTypes {
    public static enum SHAPE_TYPES{
        RECTANGLE("Rectangle"),
        OVAL("Oval"),
        LINE("Line");
        
        private final String str;
        
        SHAPE_TYPES(String str){
            this.str = str;
        }
        
        @Override
        public String toString() {
            return str;
        }
    }
    
    private final float[] fields = new float[4];
    
    private boolean filled = false;
    private boolean gradient = false;
    private boolean dashed = false;
    
    private int lineWidth = 0;
    private int dashLength = 0;
    
    private final Color color1;
    private final Color color2;
    
    public final SHAPE_TYPES SHAPE_TYPE;
    
    ShapeTypes(float f0, float f1, float f2, float f3, boolean f4, boolean f5, 
            boolean f6, int f7, int f8, Color f9, Color f10, SHAPE_TYPES shapeType){
        fields[0] = f0;
        fields[1] = f1;
        fields[2] = f2;
        fields[3] = f3;
        filled =    f4;
        gradient =  f5;
        dashed =    f6;
        lineWidth = f7;
        dashLength =f8;
        color1 =    f9;
        color2 =    f10;
        SHAPE_TYPE =shapeType;
    }
    
    ShapeTypes(Point f0, Point f1, boolean f4, boolean f5, boolean f6, 
            int f7, int f8, Color f9, Color f10, SHAPE_TYPES shapeType){
        fields[0] = (float) f0.getX();
        fields[1] = (float) f0.getY();
        fields[2] = (float) f1.getX();
        fields[3] = (float) f1.getY();
        filled =    f4;
        gradient =  f5;
        dashed =    f6;
        lineWidth = f7;
        dashLength =f8;
        color1 =    f9;
        color2 =    f10;
        SHAPE_TYPE = shapeType;
    }
    
    public float getF0(){
        return fields[0];
    }
    public float getF1(){
        return fields[1];
    }
    public float getF2(){
        return fields[2];
    }
    public float getF3(){
        return fields[3];
    }
    public boolean getFilled(){
        return filled;
    }
    public boolean getGradient(){
        return gradient;
    }
    public boolean getDashed(){
        return dashed;
    }
    public int getLineWidth(){
        return lineWidth;
    }
    public int getDashLength(){
        return dashLength;
    }
    public Color getColor1(){
        return color1;
    }
    public Color getColor2(){
        return color2;
    }
    public void setPoint1(Point point){
        fields[0] = (float) point.getX();
        fields[1] = (float) point.getY();
    }
    public void setPoint2(Point point){
        fields[2] = (float) point.getX();
        fields[3] = (float) point.getY();
    }
}
