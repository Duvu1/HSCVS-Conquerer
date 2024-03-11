package gui;

import database.ItemVO;
import java.awt.*;		
import java.util.ArrayList;
import javax.swing.*;

public class mainFrame extends JFrame {
	static final int WIDTH = 800;		// 프레임의 너비 상수
	static final int HEIGHT = 600;		// 프레임의 높이 상수
	
	private static JPanel[] panelStack = new JPanel[4];		// 패널 전환을 위한 패널 레퍼런스 저장 스택
	private static int top = 0;			// 스택의 top
	
	public static void push(JPanel panel) {
		// 스택에 push하는 메서드
		System.out.println("Pushed to   " + top + ", now top: " + (top + 1));
		panelStack[top++] = panel;
	}
	public static JPanel pop() {
		// 스택에서 pop하는 메서드
		System.out.println("Popped from " + top + ", now top: " + (top - 1));
		return panelStack[--top];
	}
	
	// 최근 조회 기록을 저장할 ArrayList
	static ArrayList<ItemVO> historyList = new ArrayList<ItemVO>();
		
	// 프레임 생성과 동시에 모든 패널들을 정적으로 생성
	static HomePanel homePanel = new HomePanel();
	static ResultPanel resultPanel = new ResultPanel();
	static InfoPanel infoPanel = new InfoPanel();
	static HistoryPanel historyPanel = new HistoryPanel();
	static BookmarkPanel bookmarkPanel = new BookmarkPanel();
	static UpdatePanel updatePanel = new UpdatePanel();
	
	public mainFrame() {
		setTitle("Convenient Store Dominator");				// 프레임 제목
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 프레임이 닫히면 프로그램 종료	
		CardLayout cards = new CardLayout();	// 프레임 내에서 다수의 패널을 겹쳐서 존재할 수 있게 한다.
		setLayout(cards);
		
		setLocation(100, 100);		// 컴퓨터 화면 내에서 프레임을 띄울 위치
		getRootPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));	// 프레임의 너비와 높이 설정
		pack();
		setResizable(false);		// 프레임 크기 고정
		setVisible(true);			// 프레임 보이기
		
		// 생성한 패널들을 프레임에 부착
		Container c = getContentPane();
		c.add(homePanel);
		
		c.add(resultPanel);
		resultPanel.setVisible(false);
		c.add(infoPanel);
		infoPanel.setVisible(false);
		c.add(historyPanel);
		historyPanel.setVisible(false);
		c.add(bookmarkPanel);
		bookmarkPanel.setVisible(false);
		c.add(updatePanel);
		updatePanel.setVisible(false);
	}
}
