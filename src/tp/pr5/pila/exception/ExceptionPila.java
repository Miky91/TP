package tp.pr5.pila.exception;

import tp.pr5.commands.exceptions.CommandExecutionException;

public class ExceptionPila extends CommandExecutionException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	Throwable arrojar;
	
	public ExceptionPila(){
		
	}
	
	public ExceptionPila(java.lang.String arg0){
		super(arg0);
	}
	
	public ExceptionPila(java.lang.Throwable arg0){
		super(arg0);
	}
	
	public ExceptionPila(java.lang.String arg0, java.lang.Throwable arg1){
		super (arg0,arg1);
	}

}
