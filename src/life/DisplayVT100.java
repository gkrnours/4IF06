package life;

import java.util.Iterator;

public class DisplayVT100 {
	public static String[] utf8 = {"▢","▣", "░","▓", "◇","◈", "☐","☒"};
	public static void show(LIFE life){
		int style = 4;
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
