package life.display;

import life.LIFE;
import net.slashie.libjcsi.jcurses.JCursesConsoleInterface;

public class DisplayVT100 extends DisplayCurse{
	public DisplayVT100(LIFE life) {
		super(new JCursesConsoleInterface());
		data = life;
	}
	public DisplayVT100() {
		super(new JCursesConsoleInterface());
	}
	
	public static String[] utf8 = {".","#", "▢","▣", " ","▓", "◇","◈", "☐","☒"};
	public static int style = 2;
}
