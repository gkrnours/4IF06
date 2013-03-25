package life;

import java.util.Iterator;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class DisplaySwingTerm extends Display{
	public ConsoleSystemInterface csi;
	private static int i=0;
	private static Coord origin = null;
	private static Coord span = null;
	private static Integer[] defaut = new Integer[]{30, 60};
	public DisplaySwingTerm(LIFE life) {
		super(life);
	}
	
	public static String[] utf8 = {".","#", "â–¢","â–£", " ","â–“", "â—‡","â—ˆ", "â˜�","â˜’"};
	public void show(){
		show(new Coord(0, 0), new Coord(defaut[0], defaut[1]));
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
		while(next!= null && !next.isIn(origin, span)){
			next = (raw.hasNext())?raw.next():null;
		}
		// continuer tant que l'on est pas dans origin <> span
		csi.cls();
		for(int y=origin.y(), yp=span.y(); y<yp; ++y){
			for(int x=origin.x(), xp=span.y(); x<xp; ++x){
				if(next!=null && next.isAt(x,y)){
					csi.print(y, x, utf8[1+style], CSIColor.FUCSHIA_PINK);
					next = (raw.hasNext())?raw.next():null;
					while(next!= null && !next.isIn(origin, span)){
						next = (raw.hasNext())?raw.next():null;
					}
				} else {
					csi.print(y, x, utf8[0+style], CSIColor.ALICE_BLUE);
				}
			}
		}
		csi.print(69, 0, "tour "+i, CSIColor.APRICOT);
		csi.print(64, 1, "origin: x   "+origin.x(), CSIColor.AMARANTH);
		csi.print(72, 2, "y   "+origin.y(), CSIColor.AMARANTH);
		csi.print(66, 3, "span: x   "+span.x(), CSIColor.MAUVE_TAUPE);
		csi.print(72, 4, "y   "+span.y(), CSIColor.MAUVE_TAUPE);
		csi.refresh();
		i++;
	}

	@Override
	public void view() {
		view(new Coord(0, 0), new Coord(defaut[0], defaut[1]));
	}

	@Override
	public void view(Coord topLeft, Coord bottomRight) {
		// TODO Auto-generated method stub
		
	}
}
