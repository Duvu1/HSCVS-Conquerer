package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MainPanel extends JPanel {
	// 패널의 기본 틀 클래스
	JPanel upperPanel = new JPanel();	// 패널 상단에 하위 패널 추가
	JPanel centerPanel = new JPanel();	// 패널 중단에 하위 패널 추가
	JPanel lowerPanel = new JPanel();	// 패널 하단에 하위 패널 추가
	
	public MainPanel() {
		setLayout(null);
		
		// 하위 패널들 설정
		upperPanel.setBounds(0, 0, mainFrame.WIDTH, mainFrame.HEIGHT / 10);
		upperPanel.setBackground(Color.WHITE);
		centerPanel.setBounds(0, mainFrame.HEIGHT / 10, mainFrame.WIDTH, mainFrame.HEIGHT / 10 * 8);
		lowerPanel.setBounds(0, mainFrame.HEIGHT / 10 * 9, mainFrame.WIDTH, mainFrame.HEIGHT / 10);
		lowerPanel.setBackground(Color.WHITE);

		// 하위 패널들 추가
		add(upperPanel);
		add(centerPanel);
		add(lowerPanel);
	}
}
