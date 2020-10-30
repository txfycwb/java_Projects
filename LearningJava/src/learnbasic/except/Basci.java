package learnbasic.except;

import java.io.UnsupportedEncodingException;

public class Basci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aasfdsadj";
		try {
			s.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert s.length() == 0 : "explain what happen";//assert语句JVM默认关闭
		System.out.println("down");
	}

}
