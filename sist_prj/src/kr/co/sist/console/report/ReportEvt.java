package kr.co.sist.console.report;

import java.awt.FileDialog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import kr.co.sist.console.Console;
import kr.co.sist.data.GetData;

public class ReportEvt {
	Console con;

	/**
	 * 파일다이어로그를 통해 파일 경로와 이름을 지정 후 데이터를 저장합니다.
	 * 
	 * @param con
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public ReportEvt(Console con) throws IOException {
		this.con = con;

		FileDialog fd = new FileDialog(con, "파일을 저장합니다", FileDialog.SAVE); 
		fd.setVisible(true);
		String directory = fd.getDirectory();
		String name = fd.getFile();
		File file = new File(directory + name);
		//filedialog로 경로 설정
		GetData gd = new GetData();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		if (con.jtfFirst.getText().equals("0") && con.jtfLast.getText().equals("0")) {// 0,0 입력이면 전체줄
			bw.write(gd.run());
		} else {
			int firstLine = Integer.parseInt(con.jtfFirst.getText());
			int lastLine = Integer.parseInt(con.jtfLast.getText());
			if (firstLine > lastLine) {
				//라인 수의 순서가 잘못 되었을 경우 예외처리 
				throw new IndexOutOfBoundsException();
			} // end if
			bw.write(gd.run(firstLine, lastLine));
		} // end else
		bw.flush();
		bw.close();
	}// ReportEvt
}// class
