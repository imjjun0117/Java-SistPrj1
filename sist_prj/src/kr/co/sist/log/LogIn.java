package kr.co.sist.log;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LogIn extends JFrame {
	private JTextField jtfId;
	private JPasswordField jpfPassword;
	private JButton jbtOk;
	
	


	public LogIn() {
		Font font = new Font("맑은 고딕", Font.BOLD, 20);
		JLabel jlblId = new JLabel("ID");
		JLabel jlblPass = new JLabel("Password"); // JLabel ID, password생성
		JLabel jlbLog = new JLabel("User Login");
		jlbLog.setFont(font);
		jtfId = new JTextField();
		jpfPassword = new JPasswordField();// JTextField ID, password 생성
		jbtOk = new JButton("Sign in");// JButton 로그인 생성

		// 크기와 위치
		setLayout(null);
		jlbLog.setBounds(100, 10, 150, 50);
		jlblId.setBounds(55, 65, 30, 30);
		jlblPass.setBounds(30, 105, 150, 30);
		jtfId.setBounds(100, 65, 155, 30);
		jpfPassword.setBounds(100, 105, 155, 30);
		jbtOk.setBounds(105, 155, 80, 30);

		// 배치
		add(jlbLog);
		add(jlblId);
		add(jlblPass);
		add(jtfId);
		add(jpfPassword);
		add(jbtOk);

		//인스턴스 종료
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		//이벤트 추가
		LogInEvt logEvt = new LogInEvt(this);
		jtfId.addActionListener(logEvt);
		jpfPassword.addActionListener(logEvt);
		jbtOk.addActionListener(logEvt);
		
		setVisible(true);
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// LogIn

	
	
	public JTextField getJtfId() {
		return jtfId;
	}


	public JPasswordField getJpfPassword() {
		return jpfPassword;
	}


	public JButton getJbtOk() {
		return jbtOk;
	}


	public static void main(String[] args) {
		new LogIn();
	}
}// class
