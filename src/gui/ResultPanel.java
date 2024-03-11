package gui;

import database.ItemVO;	
import database.ConnectDB;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class ResultPanel extends MainPanel {
	/* MainPanel로부터 상속 받는 검색 결과 패널 */	
	JTextField searchBar = new JTextField();		// 검색창 컴포넌트
	JScrollPane scrollPane = new JScrollPane();		// 결과들을 부착할 스크롤 컴포넌트
	
	static final int BUTTON_WIDTH = 70;				// 버튼 너비 상수
	static final int BUTTON_HEIGHT = 60;			// 버튼 높이 상수
	static final int BUTTON_FONT_SIZE_SMALL = 15;	// 버튼 작은 글씨 크기 상수
	static final int BUTTON_FONT_SIZE_LARGE = 25;	// 버튼 큰 글씨 크기 상수
	static final int RESULT_CONTENT_FONT_SIZE_LARGE = 12;	// 검색 결과 글씨 크기 상수
	static final int RESULT_CONTENT_HEIGHT = 60;	// 검색 결과 요소들의 높이 
	
	public ResultPanel() {
		/* 상단 패널 초기화 */
		upperPanel.setLayout(null);
		
		// 검색창 컴포넌트 설정 및 추가
		searchBar.setBounds(BUTTON_WIDTH, 0,
				upperPanel.getWidth() - BUTTON_WIDTH * 2, upperPanel.getHeight());
		searchBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.resultPanel.showResult(searchBar.getText());
				ResultPanel.this.setVisible(false);
				mainFrame.resultPanel.setVisible(true);
			}
		});
		upperPanel.add(searchBar, BorderLayout.CENTER);
		
		// 검색창을 동작시키는 버튼 설정 및 추가
		MyButton searchButton = new MyButton("GO", BUTTON_FONT_SIZE_SMALL);
		searchButton.setBounds(upperPanel.getWidth() - BUTTON_WIDTH, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		searchButton.addActionListener(new ButtonListener());
		upperPanel.add(searchButton);
		
		// 뒤로 가기 버튼 설정 및 추가
		MyButton backButton = new MyButton("뒤로", BUTTON_FONT_SIZE_SMALL);
		backButton.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		backButton.addActionListener(new ButtonListener());
		upperPanel.add(backButton);
		
		
		/* 중단 패널 초기화 */
		centerPanel.setLayout(null);
		
		// 상품 정보 종류 명시 패널 추가
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(null);
		labelPanel.setBounds(0, 0, centerPanel.getWidth(), 30);
		
		MyLabel[] labelList = new MyLabel[5];
		String[] labelContent = { "상품명", "가격", "판매 지점", "등록일", "★" };
		
		for(int i = 0; i < labelList.length; i++) {
			labelList[i] = new MyLabel(labelContent[i], RESULT_CONTENT_FONT_SIZE_LARGE);
			labelPanel.add(labelList[i]);
		}
		
		labelList[0].setBounds(0, 0, 300, 30);
		labelList[1].setBounds(320, 0, 100, 30);
		labelList[2].setBounds(430, 0, 150, 30);
		labelList[3].setBounds(590, 0, 100, 30);
		labelList[4].setBounds(705, 0, 30, 30);
		
		centerPanel.add(labelPanel);
		
		// 스크롤 컴포넌트 설정 및 추가
		scrollPane.setBounds(0, 30, centerPanel.getWidth(), centerPanel.getHeight() - 30);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);		// 스크롤 속도 조정
		centerPanel.add(scrollPane);
		
		
		/* 하단 패널 초기화 */
		lowerPanel.setLayout(new BorderLayout());
		
		// 상품 추가 버튼 설정 및 추가
		MyButton addButton = new MyButton("+", BUTTON_FONT_SIZE_LARGE);
		addButton.addActionListener(new ButtonListener());
		lowerPanel.add(addButton, BorderLayout.CENTER);
	}
	
	public void showResult(String keyword) {
		/* 검색 결과들을 DB로부터 불러오는 메서드 */
		ConnectDB connection = new ConnectDB();		// DB 연결 객체 생성
		ArrayList<ItemVO> rsList;					// 검색 결과를 반환받을 ArrayList
		
		if(keyword.equals(""))
			// 검색 키워드가 없으면, select *
			rsList = connection.exploreItem(keyword);
		else
			// 검색 키워드를 넘겨주고 메서드 실행
			rsList = connection.searchItem(keyword);
		
		// 스크롤 컴포넌트 내부에 부착하는 결과 패널 설정 및 추가
		JPanel insideScrollPanel = new JPanel();
		insideScrollPanel.setLayout(null);
		insideScrollPanel.setPreferredSize(
				// 결과 패널 높이를 (검색 결과의 개수 x 결과 요소의 높이)로 설정
				new Dimension(mainFrame.WIDTH, RESULT_CONTENT_HEIGHT * rsList.size())
				);		
		
		for(int i = 0; i < rsList.size(); i++) {
			/* 결과 패널에 실제 결과 요소들을 추가하는 반복문 */
			
			// 결과 요소를 생성하며 검색 결과 반환값을 인수로 넘겨주면,
			// 그 반환값으로 하여금 결과 요소가 초기화된다.
			ResultContentPanel resultContentPanel = new ResultContentPanel(rsList.get(i));
			resultContentPanel.setLocation(0, RESULT_CONTENT_HEIGHT * i);
			
			// 결과 요소를 클릭해 상품 정보 패널로 이동할 수 있도록 이벤트 리스너 추가
			resultContentPanel.addMouseListener(new InfoListener());
			insideScrollPanel.add(resultContentPanel);
		}
		scrollPane.setViewportView(insideScrollPanel);
	}
	
	@Override
	public void setVisible(boolean b) {
		/* ResultPanel을 상속 받는 클래스의 특별한 setVisible 메서드 */
		super.setVisible(b);
		
		if(b == true)
			/* 패널을 보이게 할 때마다 상품 목록을 갱신
			   ResultPanel을 상속 받는 클래스는 상품 목록을 보여준다는 공통점이 있으므로
			   DB에 접근해 작업한 후 상품 목록 패널로 돌아오면 자동으로 갱신한다. */
			this.showResult(this.searchBar.getText());
	}
	
	class ButtonListener implements ActionListener {
		/* 버튼 이벤트 리스너 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();	// 이벤트가 발생한 버튼 추적
			String option = btn.getText();			// 추적한 버튼의 text 얻기
			
			if(option.equals("뒤로")) {
				// 뒤로 가기 버튼을 눌렀을 때
				ResultPanel.this.setVisible(false);
				
				// 스택에서 이전 패널의 레퍼런스를 pop해 그 패널을 보인다.
				mainFrame.pop().setVisible(true);
			}
			else if(option.equals("GO")) {
				// GO 버튼(검색 버튼)을 눌렀을 때, 검색 결과 패널을 갱신만 한다.
				ResultPanel.this.showResult(ResultPanel.this.searchBar.getText());
			}
			else if(option.equals("+")) {
				// 상품 추가 버튼을 눌렀을 때
				ResultPanel.this.setVisible(false);
				mainFrame.updatePanel.setVisible(true);		// 상품 수정 패널 보이기
				mainFrame.push(mainFrame.resultPanel);
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
			
			// 이벤트가 발생한 결과 요소에 세팅된 검색 결과 객체를 얻어 상품 상세 패널 설정
			mainFrame.infoPanel.setItemVO(resultContentPanel.getItemResult());
			mainFrame.infoPanel.repaintPanel();
			
			ResultPanel.this.setVisible(false);				// 검색 결과 패널 숨기기
			mainFrame.infoPanel.setVisible(true);			// 상품 정보 패널 보이기
			mainFrame.push(mainFrame.resultPanel);
		}
	}
}
