
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.snake.entity.Snake;
import com.snake.util.Global;

public class DisplaySnake extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Snake snake = new Snake();
	
	public DisplaySnake(){
		//add(gamePanel);
		add(snake);
		Timer timer = new Timer(500 , new TimerListener());
		timer.start();
	}
	
	public static void main(String[] args){
		DisplaySnake frame = new DisplaySnake();
		frame.setSize(Global.COLUMN*Global.BLOCK, Global.ROW*Global.BLOCK);
		frame.setTitle("贪吃蛇");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
	
	public class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				snake.move();
				snake.repaint();
		}
	}
}
