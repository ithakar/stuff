

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.boom.global.Global;

public class Boom {
	
	public int[][] num = new int[Global.ROW][Global.COL];
	
	private boolean life = true;
	
	public Boom(){
		getNumber();
	}
	
	private void init(){
		for(int row = 0; row < Global.ROW; row++){
			for( int column = 0; column < Global.COL; column++){
				num[row][ column] = 0;
			}
		}
	}
	
	private void getBoom(){
		init();
		for(int i = 0; i < Global.BOOM_NUM; i++){
			int row = new Random().nextInt(Global.ROW);
			int col = new Random().nextInt(Global.COL);
			num[row][col] = 9;
		}
	}
	
	private void getNumber() {
		getBoom();
		for (int i = 0; i < Global.ROW; i++) {

			for (int j = 0; j < Global.COL; j++) {
				if (num[i][j] >= 9) {
					addNumber(num, i - 1, j - 1);
					addNumber(num, i - 1, j);
					addNumber(num, i - 1, j + 1);

					addNumber(num, i, j - 1);
					addNumber(num, i, j + 1);

					addNumber(num, i + 1, j - 1);
					addNumber(num, i + 1, j);
					addNumber(num, i + 1, j + 1);
				}
			}
		}
	}

	private void addNumber(int[][] nums, int x, int y) {
		if (x >= Global.ROW || x < 0 || y >= Global.COL || y < 0) {
			return;
		}
		nums[x][y] += 1;
	}
	

	public void drawFlag(Graphics g,int[][] nums){
		g.setColor(Color.ORANGE);
		for(int row = 0; row < Global.ROW; row++){
			for( int column = 0; column < Global.COL; column++){
				if(nums[row][column] == -9){
					g.fill3DRect(column*Global.BLOCK, row*Global.BLOCK, Global.BLOCK, Global.BLOCK, true);
				}
			}
		}
	}
	
	public void drawBoom(Graphics g){
		g.setColor(Color.RED);
		for(int row = 0; row < Global.ROW; row++){
			for( int column = 0; column < Global.COL; column++){
				if(num[row][column] >= 9){
					g.fill3DRect(column*Global.BLOCK, row*Global.BLOCK, Global.BLOCK, Global.BLOCK, true);
				}
			}
		}
	}
	
	//显示数字
	public void showNumber(Graphics g, int[][] nums){
		g.setColor(Color.BLUE);
		for(int row = 0; row < Global.ROW; row++){
			for( int column = 0; column < Global.COL; column++){
				if (nums[row][column] >= 0 && nums[row][column] < 9) {
//					g.draw3DRect(column*Global.BLOCK, row*Global.BLOCK, Global.BLOCK, Global.BLOCK, true);
					g.drawString(String.valueOf(nums[row][column]), column*Global.BLOCK, (row+1)*Global.BLOCK);
				}
			}
		}
	}
	
	//显示数字
//	public void drawNumber(Graphics g, int x, int y){
//		g.setColor(Color.BLUE);
//		for(int row = 0; row < Global.ROW; row++){
//			for( int column = 0; column < Global.COL; column++){
//				if(num[y][x] < 9 && num[y][x] > 9 ){
//					g.drawString(String.valueOf(num[y][]), *Global.BLOCK, (y+1)*Global.BLOCK);
//				}
//			}
//		}
//	}

	public boolean isLife() {
		return life;
	}

	public void setLife(boolean life) {
		this.life = life;
	}
	
	
	
	
}
