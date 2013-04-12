package life.display;

import java.util.Iterator;

import life.LIFE;
import life.cell.Cellule;
import life.cell.Coord;

public class DisplayTTY extends Display{
	
	public DisplayTTY(LIFE life) {
		super(life);
	}
	
	public static String[] utf8 = {".","#", "▢","▣", "░","▓", "◇","◈", "☐","☒"};
	public void show(){
		update();
	}
	public void update(){
		int style = 0;
		Iterator<Cellule> raw = life.raw();
		Coord next = (raw.hasNext())?raw.next():null;
		
		for(int y=life.y(), yp=y+life.h(); y<yp; ++y){
			for(int x=life.x(), xp=x+life.w(); x<xp; ++x){
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
	@Override
	public void show(Coord topLeft, Coord bottomRight) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void view() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void view(Coord topLeft, Coord bottomRight) {
		// TODO Auto-generated method stub
		
	}
}
