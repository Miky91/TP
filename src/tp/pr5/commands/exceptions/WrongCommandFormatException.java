package tp.pr5.commands.exceptions;

public class WrongCommandFormatException extends Exception {
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongCommandFormatException(){
    
		
    }

    public WrongCommandFormatException(java.lang.String arg0){
    	
    	super(arg0);
    	
    	
    	
    }

    public WrongCommandFormatException(java.lang.Throwable arg0){
    	
    	super( arg0);
    	
    }

    public WrongCommandFormatException(java.lang.String arg0,java.lang.Throwable arg1){
    	
    	super( arg0,arg1);
    	
    }

}
