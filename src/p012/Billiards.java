package p012;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Billiards extends JFrame {

	public static int Width = 800;
	public static int Height = 600;

	private JButton b_start, b_stop;

	private Board board;

	private final int N_BALL = 10;
	private Ball[] balls;

	public Billiards() {

		board = new Board();
		board.setForeground(new Color(0, 128, 0));
		board.setBackground(new Color(0, 128, 0));

		initBalls();
		board.setBalls(balls);

		b_start = new JButton("Empezar");
		b_start.addActionListener(new StartListener());
		b_stop = new JButton("Parar");
		b_stop.addActionListener(new StopListener());

		JPanel p_Botton = new JPanel();
		p_Botton.setLayout(new FlowLayout());
		p_Botton.add(b_start);
		p_Botton.add(b_stop);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(board, BorderLayout.CENTER);
		getContentPane().add(p_Botton, BorderLayout.PAGE_END);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Width, Height);
		setLocationRelativeTo(null);
		setTitle("Pr�ctica programaci�n concurrente objetos m�viles independientes");
		setResizable(false);
		setVisible(true);
	}

	private void initBalls() {
		balls=new Ball[N_BALL];
		for(int i=0;i<N_BALL;i++){
			balls[i]=new Ball();
		}
	}
	
	protected Thread[] threads;

	protected Thread makeThread (final Ball b){
		Runnable runloop=new Runnable(){
			public void run(){
				try{
					for(;;){
						b.move();
						b.reflect();
						board.paint(getGraphics());
						Thread.sleep(10);
					}
				}catch (InterruptedException e){
					return;
				}
			}
		};
		return new Thread(runloop);
	}
	
	private class StartListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			board.setBalls(balls);
			threads = new Thread[N_BALL];
			for (int i = 0; i < N_BALL ; i++){
				threads[i]= makeThread (balls[i]);
				threads[i].start();
			}
		}
	}

	private class StopListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Code is executed when stop button is pushed
		}
	}

	public static void main(String[] args) {
		new Billiards();
	}
}