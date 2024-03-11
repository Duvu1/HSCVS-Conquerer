package gui;

import java.awt.Font;
import javax.swing.*;

class MyButton extends JButton {
	/* JButton으로부터 상속 받는, 글씨 설정된 Button 클래스 */
	public MyButton() {
		// 글꼴을 설정
		super();
		
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setFont(new Font("나눔고딕", Font.PLAIN, 10));
	}
	
	public MyButton(String s) {
		// 글꼴과 굵기를 설정
		super(s);
		
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setFont(new Font("나눔고딕", Font.BOLD, 10));
	}
	
	public MyButton(String s, int i) {
		// 정수를 입력받아 크기도 설정
		super(s);
		
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setFont(new Font("나눔고딕", Font.BOLD, i));
	}
}
