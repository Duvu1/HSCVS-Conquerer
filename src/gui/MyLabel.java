package gui;

import java.awt.Font;
import javax.swing.*;

class MyLabel extends JLabel {
	/* JLabel으로부터 상속 받는, 글씨 설정된 Label 클래스 */
	public MyLabel() {
		// 글꼴을 설정
		super();
		
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setFont(new Font("나눔고딕", Font.PLAIN, 10));
	}
	
	public MyLabel(String s) {
		// 글꼴과 굵기를 설정
		super(s);
		
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setFont(new Font("나눔고딕", Font.BOLD, 10));
	}
	
	public MyLabel(String s, int i) {
		// 정수를 입력받아 크기도 설정
		super(s);
		
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setFont(new Font("나눔고딕", Font.BOLD, i));
	}
}
