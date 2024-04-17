package server;

public class WrongFormat extends Exception{
	
	private static final long serialVersionUID = -1283348700658572945L;
	
	public WrongFormat() {
		super();
	}
	
	public WrongFormat(String str) {
		super(str);
	}
}
