package server;

public class WrongLength extends Exception{
	
	private static final long serialVersionUID = -1283348700658572944L;
	
	public WrongLength() {
		super();
	}
	
	public WrongLength(String str) {
		super(str);
	}
}
