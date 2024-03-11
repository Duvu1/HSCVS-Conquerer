package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HomePanel extends MainPanel {
	/* MainPanel으로부터 상속 받는 홈 화면 패널 */
	JTextField searchBar = new JTextField();		// 검색창 컴포넌트
	
	static final int BUTTON_WIDTH = 70;				// 버튼 너비 상수
	static final int BUTTON_HEIGHT = 60;			// 버튼 높이 상수
	static final int BUTTON_FONT_SIZE_SMALL = 15;	// 버튼 작은 글씨 크기 상수
	static final int BUTTON_FONT_SIZE_LARGE = 25;	// 버튼 큰 글씨 크기 상수
	
	public HomePanel() {
		/* 상단 패널 초기화 */
		upperPanel.setLayout(null);
		
		// 검색창 컴포넌트 설정 및 추가
		searchBar.setBounds(BUTTON_WIDTH, 0,
				upperPanel.getWidth() - BUTTON_WIDTH * 2, upperPanel.getHeight());
		searchBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 검색 결과 패널에 검색 결과들을 부착하는 메서드 실행
				mainFrame.resultPanel.showResult(searchBar.getText());
				
				HomePanel.this.setVisible(false);
				mainFrame.resultPanel.setVisible(true);
				mainFrame.push(mainFrame.homePanel);
			}
		});
		upperPanel.add(searchBar);
		
		// 검색창을 동작시키는 버튼 설정 및 추가
		MyButton searchButton = new MyButton("GO", BUTTON_FONT_SIZE_SMALL);
		searchButton.setBounds(upperPanel.getWidth() - BUTTON_WIDTH, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		searchButton.addActionListener(new ButtonListener());
		upperPanel.add(searchButton);
		
		// 프로그램 종료 버튼 설정 및 추가
		MyButton terminateButton = new MyButton("종료", BUTTON_FONT_SIZE_SMALL);
		terminateButton.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		terminateButton.addActionListener(new ButtonListener());
		upperPanel.add(terminateButton);
			
		
		/* 중단 패널 초기화 */
		centerPanel.setLayout(null);
		
		// 최근 기록 패널로 전환하는 버튼 설정 및 추가
		MyButton history = new MyButton("기록", BUTTON_FONT_SIZE_LARGE);
		history.setBounds(0, mainFrame.HEIGHT / 10 * 7,
				mainFrame.WIDTH / 2, mainFrame.HEIGHT / 10);
		history.addActionListener(new ButtonListener());
		centerPanel.add(history);
		
		// 즐겨찾기 패널로 전환하는 버튼 설정 및 추가
		MyButton bookmark = new MyButton("즐겨찾기", BUTTON_FONT_SIZE_LARGE);
		bookmark.setBounds(mainFrame.WIDTH / 2, mainFrame.HEIGHT / 10 * 7,
				mainFrame.WIDTH / 2, mainFrame.HEIGHT / 10);
		bookmark.addActionListener(new ButtonListener());
		centerPanel.add(bookmark);
		
		
		/* 하단 패널 초기화 */
		lowerPanel.setLayout(new BorderLayout());
		
		// 상품 추가 패널로 전환하는 버튼 설정 및 추가
		MyButton addButton = new MyButton("+", BUTTON_FONT_SIZE_LARGE);
		addButton.addActionListener(new ButtonListener());
		lowerPanel.add(addButton, BorderLayout.CENTER);
	}
		
	class ButtonListener implements ActionListener {
		/* 버튼 이벤트 리스너 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();	// 이벤트가 발생한 버튼 추적
			String option = btn.getText();			// 추적한 버튼의 text 얻기
			
			if(option.equals("종료")) {
				// 종료 버튼을 눌렀을 때
				System.exit(0);						// 프로그램 종료
			}
			else if(option.equals("GO")) {
				// GO 버튼(검색 버튼)을 눌렀을 때
				
				// 검색 결과 패널에 검색 결과들을 부착하는 메서드 실행
				mainFrame.resultPanel.showResult(HomePanel.this.searchBar.getText());
				
				HomePanel.this.setVisible(false);			// 홈 화면 패널 숨기기
				mainFrame.resultPanel.setVisible(true);		// 검색 결과 패널 보이기
				mainFrame.push(mainFrame.homePanel);
			}
			
			else if(option.equals("즐겨찾기")) {
				// 즐겨찾기 버튼을 눌렀을 때
				
				mainFrame.bookmarkPanel.showResult("");
				
				HomePanel.this.setVisible(false);
				mainFrame.bookmarkPanel.setVisible(true);	// 즐겨찾기 패널 보이기
				mainFrame.push(mainFrame.homePanel);
			}
			else if(option.equals("기록")) {
				// 최근 기록 버튼을 눌렀을 때
				
				mainFrame.historyPanel.showResult("");
				
				HomePanel.this.setVisible(false);
				mainFrame.historyPanel.setVisible(true);	// 최근 기록 패널 보이기
				
				// 뒤로가기 버튼을 눌렀을 때 홈 화면으로 돌아오도록 스택에 홈 화면 패널 레퍼런스를 push
				mainFrame.push(mainFrame.homePanel); 
			}
			else if(option.equals("+")) {
				// 상품 추가 버튼을 눌렀을 때
				HomePanel.this.setVisible(false);
				mainFrame.updatePanel.setVisible(true);		// 상품 수정 패널 보이기
				mainFrame.push(mainFrame.homePanel);
			}
		}
	}
}
