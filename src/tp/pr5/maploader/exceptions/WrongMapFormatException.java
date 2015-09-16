package tp.pr5.maploader.exceptions;

public class WrongMapFormatException extends java.io.IOException {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	Throwable arrojar;
	
	public WrongMapFormatException(){
    	
    }

    public WrongMapFormatException(java.lang.String arg0){
    	
    	super(arg0);
    	
    }
    
    public WrongMapFormatException(java.lang.Throwable arg0){
    	
    	super(arg0);
    	
    }

    public WrongMapFormatException(java.lang.String arg0,java.lang.Throwable arg1){
    	
    	super(arg0,arg1);
    }
}