
package Game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;


import BattleShip_chat.chat_server;
import Listeners.GameMenuListener;
import Listeners.GameStartListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class GamePanel implements Runnable, ActionListener {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField txtEnter;
	private JButton connectBtn;
	private String serverip;
	private int port;
	private JTextArea textArea_chat;
	private Socket s; 

	private BufferedReader br; 
	private PrintWriter pw; 

	private String str, str1;
	private int boatsize;
	public static Socket AttackSocket;

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GamePanel window = new GamePanel();
					window.frame.setVisible(true);
					AttackSocket = new Socket("localhost", 9999);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.
	public GamePanel() {
		initialize();
	}

	// Initialize the contents of the frame.
	private void initialize() {
		BoardPanel_left bp_left = new BoardPanel_left();
		BoardPanel_right bp_right = new BoardPanel_right();

		frame = new JFrame();
		frame.setBounds(100, 100, 1279, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1265, 683);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(36, 614, 290, 39);
		panel.add(textField);
		textField.setColumns(10);
		
		textField.addActionListener(new ChattingLtnr());

		textArea_chat = new JTextArea();
		textArea_chat.setBounds(43, 86, 290, 403);
		textArea_chat.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea_chat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(36, 77, 290, 510);
		panel.add(scrollPane);

		JPanel bp1_panel = bp_left;
		bp1_panel.setBounds(360, 27, 403, 403);
		panel.add(bp1_panel);

		JPanel bp2_panel = bp_right;
		bp2_panel.setBounds(825, 27, 403, 403);
		panel.add(bp2_panel);

		// 서버 ip 주소
		JLabel lblNewLabel = new JLabel("Server IP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(982, 541, 62, 27);
		panel.add(lblNewLabel);

		// 서버 port
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setBounds(982, 574, 52, 27);
		panel.add(lblPort);

		textField_1 = new JTextField();
		textField_1.setBounds(1066, 541, 136, 23);
		panel.add(textField_1);
		textField_1.setColumns(10);

		txtEnter = new JTextField();
		txtEnter.setText("5454");
		txtEnter.setColumns(10);
		txtEnter.setBounds(1066, 580, 136, 23);
		panel.add(txtEnter);

		connectBtn = new JButton("Connect");
		connectBtn.setForeground(Color.WHITE);
		connectBtn.setBounds(1050, 623, 85, 21);
		panel.add(connectBtn);
		connectBtn.setBackground(Color.DARK_GRAY);
		
		
		connectBtn.addActionListener(this);
	
		ButtonGroup ships = new ButtonGroup();
			
		JRadioButton destroyerBtn = new JRadioButton("Destroyer");
		destroyerBtn.setBounds(361, 581, 85, 21);
		ships.add(destroyerBtn);
		panel.add(destroyerBtn);
		
		destroyerBtn.addActionListener(e -> new ButtonPressed(2, destroyerBtn));

		JRadioButton submarineBtn = new JRadioButton("Submarine");
		submarineBtn.setBounds(461, 581, 93, 21);
		ships.add(submarineBtn);
		panel.add(submarineBtn);
		
		submarineBtn.addActionListener(e -> new ButtonPressed(3,submarineBtn));
		
		JRadioButton cruiserBtn = new JRadioButton("Cruiser");
		cruiserBtn.setBounds(561, 581, 69, 21);
		ships.add(cruiserBtn);
		panel.add(cruiserBtn);
		
		cruiserBtn.addActionListener(e -> new ButtonPressed(3,cruiserBtn));
		
		JRadioButton battleshipBtn = new JRadioButton("Battleship");
		battleshipBtn.setBounds(651, 581, 85, 21);
		ships.add(battleshipBtn);
		panel.add(battleshipBtn);
		
		battleshipBtn.addActionListener(e -> new ButtonPressed(4,battleshipBtn));
		
		JRadioButton aircraftBtn = new JRadioButton("Aircraft carrier");
		aircraftBtn.setBounds(761, 581, 117, 21);
		ships.add(aircraftBtn);
		panel.add(aircraftBtn);
		
		aircraftBtn.addActionListener(e -> new ButtonPressed(5,aircraftBtn));
		
		
		JLabel lblShips = new JLabel("Place Ships");
		lblShips.setHorizontalAlignment(SwingConstants.CENTER);
		lblShips.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblShips.setBounds(361, 526, 110, 27);
		panel.add(lblShips);
		
		JButton verticalBtn = new JButton("Vertical");
		verticalBtn.setBackground(Color.DARK_GRAY);
		verticalBtn.setForeground(Color.WHITE);
		verticalBtn.setBounds(495, 626, 103, 27);
		verticalBtn.setEnabled(false);
		panel.add(verticalBtn);
				
		JButton horizontalBtn = new JButton("Horizontal");
		horizontalBtn.setForeground(Color.WHITE);
		horizontalBtn.setBackground(Color.DARK_GRAY);
		horizontalBtn.setBounds(647, 626, 103, 27);
		panel.add(horizontalBtn);
		
		verticalBtn.addActionListener(new GameMenuListener(true, false,verticalBtn,horizontalBtn));
		horizontalBtn.addActionListener(new GameMenuListener(false, true,verticalBtn, horizontalBtn));
		
		JButton startBtn = new JButton("Game Start");
		startBtn.setForeground(Color.WHITE);
		startBtn.setBackground(Color.DARK_GRAY);
		startBtn.setFont(new Font("Tahoma", Font.BOLD, 13));
		startBtn.setBounds(738, 458, 127, 45);
		panel.add(startBtn);
		
		JLabel lblWelcomeToSkh = new JLabel("Welcome to SKH world");
		lblWelcomeToSkh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblWelcomeToSkh.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToSkh.setBounds(36, 20, 290, 39);
		panel.add(lblWelcomeToSkh);
		
		JLabel lblNewLabel_1 = new JLabel("Your Field");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(461, 440, 186, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblEnemyField = new JLabel("Enemy Field");
		lblEnemyField.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnemyField.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnemyField.setBounds(949, 440, 186, 27);
		panel.add(lblEnemyField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(975, 491, 240, 146);
		panel.add(separator);
		
		JLabel lblChatSetting = new JLabel("Chat Setting");
		lblChatSetting.setHorizontalAlignment(SwingConstants.CENTER);
		lblChatSetting.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChatSetting.setBounds(990, 502, 174, 23);
		panel.add(lblChatSetting);
		
		startBtn.addActionListener(new GameStartListener(this));

	}

	public void run() {
		// 더 이상 입력을 받을 수 없을 때까지 JTextArea(채팅창)에 출력
		try {
			while ((str1 = br.readLine()) != null) {
				textArea_chat.append(str1 + "\n"); // 상대방이 보낸 문자를 채팅창에 세로로 출력
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		serverip = textField_1.getText();
		port =Integer.parseInt(txtEnter.getText());

		// 클라이언트 측 소켓 정보 초기화
		// Socket(host, port), host: 접속 서버 IP 주소, port: 서버 포트 번호
		try {
			s = new Socket(serverip, port);

			// ========== Server와 Stream 연결 ===========
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			// PrintWriter 스트림의 autoFlush 기능 활성화
			pw = new PrintWriter(s.getOutputStream(), true);

		} catch (Exception e2) {
			System.out.println("Connection error..>>>" + e2);
		}

		Thread ct = new Thread(this);
		ct.start();

	}

	class ChattingLtnr implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			str = textField.getText();
			textField.setText("");

			pw.println(str);
			pw.flush();
		}
	}
}

