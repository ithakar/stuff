
import java.awt.Color;
import java.awt.Graphics;

import com.boom.global.Global;

public class Block{

	int[][] block = new int[Global.ROW][Global.COL];
	
	public void drawBlock(Graphics g){
		g.setColor(new Color(0xcfcfcf));
		for(int i = 0; i < Global.ROW; i++){
			for( int j = 0; j < Global.COL; j++){
				g.fill3DRect(i*Global.BLOCK, j*Global.BLOCK, Global.BLOCK, Global.BLOCK, true);
			}
		}
	}
}
