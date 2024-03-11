package gui;

import database.ItemVO;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class ResultContentPanel extends JPanel {
	/* 검색 결과 요소 패널 */
	private ItemVO itemResult;						// 검색 결과를 저장할 객체
	static final int RESULT_CONTENT_HEIGHT = 60;	// 검색 결과 요소의 높이 
	
	public ResultContentPanel(ItemVO itemResult) {
		/* 검색 결과 요소 초기화 */
		this.itemResult = itemResult;
		
		setSize(mainFrame.WIDTH - 20, RESULT_CONTENT_HEIGHT);
		setBackground(Color.WHITE);
		setLayout(null);
		setBorder(new LineBorder(Color.BLACK));
		
		// 상품 이름 패널
		JPanel itemName = new JPanel();
		itemName.setBounds(10, 10, 300, RESULT_CONTENT_HEIGHT - 20);
		itemName.setLayout(new BorderLayout());
		
		MyLabel itemNameLabel = new MyLabel(itemResult.getIname(), 18);
		itemNameLabel.setBounds(0, 0, itemName.getWidth(), itemName.getHeight());
		itemName.add(itemNameLabel, BorderLayout.CENTER);
		
		// 상품 가격 패널
		JPanel itemPrice = new JPanel();
		itemPrice.setBounds(320, 10, 100, RESULT_CONTENT_HEIGHT - 20);
		itemPrice.setLayout(new BorderLayout());

		MyLabel itemPriceLabel = new MyLabel(itemResult.getIprice(), 18);
		itemPriceLabel.setBounds(0, 0, itemPrice.getWidth(), itemPrice.getHeight());
		itemPrice.add(itemPriceLabel, BorderLayout.CENTER);
		
		// 상품 판매 지점 패널
		JPanel itemStore = new JPanel();
		itemStore.setBounds(430, 10, 150, RESULT_CONTENT_HEIGHT - 20);
		itemStore.setLayout(new BorderLayout());

		MyLabel itemStoreLabel = new MyLabel(itemResult.getIstore(), 14);
		itemStoreLabel.setBounds(0, 0, itemStore.getWidth(), itemStore.getHeight());
		itemStore.add(itemStoreLabel, BorderLayout.CENTER);
		
		// 상품 등록일 패널
		JPanel itemRegisterDate = new JPanel();
		itemRegisterDate.setBounds(590, 10, 100, RESULT_CONTENT_HEIGHT - 20);
		itemRegisterDate.setLayout(new BorderLayout());

		MyLabel itemRegisterDateLabel = new MyLabel(itemResult.getIregisterdate(), 14);
		itemRegisterDateLabel.setBounds(0, 0, itemRegisterDate.getWidth(), itemRegisterDate.getHeight());
		itemRegisterDate.add(itemRegisterDateLabel, BorderLayout.CENTER);
		
		// 상품 즐겨찾기 여부 패널
		JPanel itemIsbookmark = new JPanel();
		itemIsbookmark.setBounds(700, 10, RESULT_CONTENT_HEIGHT - 20, RESULT_CONTENT_HEIGHT - 20);
		itemIsbookmark.setLayout(new BorderLayout());
		
		ImageIcon bookmarked = new ImageIcon("images/yellowstar.png");
		Image image = bookmarked.getImage();
		Image fitImage = image.getScaledInstance(RESULT_CONTENT_HEIGHT - 20, RESULT_CONTENT_HEIGHT - 20,
				Image.SCALE_SMOOTH);	// 이미지 크기 조절
		bookmarked.setImage(fitImage);
		
		ImageIcon nonbookmarked = new ImageIcon("images/whitestar.png");
		image = nonbookmarked.getImage();
		fitImage = image.getScaledInstance(RESULT_CONTENT_HEIGHT - 20, RESULT_CONTENT_HEIGHT - 20,
				Image.SCALE_SMOOTH);	// 이미지 크기 조절
		nonbookmarked.setImage(fitImage);
		
		// 즐겨찾기 체크박스
		JCheckBox bookmarkCheck = new JCheckBox(nonbookmarked);
		bookmarkCheck.setHorizontalAlignment(SwingConstants.CENTER);
		bookmarkCheck.setBounds(0, 0, RESULT_CONTENT_HEIGHT - 20, RESULT_CONTENT_HEIGHT - 20);
		bookmarkCheck.setSelectedIcon(bookmarked);
		if(itemResult.getIsbookmark())
			// 즐겨찾기 여부에 따른 아이콘 설정
			bookmarkCheck.setSelected(true);
		else
			bookmarkCheck.setSelected(false);
		itemIsbookmark.add(bookmarkCheck, BorderLayout.CENTER);

		add(itemName);
		add(itemPrice);
		add(itemStore);
		add(itemRegisterDate);
		add(itemIsbookmark);
	}
	
	public ItemVO getItemResult() {
		/* 클래스 멤버로 지니고 있던 검색 결과 객체를 반환 */
		return itemResult;
	}
}
