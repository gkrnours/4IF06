package life.display;

import java.util.Iterator;

import life.LIFE;
import life.cell.Cellule;
import life.cell.Coord;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.jcurses.JCursesConsoleInterface;

public class DisplayVT100 extends Display{
	private ConsoleSystemInterface csi;
	public DisplayVT100(LIFE life) {
		super(life);
	}
	
	public static String[] utf8 = {".","#", "▢","▣", " ","▓", "◇","◈", "☐","☒"};
	public void show(){
		csi = new JCursesConsoleInterface();
		csi.cls();
		update();
	}
	
	public void update(){
		int style = 0;
		Iterator<Cellule> raw = life.raw();
		Coord next = (raw.hasNext())?raw.next():null;
		csi.cls();
		
		for(int y=life.y(), yp=y+life.h(), dy=-y; y<yp; ++y){
			for(int x=life.x(), xp=x+life.w(), dx = -x; x<xp; ++x){
				if(next!=null && next.isAt(x,y)){
					csi.print(x+dx, y+dy, utf8[1+style], CSIColor.CARMINE);
					next = (raw.hasNext())?raw.next():null;
				} else {
					csi.print(x+dx, y+dy, utf8[0+style], CSIColor.CELADON);
				}
			}
		}
		csi.refresh();
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
