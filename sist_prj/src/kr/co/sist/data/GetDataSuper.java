package kr.co.sist.data;

import java.io.IOException;
import java.util.Map;

abstract class GetDataSuper{
	public GetDataSuper() {
	}

	/**
	 * 파일 가져오기
	 * 
	 * @throws IOException
	 */
//	public DemoParent() {
//		FileDialog fd = new FileDialog(this, "파일", FileDialog.LOAD);
//		fd.setVisible(true);
//		String path = fd.getDirectory();
//		String name = fd.getFile();
//
//		file = new File(path + name);
//		
//	}// file

	/**
	 * map을 받아 최다키를 구하는 메소드
	 * 
	 * @param map
	 * @return maxKey
	 */
	public static String getMaxKey(Map<String, Integer> map) {

		String maxKey = null;
		for (String key : map.keySet()) {
			if (maxKey == null || map.get(maxKey) < map.get(key)) {
				maxKey = key;// 키에서 최댓값 구하기
			} // end if
		} // end for
		return maxKey;

	}// end getMaxKey

	///////// 추상메소드 정리///////////////

	abstract public String printKey() throws IOException;

	abstract public String printBrowser() throws IOException;

	abstract public String printTime() throws IOException;

	abstract public String printSuccessFail() throws IOException;

	abstract public String printAbnormalRequest() throws IOException, ArithmeticException;

	abstract public String printErrorRequest() throws IOException, ArithmeticException;

	abstract public String printKey(int firstLine, int lastLine) throws IOException;

	//////// 구현 메소드/////////////
	/**
	 * 입력하는 줄이 없을경우
	 * 
	 * @return
	 */
	public String run() {
		StringBuilder sb = new StringBuilder();
		String line = "---------------------------------------\n";
		try {
			sb.append(printKey()).append(line).append(printBrowser()).append(line).append(printSuccessFail())
					.append(line).append(printTime());
			sb.append(line).append(printAbnormalRequest()).append(line).append(printErrorRequest()).append(line)
					.append(printKey());
		} catch (ArithmeticException ae) {
			ae.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch
		return sb.toString();
	}// run

	/**
	 * 입력하는 줄이 없을 경우
	 * 
	 * @param firstLine
	 * @param lastLine
	 */
	public String run(int firstLine, int lastLine) {
		StringBuilder sb = new StringBuilder();
		String line = "---------------------------------------\n";
		try {
			sb.append(printKey()).append(line).append(printBrowser()).append(line).append(printSuccessFail())
					.append(line).append(printTime());
			sb.append(line).append(printAbnormalRequest()).append(line).append(printErrorRequest()).append(line)
					.append(printKey(firstLine, lastLine));
		} catch (ArithmeticException ae) {
			ae.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		} // end catch
		return sb.toString();
	}// run

}// class
