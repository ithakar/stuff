
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.boom.entity.Block;
import com.boom.entity.Boom;
import com.boom.global.Global;
import com.boom.listener.MyMouseListener;


public class GamePaint extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Block block = new Block();
	private Boom boom = new Boom();
	
	//鼠标取到的坐标值
	private int mouse_x = 0;               
	private int mouse_y = 0;
	//鼠标取到的坐标值
	private int check_x = 0;
	private int check_y = 0;
	
	//要显示的数字
	private int[][] show_num = new int[Global.ROW][Global.COL];
	
	//用来进行循环0个炸弹的数字,将右键标记的数字置为-9
	private int[][] zero = new int[Global.ROW][Global.COL];
	
	public GamePaint(){
		init();
		addMouseListener(new MouseClick(){
			public void mouseClicked(MouseEvent e) {
				
				int mod = e.getModifiers();
				
				//将鼠标取到的值换算成row、column 类型
				 mouse_x = e.getX()/Global.BLOCK;
				 mouse_y = e.getY()/Global.BLOCK;
				 
				 //越界点击无效
				 
				 if (mouse_x >= Global.ROW || mouse_x < 0 || mouse_y >= Global.COL || mouse_y < 0) {
						return;
				 }
				 //life 为true时点击有效 ，已经点击过block的不能再次点击
				 if(boom.isLife() && show_num[mouse_y][mouse_x] == -1){
					 
					 if(mod == InputEvent.BUTTON1_MASK){
						 clicked(); 
					 }else if(mod == InputEvent.BUTTON3_MASK){
						 setFlag(mouse_x, mouse_y);
					 }
				 }
				 repaint();
			 }
		});
	}
	
	private void setFlag(int x, int y){
		if(show_num[y][x] != -1){
			return;
		}
		zero[y][x] = -9;
	}
	
	private void init(){
		for(int row = 0; row < Global.ROW; row++){
			for( int column = 0; column < Global.COL; column++){
				show_num[row][column] = -1 ;
				zero[row][column] = boom.num[row][column];
			}
		}
	}
	
	public void clicked(){
		String s = " x:" + mouse_x + " y:" + mouse_y;
		System.out.print(s);
		System.out.println(" value is:" + boom.num[mouse_y][mouse_x]);
		
		//将点击后的值都存入到show_num   显示时只显示show_num数组
//		show_num[mouse_y][mouse_x] = boom.num[mouse_y][mouse_x];
		setNumToShow(show_num, boom.num, mouse_x, mouse_y);
		
		//如果等于0       查找旁边的   然后将所有的值都加入到show_num中
		if(boom.num[mouse_y][mouse_x] == 0){
			zero[mouse_y][mouse_x] = 0;
			checkNumber(mouse_x,mouse_y);
		}
		
		if(boom.num[mouse_y][mouse_x] >= 9){
			boom.setLife(false);
			System.out.println("you're died.");
		}
	}
	
	//将被点击的方块的周围8个数字中值小于9的加入到show_num中，如果8个数字中有等于0的，迭代
	private void checkNumber(int x, int y) {
		// TODO Auto-generated method stub
			for(int i = -1; i <= 1; i++){
				for(int j = -1; j <= 1; j++){
					check_x = x + i;
					check_y = y + j;
//					if(check_x < Global.ROW && check_x >= 0 && check_y < Global.COL && check_y >= 0){
//						show_num[check_y][check_x] = boom.num[check_y][check_x];
//						if(zero[check_y][check_x] == 0){
//							zero[check_y][check_x] = -1;
//							checkNumber(check_x, check_y);
//						}
//					}
					//尝试return和break
					if (check_x >= Global.ROW || check_x < 0 || check_y >= Global.COL || check_y < 0) {
						continue;
					}
					
					show_num[check_y][check_x] = boom.num[check_y][check_x];
					if(zero[check_y][check_x] == 0){
						zero[check_y][check_x] = -1;
						checkNumber(check_x, check_y);
					}
				}
			}
	}


	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0,0,Global.BLOCK*Global.ROW,Global.BLOCK*Global.COL);
		
		
		if(block != null && boom != null ){
			block.drawBlock(g);
			boom.drawFlag(g, zero);
//			if (show_num[mouse_y][mouse_x] >= 0 ) {
				boom.showNumber(g, show_num);
//			}
			
			if(!boom.isLife()){                  
				boom.drawBoom(g);
				boom.showNumber(g, boom.num);
			}
		}
	}
	
	//如果boom.num的值小于9 ， 将其值赋给 show_num
	public void setNumToShow(int[][] m, int[][] n, int x, int y){
		if(n[y][x] < 9){
			m[y][x] = n[y][x];
		}
	}
	
	class MouseClick extends MyMouseListener{

	}
}




