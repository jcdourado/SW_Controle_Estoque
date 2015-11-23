package utilities;

public class EstoqueException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public EstoqueException() {
	}

	public EstoqueException(String arg0) {
		super(arg0);
	}

	public EstoqueException(Throwable arg0) {
		super(arg0);
	}

	public EstoqueException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public EstoqueException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}
