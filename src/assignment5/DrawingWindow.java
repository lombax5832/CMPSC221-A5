/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author aps5601
 */
public class DrawingWindow extends JFrame{
    //Toolbar
    private JToolBar drawingToolbar = new JToolBar();
    //Buttons for Toolbar
    private JButton buttonDrawingUndo = new JButton("Undo");
    private JButton buttonDrawingClear = new JButton("Clear");
    private JButton buttonFirstColor = new JButton("1st Color...");
    private JButton buttonSecondColor = new JButton("2nd Color...");
    //Labels for Toolbar
    private JLabel labelShape = new JLabel("Shape:");
    private JLabel labelFilled = new JLabel("Filled");
    private JLabel labelGradient = new JLabel("Use Gradient");
    private JLabel labelLineWidth = new JLabel("Line Width:");
    private JLabel labelDashLength = new JLabel("Dash Length:");
    private JLabel labelDashed = new JLabel("Dashed");
    //Combo Box Options
    private String[] comboBoxShapeOptions = {"Line","Oval","Rectangle"};    
    //Combo Box for Toolbar
    private JComboBox comboBoxShape = new JComboBox(comboBoxShapeOptions);
    //Checkboxes for Toolbar
    private JCheckBox checkBoxFilled = new JCheckBox();
    private JCheckBox checkBoxUseGradient = new JCheckBox();
    private JCheckBox checkBoxDashed = new JCheckBox();
    //Textfields for Toolbar
    private JTextField textFieldLineWidth = new JTextField();
    private JTextField textFieldDashLength = new JTextField();
    
    
    
    DrawingWindow(){
        
    }
}
