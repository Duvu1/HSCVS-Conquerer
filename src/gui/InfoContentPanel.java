package gui;

import java.awt.*;
import javax.swing.*;

class InfoContentPanel extends JPanel{
	/* 상품 정보 요소 패널 */
	JLabel leftLabel = new JLabel();			// 상품 정보 종류
	JLabel rightLabel = new JLabel();			// 상품 정보 내용
	static final int INFO_CONTENT_HEIGHT = 30;	// 상품 정보 요소의 높이
	
	public InfoContentPanel(String left) {
		/* 상품 정보 종류만 초기화 */
		setLayout(null);
		setSize(new Dimension(mainFrame.WIDTH, 30));
		
		leftLabel.setText(left);
		leftLabel.setFont(new Font("나눔고딕", Font.BOLD, 15));
		leftLabel.setBounds(getWidth() / 4, 0,
				getWidth() / 4, INFO_CONTENT_HEIGHT);
		
		rightLabel.setFont(new Font("나눔고딕", Font.BOLD, 15));
		rightLabel.setBounds(getWidth() / 2, 0,
				getWidth() / 4, INFO_CONTENT_HEIGHT);
		
		add(leftLabel);
		add(rightLabel);
	}
	
	public void repaintInfoContent(String right) {
		/* 상품 정보 내용 덮어쓰기 */
		rightLabel.setText(right);
	}
}
