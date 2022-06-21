package kr.co.sist.console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import kr.co.sist.console.report.ReportEvt;
import kr.co.sist.console.view.ViewDialog;
import kr.co.sist.log.LogInEvt;

public class ConsoleEvt implements ActionListener {
	private Console con;

	public ConsoleEvt(Console con) {
		this.con = con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == con.viewBtn) {
			try { // 숫자를 입력하지 않았을 떄 예외를 발생시킨다
				new ViewDialog(con);
			} catch (NumberFormatException ne) { // 줄을 숫자형태로 집어넣었는지 확인
				JOptionPane.showMessageDialog(con, "줄을 맞게 입력했는지 확인해주세요");
			} catch (IndexOutOfBoundsException ie) { // 매개변수 입력 값에 따른 예외처리
				JOptionPane.showMessageDialog(con, "첫번째 빈칸에 큰 수 두 번째 빈칸에 작은 수를 넣어주세요");
				// 경고 다이어로그로 바꿀 것
			} // end catch
		} // end if

		if (e.getSource() == con.reportBtn) {
			if(LogInEvt.getId().equals("root") && LogInEvt.getPassword().equals("1111")) {
				//root 계정으로 접속 시 차단한다.
				JOptionPane.showMessageDialog(con, "문서를 생성할 수 있는 권한이 없음");
			}else {
				// 그 외에 로그인한 계정은 정상적으로 생성
			try {
				new ReportEvt(con);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NumberFormatException ne) { // 줄을 숫자형태로 집어넣었는지 확인
				JOptionPane.showMessageDialog(con, "줄을 맞게 입력했는지 확인해주세요");
			} catch (IndexOutOfBoundsException ie) { // 매개변수 입력 값에 따른 예외처리
				JOptionPane.showMessageDialog(con, "첫번째 빈칸에 큰 수 두 번째 빈칸에 작은 수를 넣어주세요");
				// 경고 다이어로그로 바꿀 것
			} // end catch
			}// end else
		}// end if
	}// end actionPerformed

}
