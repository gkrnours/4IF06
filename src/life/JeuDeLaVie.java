package life;

import java.util.Timer;
import java.util.TimerTask;


public class JeuDeLaVie {
	private Class<?> display;
	private static void help(){
		String[] msg = {
			"Usage: [-name -h] [-s -c -w] TURN FILE",
			"-name:      Display authors name",
			"-h:         Display this message"
		};
		for(String line: msg) System.out.println(line);
	}
	
	private static void name(){
		String[] msg = {
			"Baptiste Chartier",
			"Jimmy Lourenço",
			"ludovic coues",
			"Fabien Roume"
		};
		for(String line: msg) System.out.println(line);
	}

	
	public static void simulate(Integer max, String filename){
		System.out.println(filename+" for "+max+" turns.");
		final LIFE life = Loader.read(filename);
		final Display display = new DisplaySwingTerm(life);
		display.show();
		final Timer runner = new Timer();
		final TimerTask update = new TimerTask(){
			@Override
			public void run() {
				if(!life.hasNext()){
					runner.cancel();
					runner.purge();
					return;
				}
				life.next();
				display.update();
			}
		};
		runner.schedule(update, 1000, 1000);
	}
	
	/**
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
	}
}
