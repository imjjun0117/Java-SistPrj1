package kr.co.sist.console.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.console.Console;
import kr.co.sist.data.GetData;
import kr.co.sist.log.LogInEvt;

@SuppressWarnings("serial")
public class ViewDialog extends JDialog {
	JTextArea jtaData;
	JButton jbtOk;
	Console con;

	public ViewDialog(Console con) {
		super(con, "DATA INFORMATION");
		this.con = con;

		jtaData = new JTextArea("\n------------LOG FILE INFORMATION-----------\n\n");
		jbtOk = new JButton("확인");
		JScrollPane jspData = new JScrollPane(jtaData);
		setLayout(null);
		jspData.setBounds(23, 30, 400, 500);
		jbtOk.setBounds(185,550,60,50);
		add(jspData);
		add(jbtOk);
		
		addWindowListener(new WindowAdapter() {
		//윈도우 닫기
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		jbtOk.addActionListener(new ActionListener() {
		//OK버튼 닫기
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		printData();
		
		setSize(460, 650);
		setVisible(true);

	}//ViewDialog

	public void printData() {
		GetData gd = new GetData();
	
		if (con.jtfFirst.getText().equals("0") && con.jtfLast.getText().equals("0")) {
			jtaData.append(gd.run());
		} else {
		
			int firstLine= Integer.parseInt(con.jtfFirst.getText());
			int lastLine = Integer.parseInt(con.jtfLast.getText());
				if (firstLine > lastLine) {
					throw new IndexOutOfBoundsException();
				} // end if
			jtaData.append(gd.run(firstLine,lastLine));
		
				
			}
		
	}// printData

}
