package hocjavaswing;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class CounterSwing extends JFrame{
	private JLabel lblCounter, lblStep;
	private JTextField txtCounter;
	private JButton btnCounter;
	private JRadioButton rbUp, rbDown;
	private JComboBox<Integer> cboStep;
	private int count = 0 ;
	private boolean countingUp = true;
	private int step = 1;
	
	public CounterSwing() {
		Container cp = this.getContentPane();
		// Set layout
		cp.setLayout(new FlowLayout());
		lblCounter = new JLabel("Counter");
		cp.add(lblCounter);
		txtCounter = new JTextField("20",10);
		cp.add(txtCounter);
		rbUp = new JRadioButton("Up",true);
		cp.add(rbUp);
		rbDown = new JRadioButton("Down",true);
		cp.add(rbDown);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rbUp);
		buttonGroup.add(rbDown);
		lblStep = new JLabel("Step");
		cp.add(lblStep);
		Integer[] steps= {1,2,3,4,5};
		cboStep = new JComboBox<Integer>(steps);
		cboStep.setPreferredSize(new Dimension (50,20));
		cp.add(cboStep);
		btnCounter = new JButton("Counter");
		cp.add(btnCounter); 
		// Set title
		setTitle("COunet Swing");
		// Set frame size
		setSize(400,100);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		rbUp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				countingUp= true;
				
				
			}
		});
		rbDown.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				countingUp= false;
				
			}
		});
		
		cboStep.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange()== ItemEvent.SELECTED) {
					step = (Integer) cboStep.getSelectedItem();
				}
			}
		});
		
		btnCounter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				count = Integer.parseInt(txtCounter.getText());
				if(countingUp) {
					count = count +step;
					
				}
				else {
					count = count - step;
				}
				txtCounter.setText(count+"");
				
			}
		});
	}
	
	public static void main(String[] args) {
		new CounterSwing();
		
	}
	
	
}
