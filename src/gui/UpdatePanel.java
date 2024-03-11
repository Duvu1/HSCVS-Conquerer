package gui;

import database.ItemVO;	
import database.ConnectDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

class UpdatePanel extends MainPanel {
	/* MainPanel로부터 상속 받는 상품 수정 패널 */
	
	// 이미 있는 상품일 경우 그 정보를 보이기 위해 저장하는 상품 정보 객체
	private ItemVO itemResult;
	
	static final int UPDATE_CONTENT_HEIGHT = 30;	// 상품 정보 요소의 높이 상수
	static final int UPDATE_CONTENT_VARIETY = 8;	// 상품 정보의 종류 수 상수
	static final int FONT_SIZE_LARGE = 25;			// 큰 글씨 크기 상수
	static final int FONT_SIZE_SMALL = 15;			// 작은 글씨 크기 상수
	
	// 상품 정보 리스트, 아래 두 리스트들을 가지고 반복문을 통해 상품 정보 객체를 생성하는 데 쓰인다.
	UpdateContentPanel[] updateContentPanelList = new UpdateContentPanel[UPDATE_CONTENT_VARIETY];
	
	// 상품 정보의 종류 리스트
	String[] informationList = {"상품명:", "가격:", "판매지점:", "제조사:",
								"중량/부피:", "개수:", "등록일:", "즐겨찾기:"};
	// 상품 정보의 내용 리스트
	String[] tableList = new String[UPDATE_CONTENT_VARIETY];

	public UpdatePanel() {
		/* 상단 패널 초기화 */
		upperPanel.setLayout(null);

		// 현재 패널 위치 라벨 추가
		MyLabel label = new MyLabel("상품 수정 / 추가", FONT_SIZE_LARGE);
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
		for(int i = 0; i < updateContentPanelList.length; i++) {
			updateContentPanelList[i] = new UpdateContentPanel(informationList[i]);
			updateContentPanelList[i].setLocation(0,
					centerPanel.getHeight() / 2 + UPDATE_CONTENT_HEIGHT * i);
			if(i % 2 == 0)
				updateContentPanelList[i].setBackground(Color.LIGHT_GRAY);
			else
				updateContentPanelList[i].setBackground(Color.WHITE);
			
			centerPanel.add(updateContentPanelList[i]);
		}
		

		/* 하단 패널 초기화 */
		lowerPanel.removeAll();		// 부모 클래스인 MainPanel의 "+" 버튼 삭제
		lowerPanel.setLayout(new BorderLayout());
		
		// 현재 보고 있는 상품을 삭제하는 버튼 설정 및 추가
		MyButton deleteButton = new MyButton("삭제", FONT_SIZE_LARGE);
		deleteButton.setPreferredSize(new Dimension(mainFrame.WIDTH / 2, mainFrame.HEIGHT / 10));
		deleteButton.addActionListener(new ButtonListener());
		lowerPanel.add(deleteButton, BorderLayout.WEST);

		// 현재 보고 있는 상품의 변경 사항을 적용하는 버튼 설정 및 추가
		MyButton confirmButton = new MyButton("확인", FONT_SIZE_LARGE);
		confirmButton.setPreferredSize(new Dimension(mainFrame.WIDTH / 2, mainFrame.HEIGHT / 10));
		confirmButton.addActionListener(new ButtonListener());
		lowerPanel.add(confirmButton, BorderLayout.EAST);
	}
	
	public void setItemResult(ItemVO itemResult) {
		/* 검색 결과 객체 저장 */
		this.itemResult = itemResult;
	}
	
	public void repaintPanel(ItemVO itemResult) {
		/* 상품 정보 패널로 전환할 때마다, 보여지는 상품 정보를 갱신 */
		tableList[0] = itemResult.getIname();
		tableList[1] = itemResult.getIprice();
		tableList[2] = itemResult.getIstore();
		tableList[3] = itemResult.getImanufacturer();
		tableList[4] = itemResult.getIamount();
		tableList[5] = itemResult.getIquantity();
		tableList[6] = itemResult.getIregisterdate();
		tableList[7] = String.valueOf(itemResult.getIsbookmark());
		
		for(int i = 0; i < UPDATE_CONTENT_VARIETY; i++)
			updateContentPanelList[i].repaintUpdateContent(tableList[i]);
	}
	
	public void alertAlert() {
		/* 키 값에 공백 문자열 입력을 시도할 경우 반려하는 메서드 */
		JFrame alertFrame = new JFrame();
		alertFrame.setSize(new Dimension(300, 100));
		alertFrame.setLocation(100 + (mainFrame.WIDTH - alertFrame.getWidth()) / 2,
				100 + (mainFrame.HEIGHT - alertFrame.getHeight()) / 2);
		alertFrame.add(new MyLabel("상품명과 판매지점을 반드시 작성해주세요.", 10));
		alertFrame.setVisible(true);
	}
	
	@Override
	public void setVisible(boolean b) {
		/* 패널을 숨길 때마다 모든 텍스트필드를 청소하는 setVisible 메서드 */
		super.setVisible(b);
		
		if(b == false)
			for(int i = 0; i < UPDATE_CONTENT_VARIETY; i++)
				updateContentPanelList[i].repaintUpdateContent("");
	}
	
	class ButtonListener implements ActionListener {
		/* 버튼 이벤트 리스너 */
		@Override
		public void actionPerformed(ActionEvent e) {
			MyButton btn = (MyButton)e.getSource();	// 이벤트가 발생한 버튼 추적
			String option = btn.getText();			// 추적한 버튼의 text 얻기
			
			if(option.equals("뒤로")) {
				// 뒤로 가기 버튼을 눌렀을 때
				UpdatePanel.this.setVisible(false);
				mainFrame.pop().setVisible(true);
			}
			else if(option.equals("확인")) {
				// 확인 버튼을 눌렀을 때
				ConnectDB connection = new ConnectDB();		// DB 연결 객체 생성
				
				// VO 객체에 입력해서 삽입한다.
				ItemVO itemInfo = new ItemVO();
				String filter;
				filter = updateContentPanelList[0].rightTextField.getText();
				if(filter.equals("")) {
					alertAlert();
					return;
				}
				else
					itemInfo.setIname(filter.trim());				// 상품명
				
				itemInfo.setIprice(updateContentPanelList[1].
						rightTextField.getText());					// 가격
				
				filter = updateContentPanelList[2].rightTextField.getText();
				if(filter.equals("")) {
					alertAlert();
					return;
				}
				else
					itemInfo.setIstore(filter.trim());				// 판매지점
				
				itemInfo.setImanufacturer(updateContentPanelList[3].
						rightTextField.getText());					// 제조사
				
				itemInfo.setIamount(updateContentPanelList[4].
						rightTextField.getText());					// 중량/부피
				
				itemInfo.setIquantity(updateContentPanelList[5].
						rightTextField.getText());					// 개수
				
				itemInfo.setIregisterdate(updateContentPanelList[6].
						rightTextField.getText());					// 등록일
				
				itemInfo.setIsbookmark(
						Boolean.parseBoolean(
								updateContentPanelList[7].rightTextField.getText()
								)
						);											// 즐겨찾기
				
				if(connection.getItem(itemInfo.getIname(), itemInfo.getIstore()) != null) {
					// 키가 DB에 존재하면 키를 제외한 나머지 데이터를 수정
					
					if(connection.updateItem(itemInfo) == 0) {
						// 성공하면 상품 추가 패널을 숨기고 갱신된 상품 상세 패널 보이기
						UpdatePanel.this.setVisible(false);
						mainFrame.infoPanel.setItemVO(itemInfo);
						mainFrame.infoPanel.repaintPanel();
						mainFrame.pop().setVisible(true);
					}
				}
				else {
					// 그렇지 않으면 DB에 데이터 삽입
					if(connection.insertItem(itemInfo) == 0) {
						// 성공하면 상품 추가 패널을 숨기기
						UpdatePanel.this.setVisible(false);
						mainFrame.infoPanel.setItemVO(itemInfo);
						mainFrame.infoPanel.repaintPanel();
						mainFrame.pop().setVisible(true);
					}
				}
			}
			else if(option.equals("삭제")) {
				// 삭제 버튼을 눌렀을 때
				ConnectDB connection = new ConnectDB();		// DB 연결 객체 생성
				
				// DB에서 데이터 삭제, key로 상품명과 판매 지점을 넘겨준다.
				connection.deleteItem(updateContentPanelList[0].rightTextField.getText(),
						updateContentPanelList[2].rightTextField.getText());
				
				UpdatePanel.this.setVisible(false);
				
				// 삭제한 상품의 정보를 보일 수는 없으므로 pop을 2회 하고 패널 전환
				mainFrame.pop();
				mainFrame.pop().setVisible(true);
			}
		}
	}
}
