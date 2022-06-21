package kr.co.sist.console;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Console extends JFrame  {
	JButton viewBtn, reportBtn, lineBtn;
	public JTextField jtfFirst, jtfLast;

	JCheckBox jcbAll;
	
	
	public Console() {
		JLabel jlbLine, jlbAll;
		
		viewBtn = new JButton("VIEW");
		reportBtn = new JButton("REPORT");
		jtfFirst = new JTextField("FirstLine",10);
		jtfLast = new JTextField("LastLine",10);
		jcbAll = new JCheckBox();
		jlbLine = new JLabel("출력 할 줄 입력 : ");
		jlbAll = new JLabel("전체 줄 출력");
		
		setLayout(null);
		jlbLine.setBounds(30,15,100,30);
		jtfFirst.setBounds(30,60,70,30);
		jtfLast.setBounds(130,60,70,30);
		jcbAll.setBounds(300,60,20,30);
		jlbAll.setBounds(220,60,80,30);
		viewBtn.setBounds(70,150,90,30);
		reportBtn.setBounds(170,150,90,30);
		add(jlbLine);
		add(jtfFirst);
		add(jtfLast);
		add(jcbAll);
		add(jlbAll);
		add(viewBtn);
		add(reportBtn);
		
		ConsoleEvt conEvt = new ConsoleEvt(this);
		viewBtn.addActionListener(conEvt);
		reportBtn.addActionListener(conEvt);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		jcbAll.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() == jcbAll) {
					jtfFirst.setText("0");
					jtfLast.setText("0");
				}//end if
			}//itemStateChanged
		});
		
		setSize(350,270);
		setVisible(true);
	}//Console
	
	

	public static void main(String[] args) {

		new Console();
	}//main

}//class
