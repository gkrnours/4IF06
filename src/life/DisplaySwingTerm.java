package life;

import java.util.Iterator;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class DisplaySwingTerm extends Display{
	private ConsoleSystemInterface csi;
	private static int i=0;
	private static Coord origin = null;
	private static Coord span = null;
	public DisplaySwingTerm(LIFE life) {
		super(life);
	}
	
	public static String[] utf8 = {".","#", "â–¢","â–£", " ","â–“", "â—‡","â—ˆ", "â˜�","â˜’"};
	public void show(){
		show(new Coord(0, 0), new Coord(60, 20));
	}
	@Override
	public void show(Coord topLeft, Coord bottomRight) {
		origin = topLeft;
		span = bottomRight;
		csi = new WSwingConsoleInterface("Life");
		csi.cls();
		update();
	}
	
	public void update(){
		int style = 0;
		Iterator<Cellule> raw = life.raw();
		Coord next = (raw.hasNext())?raw.next():null;
		// continuer tant que l'on est pas dans origin <> span
		csi.cls();
		
		for(int y=origin.y(), yp=span.y(); y<yp; ++y){
			for(int x=life.x(), xp=x+life.w(), dx = -x; x<xp; ++x){
				if(next!=null && next.isAt(x,y)){
					csi.print(x+dx, y, utf8[1+style], CSIColor.FUCSHIA_PINK);
					next = (raw.hasNext())?raw.next():null;
				} else {
					csi.print(x+dx, y, utf8[0+style], CSIColor.ALICE_BLUE);
				}
			}
		}
		csi.print(76, 0, ""+i, CSIColor.APRICOT);
		csi.refresh();
		i++;
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
