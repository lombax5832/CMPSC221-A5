/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

import assignment5.ShapeTypes.SHAPE_TYPES;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author aps5601
 */
public class DrawingWindow extends JFrame{
    //Toolbar
    private final JPanel drawingToolbar = new JPanel();
    private final JPanel drawingToolbarTop = new JPanel();
    private final JPanel drawingToolbarBottom = new JPanel();
    private final JPanel panelMouseCoords = new JPanel();
    private final DrawingPanel drawingPanel = new DrawingPanel();
    //Buttons for Toolbar
    private final JButton buttonDrawingUndo = new JButton("Undo");
    private final JButton buttonDrawingClear = new JButton("Clear");
    private final JButton buttonFirstColor = new JButton("1st Color...");
    private final JButton buttonSecondColor = new JButton("2nd Color...");
    //Labels for Toolbar
    private final JLabel labelShape = new JLabel("Shape:");
    private final JLabel labelLineWidth = new JLabel("Line Width:");
    private final JLabel labelDashLength = new JLabel("Dash Length:");
    //Combo Box Options
    private final String[] comboBoxShapeOptions = {"Rectangle","Oval","Line"};
    //Combo Box for Toolbar
    private final JComboBox comboBoxShape = new JComboBox(comboBoxShapeOptions);
    //Checkboxes for Toolbar
    private final JCheckBox checkBoxFilled = new JCheckBox("Filled");
    private final JCheckBox checkBoxUseGradient = new JCheckBox("Use Gradient");
    private final JCheckBox checkBoxDashed = new JCheckBox("Dashed");
    //Textfields for Toolbar
    private final JTextField textFieldLineWidth = new JTextField("1",2);
    private final JTextField textFieldDashLength = new JTextField("1",2);
    //Layout to be used
    private final FlowLayout toolBarLayout = new FlowLayout();
    //Label for mouse Coordinates
    private final JLabel labelMouseCoords = new JLabel("(0,0)");
    //ArrayList to hold shapes
    private ShapeTypes tempShape;
    //Colors
    private Color color1 = Color.GRAY;
    private Color color2 = Color.GRAY;
    
    DrawingWindow() {
        super("Drawing");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        
        this.setMinimumSize(new Dimension(640, 480));
        this.add(drawingToolbarTop);
        this.add(drawingToolbarBottom);
        this.add(drawingPanel);
        this.add(panelMouseCoords);
        this.initListeners();
    }
    
    
    
    private void initComponents(){
        //Toolbar stuff
        drawingToolbarTop.setLayout(toolBarLayout);
        
        drawingToolbarTop.add(buttonDrawingUndo);
        drawingToolbarTop.add(buttonDrawingClear);
        drawingToolbarTop.add(labelShape);
        drawingToolbarTop.add(comboBoxShape);
        drawingToolbarTop.add(checkBoxFilled);
        drawingToolbarBottom.add(checkBoxUseGradient);
        drawingToolbarBottom.add(buttonFirstColor);
        drawingToolbarBottom.add(buttonSecondColor);
        drawingToolbarBottom.add(labelLineWidth);
        drawingToolbarBottom.add(textFieldLineWidth);
        drawingToolbarBottom.add(labelDashLength);
        drawingToolbarBottom.add(textFieldDashLength);
        drawingToolbarBottom.add(checkBoxDashed);
        
        drawingToolbarTop.setMaximumSize(new Dimension(640, 20));
        drawingToolbarBottom.setMaximumSize(new Dimension(640, 20));
        
        //Mouse Coords
        panelMouseCoords.setLayout(new BorderLayout());
        panelMouseCoords.add(labelMouseCoords, BorderLayout.WEST);
        panelMouseCoords.setMinimumSize(new Dimension(640, 12));
        panelMouseCoords.setSize(new Dimension(this.getWidth(), 12));
        panelMouseCoords.setMaximumSize(new Dimension(Integer.MAX_VALUE, 12));
    }
    
    private static String mouseLocationString(Point point){
        return "("+(int)point.getX()+" ,"+(int)point.getY()+")";
    }
    
    private SHAPE_TYPES getSHAPE_TYPE(String str){
        switch (str){
            case "Rectangle":
                return SHAPE_TYPES.RECTANGLE;
            case "Oval":
                return SHAPE_TYPES.OVAL;
            default:
                return SHAPE_TYPES.LINE;
        }
    }
    
    private void initListeners(){
        buttonDrawingUndo.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawingPanel.removeLastShape();
                }
            }
        );
        
        buttonDrawingClear.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawingPanel.clearDrawingArea();
                }
            }
        );
        
        buttonFirstColor.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    color1 = JColorChooser.showDialog(DrawingWindow.this, "Choose Color 1", color1);
                }

            }
        );
        
        buttonSecondColor.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    color2 = JColorChooser.showDialog(DrawingWindow.this, "Choose Color 2", color2);
                }

            }
        );
        
        drawingPanel.addMouseMotionListener(
            new MouseMotionListener(){
                @Override
                public void mouseDragged(MouseEvent e) {
                    tempShape.setPoint2(e.getPoint());
                    drawingPanel.redraw();
                    drawingPanel.drawShape(tempShape);
                }

                @Override
                public void mouseMoved(MouseEvent e) {
                    labelMouseCoords.setText(mouseLocationString(e.getComponent().getMousePosition()));
                }
            }
        );
        
        drawingPanel.addMouseListener(
            new MouseListener(){
                @Override
                public void mousePressed(MouseEvent e) {
                    tempShape = new ShapeTypes(e.getPoint(),e.getPoint(),checkBoxFilled.isSelected(),checkBoxUseGradient.isSelected(),checkBoxDashed.isSelected(),Integer.parseInt(textFieldLineWidth.getText()),Integer.parseInt(textFieldDashLength.getText()),color1,color2,getSHAPE_TYPE((String) comboBoxShape.getSelectedItem()));
                    drawingPanel.addShape(tempShape);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    tempShape.setPoint2(e.getPoint());
                    drawingPanel.redraw();
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {}
                @Override
                public void mouseExited(MouseEvent e) {}
                @Override
                public void mouseClicked(MouseEvent e) {}
            }
        );
    }
    
}