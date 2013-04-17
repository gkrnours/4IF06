package life.display;

import life.LIFE;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class DisplaySwingTerm extends DisplayCurse{
	public DisplaySwingTerm(LIFE life) {
		super(new WSwingConsoleInterface("Jeu de la Vie"));
		data = life;
	}
	public DisplaySwingTerm() {
		super(new WSwingConsoleInterface("Jeu de la Vie"));
	}
}
