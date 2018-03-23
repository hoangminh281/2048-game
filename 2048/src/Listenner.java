
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Listenner extends JFrame implements KeyListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 5739205329210859286L;
	private JFrame giaodien;
	private JPanel scorePanel, gamePanel;
	private Sugus[][] sugus;
	private JLabel scoreLabel, timeLabel, recordLabel;
	private Score score;
	private JButton button;

	public Listenner() {
		initGiaodien();
	}

	private void initGiaodien() {
		giaodien = new JFrame("2048");
		giaodien.setSize(500, 600);
		giaodien.setLayout(null);
		giaodien.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		giaodien.setLocationRelativeTo(null);
		giaodien.addKeyListener(this);
		createPanel();
		giaodien.setVisible(true);
		giaodien.requestFocus();
		start();
	}

	private void createPanel() {
		score = new Score();
		// panel diem
		scorePanel = new JPanel();
		scorePanel.setLayout(null);
		scorePanel.setBounds(43, 10, 400, 120);
		scorePanel.setBackground(Color.CYAN);
		// scorePanel.add(new JLabel("2048").setBounds(0,10,100,50));
		setScoreLabel();
		setTimeLabel();
		setRecordLabel();
		setButton();
		// panel game
		gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(4, 4, 5, 5));
		gamePanel.setBounds(43, 140, 400, 400);
		sugus = new Sugus[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				sugus[i][j] = new Sugus();
				gamePanel.add(sugus[i][j].getPanel());
			}
		}
		giaodien.add(scorePanel);
		giaodien.add(gamePanel);
	}

	private void setScoreLabel() {
		scoreLabel = new JLabel(score.toString(), JLabel.CENTER);
		scorePanel.add(scoreLabel);
	}

	private void setTimeLabel() {
		timeLabel = new JLabel("", JLabel.CENTER);
		scorePanel.add(timeLabel);
		Timer time = new Timer();
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

	private void setRecordLabel() {
		recordLabel = new JLabel("BEST: " + score.getRecordOb().getRecord(), JLabel.CENTER);
		scorePanel.add(recordLabel);
	}

	private void setButton() {
		button = new JButton("NEW GAME");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				if (ae.getActionCommand().equals("NEW GAME")) {
					start();
				}
			}
		});
		scorePanel.add(button);
	}

	private void setPrintScore(int i) {
		score.setScore(i);
		scoreLabel.setText(score.toString());
	}

	private void start() {
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

	/*
	 * private void debug() { for (Sugus[] a : sugus) { for (Sugus b : a) {
	 * System.out.print(b.getNum() + "-" + b.isStatus() + " "); }
	 * System.out.println(); } System.out.println("Diem: " + score.getScore() +
	 * "-" + "Diem cao: " + score.getRecordOb().getRecord()); }
	 */
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

	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		boolean lock = true;
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			lock = upAction();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			lock = downAction();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			lock = leftAction();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			lock = rightAction();
		}
		score.updateScore(sugus);
		if (!lock) {
			reRoll();
		}
		if (test()) {
			score.updateRecord(score);
			giaodien.setVisible(false);
		}
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
					} else {
						continue;
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
					} else {
						continue;
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
					} else {
						continue;
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
					} else {
						continue;
					}
				}
			}
		}
		return lock;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
