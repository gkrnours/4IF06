package life;

import java.util.Iterator;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class DisplayVT100 extends Display{
	private ConsoleSystemInterface csi;
	public DisplayVT100(LIFE life) {
		super(life);
	}
	
	public static String[] utf8 = {".","#", "▢","▣", "░","▓", "◇","◈", "☐","☒"};
	public void show(){
		csi = new WSwingConsoleInterface("LIFE");
		csi.cls();
		update();
	}
	
	public void update(){
		int style = 0;
		Iterator<Coord> raw = life.raw();
		Coord next = (raw.hasNext())?raw.next():null;
		csi.cls();
		
		for(int y=life.y(), yp=y+life.h(), dy=-y; y<yp; ++y){
			for(int x=life.x(), xp=x+life.w(), dx = -x; x<xp; ++x){
				if(next!=null && next.isAt(x,y)){
					csi.print(x+dx, y+dy, "#", CSIColor.CARMINE);
					next = (raw.hasNext())?raw.next():null;
				} else {
					csi.print(x+dx, y+dy, ".", CSIColor.CELADON);
				}
			}
		}
		csi.refresh();
	}
}
