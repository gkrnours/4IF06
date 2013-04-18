package life.display;

import java.util.Iterator;

import life.LIFE;
import life.cell.Cellule;
import life.cell.Coord;

public class DisplayTTY implements Display{
	private LIFE data;
	
	public DisplayTTY(LIFE life) {
		data = life;
	}
	public DisplayTTY() {
	}
	
	public static String[] utf8 = 
		{".","#", "▢","▣", "░","▓", "◇","◈", "☐","☒"};
	
	public void show(){
		refresh();
	}
	@Override
	public void view(Coord topLeft, Coord bottomRight) {
	}
	@Override
	public void refresh(){
		int style = 0;
		Iterator<Cellule> raw = data.raw();
		Coord next = (raw.hasNext())?raw.next():null;
		
		for(int y=data.y(), yp=y+data.h(); y<yp; ++y){
			for(int x=data.x(), xp=x+data.w(); x<xp; ++x){
				if(next!=null && next.isAt(x,y)){
					System.out.print(utf8[1+style]);
					next = (raw.hasNext())?raw.next():null;
				} else {
					System.out.print(utf8[0+style]);
				}
			}
			System.out.println();
		}
	}
	public void update(LIFE life) {
		data = life;
	}
}
