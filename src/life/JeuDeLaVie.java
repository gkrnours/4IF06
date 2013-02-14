package life;

public class JeuDeLaVie {
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
			"iTzamma",
			"ludovic coues",
			"startrockque"
		};
		for(String line: msg) System.out.println(line);
	}

	
	public static void simulate(Integer max, String filename){
		System.out.println(filename+" for "+max+" turns.");
		LIFE life = Loader.read(filename);
		Display display = new DisplayVT100(life);
		life.debug();
		display.show();
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
