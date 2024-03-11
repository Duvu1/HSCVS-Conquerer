package gui;

import java.awt.*;
import javax.swing.*;

class UpdateContentPanel extends JPanel{
	/* 상품 수정 요소 패널 */
	JLabel leftLabel = new JLabel();				// 상품 정보 종류
	JTextField rightTextField = new JTextField();	// 상품 정보 내용 입력창
	static final int UPDATE_CONTENT_HEIGHT = 30;	// 상품 정보 요소의 높이
	
	public UpdateContentPanel(String left) {
		/* 상품 정보 종류만 초기화 */
		setLayout(null);
		setSize(new Dimension(mainFrame.WIDTH, 30));
		
		leftLabel.setText(left);
		leftLabel.setFont(new Font("나눔고딕", Font.BOLD, 15));
		leftLabel.setBounds(getWidth() / 4,
				0, getWidth() / 4, UPDATE_CONTENT_HEIGHT);
		rightTextField.setBounds(getWidth() / 2,
				0, getWidth() / 4, UPDATE_CONTENT_HEIGHT);
		
		add(leftLabel);
		add(rightTextField);
	}
	
	public void repaintUpdateContent(String right) {
		/* 상품 정보 내용 덮어쓰기 */
		rightTextField.setText(right);
	}
}
