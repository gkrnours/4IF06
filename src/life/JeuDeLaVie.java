package life;

import life.cell.Coord;
import life.display.Display;
import life.display.DisplayCurse;
import life.display.DisplaySwingTerm;
import life.display.DisplayTTY;
import net.slashie.libjcsi.CharKey;

/**
 * Classe qui lance le jeu de la vie ainsi que toutes ces options(commmandes shell, aide ...)
 *
 */
public class JeuDeLaVie {
	private static Class<? extends Display> engine = DisplayTTY.class;
	private static void help(){
		String[] msg = {
			"Usage: [-name -h] [-s -c -w] TURN FILE",
			"-name:      Display authors name",
			"-h:         Display this message"
		};
		for(String line: msg) System.out.println(line);
	}
	
	/**
	 * Méthode qui renseigne le nom des créateurs du jeu
	 */
	private static void name(){
		String[] msg = {
			"Baptiste Chartier",
			"Jimmy Lourenço",
			"ludovic coues",
			"Fabien Roume"
		};
		for(String line: msg) System.out.println(line);
	}
	

	/**
	 * Methode de simulation du jeu avec lecture de fichier .lif
	 * appel d'interface graphique avec prise en compte de differents parametres
	 * (nombre de generations, temps ...)
	 * @param max
	 * @param filename
	 */
	private static void simulate(final Integer max, String filename){
		System.out.println(filename+" for "+max+" turns.");
		
		// init 
		LIFE life = Loader.read(filename);
		
		Display display;
		try{
			display = (Display)engine.newInstance();
		}catch(Exception e){
			display = new DisplayTTY();
		}
		display.update(life);
		display.show();
		if(display instanceof DisplayCurse){
			boolean forest = true;
			while(forest){
				switch(((DisplayCurse) display).csi.inkey().code){
				case CharKey.SPACE:
					if(!life.hasNext()) break;
					System.out.println(life);
					long t1 = System.currentTimeMillis();
					life = life.next();
					long t2 = System.currentTimeMillis();
					display.update(life);
					if(life instanceof LIFE)
						display.refresh();
					System.out.println((t2-t1)+"µs");
					break;
				case CharKey.k:
				case CharKey.UARROW:
					((DisplayCurse) display).move(new Coord( 1, 0));
					break;
				case CharKey.j:
				case CharKey.DARROW:
					((DisplayCurse) display).move(new Coord(-1, 0));
					break;
				case CharKey.h:
				case CharKey.LARROW:
					((DisplayCurse) display).move(new Coord( 0, 1));
					break;
				case CharKey.l:
				case CharKey.RARROW:
					((DisplayCurse) display).move(new Coord( 0,-1));
					break;
				case CharKey.ESC:
					forest = false;
					break;
				}
			}
		}
	}
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length == 0){
			help();
			System.exit(1);
		}
		switch(args[0]){
		case "-name": name(); break;
		case "-s": // Run for X turn
			engine = DisplaySwingTerm.class;
			Integer max = Integer.parseInt(args[1]);
			simulate(max, args[2]); 
			break;
		case "-c": // analyse a setup in X turn or less
			// TODO analyse file
			break;
		case "-w": // analyse all setup in a directory in X turn or less
			// TODO analyse folder
			break;
		default: help();
		}
		System.exit(0);
	}
}
