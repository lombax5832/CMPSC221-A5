/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

import static assignment5.ShapeTypes.SHAPE_TYPES.LINE;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author TempAdmin2
 */
public class DrawingPanel extends JPanel{
    private ArrayList<ShapeTypes> shapeTypes = new ArrayList<ShapeTypes>();
    
    Graphics2D g2d;
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.g2d = (Graphics2D) g;
        this.setBackground(Color.WHITE);
        
        for(ShapeTypes shape:shapeTypes){
            drawShape(shape);
        }
    }
    
    public void clearDrawingArea(){
        shapeTypes.clear();
        redraw();
    }
    
    private Rectangle2D.Float makeRectangle(ShapeTypes shape){
        return new Rectangle2D.Float(Math.min(shape.getF0(), shape.getF2()), Math.min(shape.getF1(), shape.getF3()), Math.abs(shape.getF2()-shape.getF0()), Math.abs(shape.getF3()-shape.getF1()));
    }
    
    private Ellipse2D.Float makeOval(ShapeTypes shape){
        return new Ellipse2D.Float(Math.min(shape.getF0(), shape.getF2()), Math.min(shape.getF1(), shape.getF3()), Math.abs(shape.getF2()-shape.getF0()), Math.abs(shape.getF3()-shape.getF1()));
    }
    
    private Line2D.Float makeLine(ShapeTypes shape){
        return new Line2D.Float(shape.getF0(), shape.getF1(), shape.getF2(), shape.getF3());
    }
    
    private void applyModifiers(ShapeTypes shape){
        //Colors
        if(shape.getGradient()){
            g2d.setPaint(new GradientPaint(shape.getF0(), shape.getF1(), shape.getColor1(), shape.getF2(), shape.getF3(), shape.getColor2()));
        }
        else {
            g2d.setPaint(shape.getColor1());
        }
        //Dashes and width
        float[] dashes = {shape.getDashLength()};
        if(shape.getDashed()){
            g2d.setStroke(new BasicStroke(shape.getLineWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashes, 0));
        }else{
            g2d.setStroke(new BasicStroke(shape.getLineWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
        }        
    }
    
    public void drawShape(ShapeTypes shape){
        applyModifiers(shape);
        //Get shape
        Shape toDraw = null;
        switch(shape.SHAPE_TYPE){
            case RECTANGLE:
                toDraw = makeRectangle(shape);
                break;
            case OVAL:
                toDraw = makeOval(shape);
                break;
            case LINE:
                toDraw = makeLine(shape);
                break;
        }
        
        //Draw the shape
        if(shape.getFilled() && !(shape.SHAPE_TYPE==LINE)){
            g2d.fill(toDraw);
        }else{
            g2d.draw(toDraw);
        }
    }
    
    public void addShape(ShapeTypes shape){
        shapeTypes.add(shape);
    }
    
    public void removeLastShape(){
        if(shapeTypes.size() > 0){
            shapeTypes.remove(shapeTypes.size() - 1);
            redraw();
        }
    }
    
    public void redraw(){
        this.repaint();
    }
}