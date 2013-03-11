package life;

public class Morte extends Cellule {

	public Morte(Integer x, Integer y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean vivante() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cellule next() {
		return (3 <= neighboor && neighboor <= 3)?this:null;
	}

}
