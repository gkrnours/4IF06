package life;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Loader {

	public static LIFE read(String filename) {
		File f = new File(filename);
		Scanner io;
		try {
			io = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.err.println("No such file.");
			return null;
		}
		io.useDelimiter("[\\r\\n;]+");
		LIFE life = load(io);
		io.close();
		return life;
	}

	public static LIFE load(Iterator<String> io) {
		Scanner tokens;
		String line;
		Integer x = null, y = null;
		ArrayList<Coord> cells = new ArrayList<Coord>();
		if (!io.hasNext()) {
			System.err.println("Empty file.");
			return null;
		}
		line = io.next();
		if (!line.matches("^#Life 1.0[5|6]")) {
			System.err.println("Wrong format.\n" + line);
			return null;
		}

		while (io.hasNext()) {
			line = io.next();
			switch (line.substring(0, 2)) {
			case "#D":
				break;
			case "#R": // TODO break;
			case "#N": // TODO R23/3 break;
				break;
			case "#P":
				tokens = new Scanner(line);
				tokens.next();
				x = tokens.nextInt();
				y = tokens.nextInt() - 1;
				tokens.close();
				break;
			case "**":
			case "*.":
			case "..":
			case ".*":
				if (x == null) {
					System.err.println("no x ? Oops™");
					return null;
				}
				++y;
				for (int i = 0; i < line.length(); ++i)
					if (line.charAt(i) == '*')
						cells.add(new Coord(x + i, y));
				break;
			default:
				// System.out.println(line);
				// System.err.println("wrong line ? Oops™");
				// return null;
			}
		}
		return new LIFE(cells);
	}
}
