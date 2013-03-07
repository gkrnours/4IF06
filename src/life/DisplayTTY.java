package life;

import java.util.Iterator;

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
}
