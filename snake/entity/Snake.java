
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JPanel;
import com.snake.util.Global;

public class Snake extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LinkedList<Point> body = new LinkedList<Point>();

	public Snake(){
		int x = Global.COLUMN/2;
		int y = Global.ROW/2;
		for(int i=0; i<3; i++){
			body.addLast(new Point(x-- , y));
		}
	}
	
//	public void init(){
//		int x = Global.COLUMN/2;
//		int y = Global.ROW/2;
//		for(int i=0; i<3; i++){
//			body.addLast(new Point(x-- , y));
//		}
//	}
	
	public Point getBody(int index){
		return body.get(index);
	}
	
	public int getLength(){
		return body.size();
	}
	
	public void move(){
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		x++;
		if(x*Global.BLOCK > getWidth()){
			x = 0;
		}
		Point newhaed = new Point(x , y);
		body.addFirst(newhaed);
		body.removeLast();
	}
	
	public void paint(Graphics  g){
		
		super.paintComponents(g);
		g.setColor(Color.BLUE);
		for(Point p : body){
			g.fill3DRect(p.x*Global.BLOCK, p.y*Global.BLOCK, Global.BLOCK, Global.BLOCK,true);
		}
	}
}
