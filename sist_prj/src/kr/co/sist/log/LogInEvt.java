package kr.co.sist.log;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import kr.co.sist.console.Console;

public class LogInEvt implements ActionListener {
	private LogIn log;
	private static String id, password;
	private static File file;
	private String path, name;

	private static Map<String, String> idInfo = new HashMap<>(); // 계정정보를 map에 담아서 관리한다

	public LogInEvt(LogIn log) {
		this.log = log;
		idInfo.put("admin", "1234");
		idInfo.put("administrator", "12345");
		idInfo.put("root", "1111");

	}// LogInEvt

	@SuppressWarnings("deprecation")
	public void doLogIn() {
		id = log.getJtfId().getText();
		password = log.getJpfPassword().getText();
		if (password.equals(idInfo.get(id))) {
			fileRead();
			new Console();
		} else if (id.equals("") || password.equals("")) { // 아이디나 비밀번호가 누락된 경우
			JOptionPane.showMessageDialog(log, "아이디, 비밀번호를 입력하세요", "", JOptionPane.WARNING_MESSAGE);
		} else { // 아이디나 비밀번호가 틀린 경우
			JOptionPane.showMessageDialog(log, "아이디와 비밀번호가 틀렸습니다", "", JOptionPane.ERROR_MESSAGE);
		} // end else

	}// doLogIn

		public void fileRead() {
			FileDialog fd = new FileDialog(log, "파일선택", FileDialog.LOAD);
			fd.setVisible(true);
			path = fd.getDirectory();
			name = fd.getFile();
			file = new File(path + name);
		}// fileRead

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == log.getJtfId() || e.getSource() == log.getJpfPassword()
				|| e.getSource() == log.getJbtOk()) {
			doLogIn();
			log.dispose();
		} // end if

	}// actionPerformed

	public LogIn getLog() {
		return log;
	}

	public static String getId() {
		return id;
	}

	public static String getPassword() {
		return password;
	}

	public static File getFile() {
		return file;
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public static Map<String, String> getIdInfo() {
		return idInfo;
	}

}// class
