
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Display extends javax.swing.JFrame implements KeyListener {

	private static final long serialVersionUID = -4683048587441148233L;

	public Display() {
		initComponents();
	}

	private void initComponents() {
		Panel = new javax.swing.JPanel();
		tile = new javax.swing.JLabel();
		scorePanel = new javax.swing.JPanel();
		scoreLabel = new javax.swing.JLabel();
		scoreOut = new javax.swing.JLabel();
		bestPanel = new javax.swing.JPanel();
		bestLabel = new javax.swing.JLabel();
		bestOut = new javax.swing.JLabel();
		newgame = new javax.swing.JButton();
		timeLabel = new javax.swing.JLabel();
		gamePanel = new javax.swing.JPanel();
		score = new Score();
		sugus = new Sugus[4][4];

		setTitle("2048");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(253, 242, 230));
		setBounds(new java.awt.Rectangle(450, 50, 400, 400));
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		addKeyListener(this);
		setFocusable(true);

		tile.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 65)); // NOI18N
		tile.setForeground(new java.awt.Color(51, 51, 51));
		tile.setText("2048");

		scorePanel.setBackground(new java.awt.Color(181, 159, 159));

		scoreLabel.setBackground(new java.awt.Color(255, 255, 255));
		scoreLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
		scoreLabel.setForeground(new java.awt.Color(255, 255, 255));
		scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		scoreLabel.setText("SCORE");

		scoreOut.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 30)); // NOI18N
		scoreOut.setForeground(new java.awt.Color(255, 255, 255));
		scoreOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		javax.swing.GroupLayout scorePanelLayout = new javax.swing.GroupLayout(scorePanel);
		scorePanel.setLayout(scorePanelLayout);
		scorePanelLayout
				.setHorizontalGroup(scorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(scoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scoreOut, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE));
		scorePanelLayout
				.setVerticalGroup(scorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(scorePanelLayout.createSequentialGroup()
								.addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(scoreOut, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)));

		bestPanel.setBackground(new java.awt.Color(178, 157, 157));

		bestLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
		bestLabel.setForeground(new java.awt.Color(255, 255, 255));
		bestLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		bestLabel.setText("BEST");

		bestOut.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 30)); // NOI18N
		bestOut.setForeground(new java.awt.Color(255, 255, 255));
		bestOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		bestOut.setText(String.valueOf(score.getRecordOb().getRecord()));

		javax.swing.GroupLayout bestPanelLayout = new javax.swing.GroupLayout(bestPanel);
		bestPanel.setLayout(bestPanelLayout);
		bestPanelLayout
				.setHorizontalGroup(bestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(bestLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(bestOut, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE));
		bestPanelLayout.setVerticalGroup(bestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(bestPanelLayout.createSequentialGroup()
						.addComponent(bestLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(bestOut,
								javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)));

		newgame.setBackground(new java.awt.Color(79, 111, 100));
		newgame.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
		newgame.setForeground(new java.awt.Color(255, 255, 255));
		newgame.setText("New Game");
		newgame.setBorder(null);
		newgame.addActionListener(this::newgameActionPerformed);

		timeLabel.setBackground(new java.awt.Color(86, 24, 24));
		timeLabel.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
		timeLabel.setForeground(new java.awt.Color(51, 25, 20));
		timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
		Panel.setLayout(PanelLayout);
		PanelLayout.setHorizontalGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(PanelLayout.createSequentialGroup()
						.addComponent(tile, javax.swing.GroupLayout.PREFERRED_SIZE, 156,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(scorePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(bestPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(newgame, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
		PanelLayout.setVerticalGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(PanelLayout
						.createSequentialGroup().addGroup(
								PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(scorePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(bestPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(timeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
								.addComponent(newgame, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(tile,
								javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(28, 28, 28)));
		gamePanel.setForeground(new java.awt.Color(240, 227, 199));
		gamePanel.setPreferredSize(new java.awt.Dimension(400, 400));

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(gamePanel);
		gamePanel.setLayout(jPanel5Layout);
		gamePanel.setLayout(new GridLayout(4, 4, 5, 5));
		gamePanel.setBounds(43, 140, 400, 400);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				sugus[i][j] = new Sugus();
				gamePanel.add(sugus[i][j].getPanel());
			}
		}
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
		start();
	}

	private void debug() {
		for (Sugus[] a : sugus) {
			for (Sugus b : a) {
				System.out.print(b.getNum() + "-" + b.isStatus() + " ");
			}
			System.out.println();
		}
		System.out.println("Diem: " + score.getScore() + "-" + "Diem cao: " + score.getRecordOb().getRecord());
	}

	private void start() {
		requestFocus();
		Random rd = new Random();
		int row, col;
		row = rd.nextInt(4);
		col = rd.nextInt(4);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				sugus[i][j].setImage(0);
			}
		}
		sugus[row][col].numberRandoms();
		int i, j;
		do {
			i = rd.nextInt(4);
			j = rd.nextInt(4);
		} while (i == row && j == col);
		sugus[i][j].numberRandoms();
		setPrintScore((int) Math.pow(2, sugus[row][col].getNum()) + (int) Math.pow(2, sugus[i][j].getNum()));
		setTimeLabel();
	}

	private void setPrintScore(int i) {
		score.setScore(i);
		scoreOut.setText(String.valueOf(score.getScore()));
	}

	private void setTimeLabel() {
		time = new Timer();
		time.schedule(new TimerTask() {
			int s = 0, m = 0;

			@Override
			public void run() {
				timeLabel.setText(String.format("%02d", m) + ":" + String.format("%02d", s));
				score.getTime().setSecond(s++);
				if (s == 60) {
					score.getTime().setMinute(++m);
					s = 0;
				}
			}
		}, 0, 1000);
	}

	private void newgameActionPerformed(java.awt.event.ActionEvent evt) {
		if (evt.getActionCommand().equals("New Game")) {
			time.cancel();
			start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		boolean lock = true;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			lock = upAction();
			break;
		case KeyEvent.VK_DOWN:
			lock = downAction();
			break;
		case KeyEvent.VK_LEFT:
			lock = leftAction();
			break;
		case KeyEvent.VK_RIGHT:
			lock = rightAction();
			break;
		default:
			break;
		}
		score.updateScore(sugus);
		if (!lock) {
			reRoll();
		}
		if (test()) {
			score.updateRecord(score);
			//gamePanel.setVisible(false);
			time.cancel();
			//Game Over
		}
	}

	private void reRoll() {
		Random rd = new Random();
		int row, col;
		do {
			row = rd.nextInt(4);
			col = rd.nextInt(4);
		} while (sugus[row][col].isStatus());
		sugus[row][col].numberRandoms();
		setPrintScore(score.getScore() + (int) Math.pow(2, sugus[row][col].getNum()));
	}

	private boolean test() {
		boolean lose = true;
		for (int col = 0; col < 4; col++) {
			for (int i = 0; i < 3; i++) {
				if (!sugus[i][col].isStatus()) {
					lose = false;
					break;
				}
				if (!sugus[i + 1][col].isStatus()) {
					lose = false;
					break;
				}
				if (sugus[i][col].getNum() == sugus[i + 1][col].getNum()) {
					lose = false;
					break;
				}
			}
		}
		for (int row = 0; row < 4; row++) {
			for (int i = 0; i < 3; i++) {
				if (!sugus[row][i].isStatus()) {
					lose = false;
					break;
				}
				if (!sugus[row][i + 1].isStatus()) {
					lose = false;
					break;
				}
				if (sugus[row][i].getNum() == sugus[row][i + 1].getNum()) {
					lose = false;
					break;
				}
			}
		}
		return lose;
	}

	private boolean upAction() {
		boolean lock = true;
		for (int col = 0; col < 4; col++) {
			for (int i = 0; i < 3; i++) {
				if (!sugus[i][col].isStatus()) {
					continue;
				}
				for (int j = i + 1; j < 4; j++) {
					if (!sugus[j][col].isStatus()) {
						continue;
					}
					if (sugus[i][col].getNum() == sugus[j][col].getNum()) {
						sugus[i][col].setImage(sugus[i][col].getNum() + 1);
						sugus[j][col].setImage(0);
						lock = false;
						break;
					} else {
						break;
					}
				}
			}
			for (int row = 0; row < 3; row++) {
				if (sugus[row][col].isStatus()) {
					continue;
				}
				for (int i = row + 1; i < 4; i++) {
					if (sugus[i][col].isStatus()) {
						sugus[row][col].setImage(sugus[i][col].getNum());
						sugus[i][col].setImage(0);
						lock = false;
						break;
					}
				}
			}
		}
		return lock;
	}

	private boolean downAction() {
		boolean lock = true;
		for (int col = 0; col < 4; col++) {
			for (int i = 3; i > 0; i--) {
				if (!sugus[i][col].isStatus()) {
					continue;
				}
				for (int j = i - 1; j >= 0; j--) {
					if (!sugus[j][col].isStatus()) {
						continue;
					}
					if (sugus[i][col].getNum() == sugus[j][col].getNum()) {
						sugus[i][col].setImage(sugus[i][col].getNum() + 1);
						sugus[j][col].setImage(0);
						lock = false;
						break;
					} else {
						break;
					}
				}
			}
			for (int row = 3; row >= 1; row--) {
				if (sugus[row][col].isStatus()) {
					continue;
				}
				for (int i = row - 1; i >= 0; i--) {
					if (sugus[i][col].isStatus()) {
						sugus[row][col].setImage(sugus[i][col].getNum());
						sugus[i][col].setImage(0);
						lock = false;
						break;
					}
				}
			}
		}
		return lock;
	}

	private boolean leftAction() {
		boolean lock = true;
		for (int row = 0; row < 4; row++) {
			for (int i = 0; i < 3; i++) {
				if (!sugus[row][i].isStatus()) {
					continue;
				}
				for (int j = i + 1; j < 4; j++) {
					if (!sugus[row][j].isStatus()) {
						continue;
					}
					if (sugus[row][i].getNum() == sugus[row][j].getNum()) {
						sugus[row][i].setImage(sugus[row][i].getNum() + 1);
						sugus[row][j].setImage(0);
						lock = false;
						break;
					} else {
						break;
					}
				}
			}
			for (int col = 0; col < 3; col++) {
				if (sugus[row][col].isStatus()) {
					continue;
				}
				for (int i = col + 1; i < 4; i++) {
					if (sugus[row][i].isStatus()) {
						sugus[row][col].setImage(sugus[row][i].getNum());
						sugus[row][i].setImage(0);
						lock = false;
						break;
					}
				}
			}
		}
		return lock;
	}

	private boolean rightAction() {
		boolean lock = true;
		for (int row = 0; row < 4; row++) {
			for (int i = 3; i > 0; i--) {
				if (!sugus[row][i].isStatus()) {
					continue;
				}
				for (int j = i - 1; j >= 0; j--) {
					if (!sugus[row][j].isStatus()) {
						continue;
					}
					if (sugus[row][i].getNum() == sugus[row][j].getNum()) {
						sugus[row][i].setImage(sugus[row][i].getNum() + 1);
						sugus[row][j].setImage(0);
						lock = false;
						break;
					} else {
						break;
					}
				}
			}
			for (int col = 3; col >= 1; col--) {
				if (sugus[row][col].isStatus()) {
					continue;
				}
				for (int i = col - 1; i >= 0; i--) {
					if (sugus[row][i].isStatus()) {
						sugus[row][col].setImage(sugus[row][i].getNum());
						sugus[row][i].setImage(0);
						lock = false;
						break;
					}
				}
			}
		}
		return lock;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(() -> {
			new Display().setVisible(true);
		});
	}

	private javax.swing.JPanel Panel;
	private javax.swing.JLabel bestLabel;
	private javax.swing.JLabel bestOut;
	private javax.swing.JPanel bestPanel;
	private javax.swing.JPanel gamePanel;
	private javax.swing.JButton newgame;
	private javax.swing.JLabel scoreLabel;
	private javax.swing.JLabel scoreOut;
	private javax.swing.JPanel scorePanel;
	private javax.swing.JLabel tile;
	private javax.swing.JLabel timeLabel;
	private Sugus[][] sugus;
	private Score score;
	private Timer time;
}
