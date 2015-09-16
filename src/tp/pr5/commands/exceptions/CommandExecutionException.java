package tp.pr5.commands.exceptions;

public class CommandExecutionException extends Exception {
    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	Throwable arrojar;
	
	public CommandExecutionException(){
		
	}
    public CommandExecutionException(java.lang.String arg0){
    	
    	super(arg0);
    }

    public CommandExecutionException(java.lang.Throwable arg0){
    	
    	super(arg0);
    }

    public CommandExecutionException(java.lang.String arg0,java.lang.Throwable arg1){
    	
    	super(arg0,arg1);
    	
    }

}
