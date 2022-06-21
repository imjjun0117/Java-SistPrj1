package kr.co.sist.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kr.co.sist.log.LogInEvt;

@SuppressWarnings("serial")
public class  GetData extends GetDataSuper {
	
	
	/**
	 * 인덱스의 범위로 데이터를 리스트에 저장하여 반환한다
	 * 
	 * @param begin
	 * @param end
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> setList(String begin, String end) throws IOException {
		// super();
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader br = null;
		if(LogInEvt.getFile().exists()) {
		try {
			br = new BufferedReader(new FileReader(LogInEvt.getFile()));
			String temp = "";
			int first = 0;
			int last = 0;
			while ((temp = br.readLine()) != null) {
				first = temp.indexOf(begin);
				last = temp.indexOf(end, first);
				data.add(temp.substring(first + 1, last)); // 성공 실패의 값 list에 저장
			} // end while
		} finally {
			if (br != null) {
				br.close();
			} // end if
		} // end finally
		} // end if 
		return data;
	}// end setList

	public ArrayList<String> setList(String begin, String end, int start) throws IOException { // OverLoad
		// super();
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(LogInEvt.getFile()));
			String temp = "";
			int first = 0;
			int last = 0;
			while ((temp = br.readLine()) != null) {
				first = temp.indexOf(begin, start);
				last = temp.indexOf(end, first);
				data.add(temp.substring(first + 1, last)); // 성공 실패의 값 list에 저장
			} // end while
			return data;
		} finally {
			if (br != null) {
				br.close();
			} // end if
		} // end finally
	}// end setList

	/**
	 * 
	 * 키 값의 존재유무 파악 Overload
	 * @param begin
	 * @param end
	 * @param contains
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> setList(String begin, String end, String contains) throws IOException { // OverLoad
		ArrayList<String> data = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(LogInEvt.getFile()));
			String temp = "";
			int first = 0;
			int last = 0;
			while ((temp = br.readLine()) != null) {
				if (temp.contains(contains)) {
					first = temp.indexOf(begin);
					last = temp.indexOf(end, first);
					data.add(temp.substring(first + 1, last)); // 성공 실패의 값 list에 저장
				} else {
					data.add("사용안함");
				}
			} // end while
			return data;
		} finally {
			if (br != null) {
				br.close();
			} // end if
		} // end finally
	}// end setList
	
	/**
	 *	최다 사용 키를 구함 
	 */
	public String printKey() throws IOException { 
		
		List<String> data = setList("=", "&", "key");
		Map<String, Integer> map = new HashMap<>();
		//각 항목별로 얼마나 호출되었는지
		for (int i = 0; i < data.size(); i++) {

			String key = data.get(i);
			Integer count = map.get(key); // value 할당
			int value = (count == null) ? 1 : ++count; // value가 null이면 1 그렇지않다면 ++
			map.put(key, value); // map 할당

		} // end for

		return "최다사용 키의 이름과 횟수\n"+getMaxKey(map)+" : "+map.get(getMaxKey(map))+"회\n";
	}// getKey

	/**
	 * 브라우저 별 접속 횟수 구하기
	 * 
	 * @throws IOException
	 */
	public String printBrowser() throws IOException {

		List<String> browserList = setList("[", "]", 6);
		HashMap<String, Integer> count = new HashMap<String, Integer>();

		BufferedReader br = null;
		try {
			// 각 항목별로 얼마나 호출되었는지 정리
			for (int i = 0; i < browserList.size(); i++) {

				String key = browserList.get(i);
				Integer cnt = count.get(key); // value 할당
				int value = (cnt == null) ? 1 : ++cnt; // value가 null이면 1 그렇지않다면 ++
				count.put(key, value); // map 할당

			} // end for

			// 출력
			Iterator<String> keys = count.keySet().iterator();
			StringBuilder sb = new StringBuilder();
			sb.append("브라우저 별 사용현황\n");
			while (keys.hasNext()) {
				String key = keys.next();
				float all = ((float) count.get(key) / (float) browserList.size()) * 100; // 비율구하기
				sb.append(key+" - "+count.get(key)+"("+String.format("%.2f", all)+"%)\n");
			} // end while
			return sb.toString();
		} // end if

		finally {
			if (br != null) {
				br.close();
			} // close
		} // end finally

	}// browser

	/**
	 * 가장 많이 접속하는 시간 구하기
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public String printTime() throws IOException {

		HashMap<String, Integer> tmCount = new HashMap<String, Integer>();
		ArrayList<String> data = setList(" ", ":");
		// 각 항목별로 얼마나 호출되었는지 정리
		for (int i = 0; i < data.size(); i++) {

			String key = data.get(i);
			Integer count = tmCount.get(key); // value 할당
			int value = (count == null) ? 1 : ++count; // value가 null이면 1 그렇지않다면 ++
			tmCount.put(key, value); // map 할당

		} // end for
			// 출력
		return "가장 많이 접속하는 시간 : "+getMaxKey(tmCount)+"\n";
	}// time

	/**
	 * 성공, 실패 횟수(200, 404) 구하기
	 * 
	 * @throws IOException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public String printSuccessFail() throws IOException {
		ArrayList<String> data = setList("[", "]");
		int success = 0;
		int fail = 0;
		int num = 0;
		for (int i = 0; i < data.size(); i++) {
			num = Integer.parseInt(data.get(i));
			// 성공 실패 횟수 구하기
			switch (num) {
			case 200:
				success++;
				break;
			case 404:
				fail++;
				break;
			}// end
		}

		// 값 출력
		return "서비스 성공(200) 횟수 : "+success+"번\n서비스 실패(404) 횟수 : "+fail+"번\n";

	}// end sucessFail

	/**
	 * 비정상적 요청(403) 횟수, 비율 구하기
	 * 
	 * @throws IOException
	 * @throws ArithmeticException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public String printAbnormalRequest() throws IOException, ArithmeticException {
		ArrayList<String> data = setList("[", "]");
		int abnormal = 0; // 비정상적 요청횟수
		int allRequest = data.size(); // 모든 요청횟수
		double proportion = 0;
		int num = 0;

		for (int i = 0; i < allRequest; i++) {
			num = Integer.parseInt(data.get(i));
			// 비정상적 요청 횟수 구하기
			switch (num) {
			case 403:
				abnormal++;
				break;
			}// end switch

		} // end for

		// 비율 구하기
		proportion = Math.round((((double) abnormal / allRequest * 1.0) * 10000)) / 100.0;

		// 출력
		return "비정상적인 요청(403)이 발생 횟수 : "+abnormal+"번, 비율 : "+String.format("%.2f",proportion )+"%\n";
	}// end abnormalRequest

	/**
	 * 요청에 대한 에러(500) 횟수, 비율 구하기
	 * 
	 * @throws IOException
	 * @throws ArithmeticException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public String printErrorRequest() throws IOException, ArithmeticException {
		ArrayList<String> data = setList("[", "]");
		int error = 0; // 에러요청 횟수
		int allRequest = data.size(); // 전체 요청 횟수
		double proportion = 0; // 비율

		int num = 0;
		for (int i = 0; i < allRequest; i++) {
			num = Integer.parseInt(data.get(i));
			// 에러요청 횟수 구하기
			switch (num) {
			case 500:
				error++;
				break;
			}// end switch

		} // end for
			// 에러요청 비율 구하기
		proportion = Math.round((((double) error / allRequest * 1.0) * 10000)) / 100.0;

		// 출력
		return "요청에 대한 에러(500)이 발생 횟수 : "+error+"번, 비율 : "+String.format("%.2f", proportion)+"%\n";
		
	}

	/**
	 * 입력하는 줄의 범위가 있을 경우 줄의 범위 내에서 최다 사용 키를 출력한다
	 */
	public String printKey(int firstLine, int lastLine) throws IOException {
		
		Map<String, Integer> map = new HashMap<>();
		ArrayList<String> data = setList("=", "&", "key");
		
		for (int i = firstLine - 1; i < lastLine; i++) {// 매개변수로 지정된 범위 ->나머지는 똑같은 방식
			String key = data.get(i);
			Integer count = map.get(key);
			int value = (count == null) ? 1 : ++count;
			map.put(key, value);
		}
		// 반환

		return "입력되는 라인에 해당하는 정보 출력\n"+getMaxKey(map)+" : "+map.get(getMaxKey(map))+"회\n";
	}// getKey


}//class
