package life;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {
	private static LIFE err(Scanner io, String err){
		System.err.println(err);
		if(io != null) io.close();
		return null;
	}
	public static LIFE read(String filename){
		File f = new File(filename);
		Scanner io, tokens;
		String line;
		Integer x = null, y = null;
		ArrayList<Coord> cells = new ArrayList<Coord>();
		
		try { io = new Scanner(f); } 
		catch (FileNotFoundException e) { return err(null, "No such file."); }
		if(!io.hasNext()){ return err(io, "Empty file."); }
		line = io.nextLine();
		if(!line.matches("^#Life 1.0[5|6]")){return err(io,"Wrong format."); }
		
		while(io.hasNext()){
			line = io.nextLine();
			switch(line.substring(0,2)){
			case "#D":	break;
			case "#R":	//TODO break;
			case "#N":	//TODO R23/3 break;
				break;
			case "#P":  
				tokens = new Scanner(line); tokens.next();
				x = tokens.nextInt(); y = tokens.nextInt()-1;
				tokens.close();
				break;
			case "**":
			case "*.":
			case "..":
			case ".*":
				if(x==null) return err(io, "no x ? Oops™");
				++y;
				for(int i=0; i<line.length(); ++i)
					if(line.charAt(i) == '*') 
						cells.add(new Coord(x+i,y));
				break;
			default: 
				System.out.println(line);
				return err(io, "wrong line ? Oops™");
			}
		}
		
		io.close();
		return new LIFE(cells);
	}
}
