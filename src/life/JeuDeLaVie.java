package life;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	 * Calcul l'évolution d'une position donnée
	 */
	public static void calculate(final Integer max, String filename){
		LIFE life = Loader.read(filename);
		for(int i=0; 
				i<max && life.hasNext() && !(life instanceof Asymptotique);
				++i)
		{
			life = life.next();
		}
		System.out.println(life.rapport());
	}
	
	/**
	 * Calcul l'évolution de tout les fichiers d'un répertoire et crée un 
	 * fichier html contenant le résultat
	 */
	public static void wall(final Integer max, String filename){
		File dir = new File(filename);
		File[] lifs = dir.listFiles();
	try {
		BufferedWriter out = new BufferedWriter(new FileWriter("OUTPUT.HTML"));
		out.write("<pre>", 0, 3);
		out.newLine();
		for(File lif :lifs){
			LIFE life = Loader.read(lif.toString());
			for(int i=0; 
				i<max && life.hasNext() && !(life instanceof Asymptotique);
				++i)
			{
				life = life.next();
			}
			String rapport = life.rapport();
			out.write(lif.toString(), 0, lif.toString().length());
			out.newLine();
			out.write("\t"+rapport, 0, ("\t"+rapport).length());
			out.newLine();
			out.newLine();
		}
		out.close();
	} catch (IOException e) {
			e.printStackTrace();
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
		Integer max = 0;
		if(2 <= args.length){
			max = Integer.parseInt(args[1]);
		}
		switch(args[0]){
		case "-name": name(); break;
		case "-s": // Run for X turn
			engine = DisplaySwingTerm.class;
			simulate(max, args[2]); 
			break;
		case "-c": // analyse a setup in X turn or less
			calculate(max, args[2]); 
			break;
		case "-w": // analyse all setup in a directory in X turn or less
			wall(max, args[2]);
			break;
		default: help();
		}
		System.exit(0);
	}
}
