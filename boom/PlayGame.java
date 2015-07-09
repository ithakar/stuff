
import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.boom.gamePaint.GamePaint;
import com.boom.global.Global;

public class PlayGame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static void main(String[] args){
//		Block block = new Block();
//		Boom boom = new Boom();
		GamePaint gamePaint= new GamePaint();
		
		PlayGame frame = new PlayGame();
		
		frame.setTitle("BOOM");
		frame.setSize(Global.ROW*Global.BLOCK +Global.BLOCK/2, Global.COL*Global.BLOCK + Global.BLOCK +Global.BLOCK/2);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePaint.setSize(Global.ROW*Global.BLOCK, Global.COL*Global.BLOCK);
		frame.add(gamePaint, BorderLayout.CENTER);
	}
}
