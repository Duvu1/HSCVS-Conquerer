package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gui.BookmarkPanel.ButtonListener;
import gui.ResultPanel.InfoListener;

class HistoryPanel extends ResultPanel {
	/* ResultPanel로부터 상속 받은 최근 조회 기록 패널 */
	static final int BUTTON_WIDTH = 70;				// 버튼 너비 상수
	static final int BUTTON_HEIGHT = 60;			// 버튼 높이 상수
	static final int FONT_SIZE_LARGE = 25;			// 큰 글씨 크기 상수
	static final int FONT_SIZE_SMALL = 15;			// 작은 글씨 크기 상수
	
	public HistoryPanel(){
		// ResultPanel의 생성자 호출
		super();

		upperPanel.removeAll();
		
		// 현재 패널 위치 라벨 추가
		MyLabel label = new MyLabel("최근 조회 기록", FONT_SIZE_LARGE);
		label.setBounds(0, 0, upperPanel.getWidth(), upperPanel.getHeight());
		upperPanel.add(label, BorderLayout.CENTER);
		
		// 뒤로 가기 버튼 설정 및 추가
		MyButton backButton = new MyButton("뒤로", FONT_SIZE_SMALL);
		backButton.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		backButton.addActionListener(new ButtonListener());
		upperPanel.add(backButton);
		
		// 상품 추가 버튼 설정 및 추가
		MyButton addButton = new MyButton("+", BUTTON_FONT_SIZE_LARGE);
		addButton.addActionListener(new ButtonListener());
		lowerPanel.add(addButton, BorderLayout.CENTER);
	}
	
	@Override
	public void showResult(String keyword) {
		/* 최근 조회한 상품들을 불러오는 메서드 */
		
		// 스크롤 컴포넌트 내부에 부착하는 결과 패널 설정 및 추가
		JPanel insideScrollPanel = new JPanel();
		insideScrollPanel.setLayout(null);
		insideScrollPanel.setPreferredSize(
				// 결과 패널 높이를 (상품 요소의 높이 x 10)로 설정
				new Dimension(mainFrame.WIDTH, RESULT_CONTENT_HEIGHT * 10)
				);		
		
		for(int i = 0; i < 10 && i < mainFrame.historyList.size(); i++) {
			/* 결과 패널에 실제 요소들을 추가하는 반복문 */
			mainFrame.historyList.get(i);
			ResultContentPanel resultContentPanel = new ResultContentPanel(mainFrame.historyList.get(i));
			resultContentPanel.setLocation(0, RESULT_CONTENT_HEIGHT * i);
			
			// 결과 요소를 클릭해 상품 정보 패널로 이동할 수 있도록 이벤트 리스너 추가
			resultContentPanel.addMouseListener(new InfoListener());
			insideScrollPanel.add(resultContentPanel);
		}
		scrollPane.setViewportView(insideScrollPanel);
	}
	
	class ButtonListener implements ActionListener {
		/* 버튼 이벤트 리스너 */
		@Override
		public void actionPerformed(ActionEvent e) {
			MyButton btn = (MyButton)e.getSource();	// 이벤트가 발생한 버튼 추적
			String option = btn.getText();			// 추적한 버튼의 text 얻기
			
			if(option.equals("뒤로")) {
				// 뒤로 가기 버튼을 눌렀을 때
				HistoryPanel.this.setVisible(false);
				
				// 스택에서 이전 패널의 레퍼런스를 pop해 그 패널을 보인다.
				mainFrame.pop().setVisible(true);
			}
			else if(option.equals("+")) {
				// 상품 추가 버튼을 눌렀을 때
				HistoryPanel.this.setVisible(false);
				mainFrame.updatePanel.setVisible(true);		// 상품 수정 패널 보이기
				mainFrame.push(mainFrame.historyPanel);
			}
		}
	}
	
	class InfoListener extends MouseAdapter {
		/* 결과 요소를 클릭했을 때의 이벤트 리스너 */
		@Override
		public void mouseClicked(MouseEvent e) {
			// 마우스 클릭 이벤트를 다루는 메서드만 이용
			
			// 이벤트가 발생한 결과 요소 추적
			ResultContentPanel resultContentPanel = (ResultContentPanel)e.getComponent();
			
			// 클릭은 조회를 의미하므로 최근 조회 리스트에 검색 결과 객체를 삽입
			mainFrame.historyList.add(0, resultContentPanel.getItemResult());
			
			// 이벤트가 발생한 결과 요소에 세팅된 상품 데이터 객체를 얻어 상품 상세 패널 설정
			mainFrame.infoPanel.setItemVO(resultContentPanel.getItemResult());
			mainFrame.infoPanel.repaintPanel();
			
			HistoryPanel.this.setVisible(false);				// 최근 조회 패널 숨기기
			mainFrame.infoPanel.setVisible(true);				// 상품 정보 패널 보이기
			mainFrame.push(mainFrame.historyPanel);
		}
	}
}
