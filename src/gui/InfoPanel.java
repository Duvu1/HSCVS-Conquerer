package gui;

import database.ItemVO;	
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

class InfoPanel extends MainPanel {
	/* MainPanel로부터 상속 받는 상품 정보 패널 */
	private ItemVO itemResult;						// 검색 결과를 저장할 객체
	
	static final int INFO_CONTENT_HEIGHT = 30;		// 상품 정보 요소의 높이 상수
	static final int INFO_CONTENT_VARIETY = 8;		// 상품 정보의 종류 수 상수
	static final int FONT_SIZE_LARGE = 25;			// 큰 글씨 크기 상수
	static final int FONT_SIZE_SMALL = 15;			// 작은 글씨 크기 상수
	
	// 상품 정보 리스트, 아래 두 리스트들을 가지고 반복문을 통해 상품 정보 객체를 생성하는 데 쓰인다.
	InfoContentPanel[] infoContentPanelList = new InfoContentPanel[INFO_CONTENT_VARIETY];
	
	// 상품 정보의 종류 리스트
	String[] informationList = {"상품명:", "가격:", "판매지점:", "제조사:",
								"중량/부피:", "개수:", "등록일:", "즐겨찾기:"};
	// 상품 정보의 내용 리스트
	String[] tableList = new String[INFO_CONTENT_VARIETY];

	public InfoPanel() {
		/* 상단 패널 초기화 */
		upperPanel.setLayout(null);
		
		// 현재 패널 위치 라벨 추가
		MyLabel label = new MyLabel("상품 상세", FONT_SIZE_LARGE);
		label.setBounds(0, 0, upperPanel.getWidth(), upperPanel.getHeight());
		upperPanel.add(label);
		
		// 뒤로 가기 버튼 설정 및 추가
		MyButton backButton = new MyButton("뒤로", FONT_SIZE_SMALL);
		backButton.setBounds(0, 0, upperPanel.getHeight() + 10, upperPanel.getHeight());
		backButton.addActionListener(new ButtonListener());
		upperPanel.add(backButton);
		
		
		/* 중단 패널 초기화 */
		centerPanel.setLayout(null);
		
		// 상품 사진을 붙이는 패널
		JPanel photoPanel = new JPanel();
		photoPanel.setLayout(new BorderLayout());
		photoPanel.setBounds(centerPanel.getWidth() / 2 - centerPanel.getHeight() / 4, 0,
				centerPanel.getHeight() / 2, centerPanel.getHeight() / 2);
		photoPanel.setBackground(Color.WHITE);
		photoPanel.setBorder(new LineBorder(Color.BLACK));
		centerPanel.add(photoPanel);
		
		MyLabel imageLabel = new MyLabel("No Image...", FONT_SIZE_SMALL);
		photoPanel.add(imageLabel, BorderLayout.CENTER);
		
		// 상품 정보 요소 생성 및 추가
		for(int i = 0; i < infoContentPanelList.length; i++) {
			infoContentPanelList[i] = new InfoContentPanel(informationList[i]);
			infoContentPanelList[i].setLocation(0,
					centerPanel.getHeight() / 2 + INFO_CONTENT_HEIGHT * i);
			if(i % 2 == 0)
				infoContentPanelList[i].setBackground(Color.LIGHT_GRAY);
			else
				infoContentPanelList[i].setBackground(Color.WHITE);
				
			centerPanel.add(infoContentPanelList[i]);
		}

		
		/* 하단 패널 초기화 */
		lowerPanel.removeAll();		// 부모 클래스인 MainPanel의 "+" 버튼 삭제
		lowerPanel.setLayout(new BorderLayout());
		
		// 현재 보고 있는 상품의 수정 패널로 전환하는 버튼 설정 및 추가("+" 버튼 대체)
		MyButton modifyButton = new MyButton("수정 / 추가", FONT_SIZE_LARGE);
		modifyButton.addActionListener(new ButtonListener());
		lowerPanel.add(modifyButton, BorderLayout.CENTER);	
	}
	
	public void setItemVO(ItemVO itemResult) {
		/* 검색 결과 객체 저장 */
		this.itemResult = itemResult;
	}
	
	public void repaintPanel() {
		/* 상품 정보 패널로 전환할 때마다, 보여지는 상품 정보를 갱신 */
		tableList[0] = itemResult.getIname();
		tableList[1] = itemResult.getIprice();
		tableList[2] = itemResult.getIstore();
		tableList[3] = itemResult.getImanufacturer();
		tableList[4] = itemResult.getIamount();
		tableList[5] = itemResult.getIquantity();
		tableList[6] = itemResult.getIregisterdate();
		tableList[7] = String.valueOf(itemResult.getIsbookmark());
		
		for(int i = 0; i < INFO_CONTENT_VARIETY; i++)
			infoContentPanelList[i].repaintInfoContent(tableList[i]);
	}
	
	@Override
	public void setVisible(boolean b) {
		/* 패널을 보이게 할 때마다 상품 목록을 갱신하는 setVisible 메서드 */
		super.setVisible(b);
		
		if(b == true)
			this.repaintPanel();
	}
	
	class ButtonListener implements ActionListener {
		/* 버튼 이벤트 리스너 */
		@Override
		public void actionPerformed(ActionEvent e) {
			MyButton btn = (MyButton)e.getSource();	// 이벤트가 발생한 버튼 추적
			String option = btn.getText();			// 추적한 버튼의 text 얻기
			
			if(option.equals("뒤로")) {
				// 뒤로 가기 버튼을 눌렀을 때
				InfoPanel.this.setVisible(false);
				mainFrame.pop().setVisible(true);
			}
			else if(option.equals("수정 / 추가")) {
				// 상품 수정 버튼을 눌렀을 때
				InfoPanel.this.setVisible(false);
				
				// 상품 수정 패널에 현재 상품의 정보를 저장
				mainFrame.updatePanel.setItemResult(InfoPanel.this.itemResult);
				
				// 저장한 상품 정보로 하여금 상품 수정 패널 갱신
				mainFrame.updatePanel.repaintPanel(InfoPanel.this.itemResult);
				
				mainFrame.updatePanel.setVisible(true);		// 상품 수정 패널 보이기
				mainFrame.push(mainFrame.infoPanel);
			}
		}
	}
}
