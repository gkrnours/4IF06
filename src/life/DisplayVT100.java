package life;

import java.util.Iterator;

public class DisplayVT100 {
	protected LIFE life;
	
	public DisplayVT100(LIFE life){
		this.life = life;
	}
	public static String[] utf8 = {".","#", "▢","▣", "░","▓", "◇","◈", "☐","☒"};
	public void show(){
		int style = 0;
		Iterator<Coord> raw = life.raw();
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
}
