package com.jmzx.maven.plugin.support.logger;

/**  
* @ClassName: 
*	 Logger  
* @since 
*	  V1.0
* @author 
*		zhangyong   
* @version 
*		V1.0      
*/
public interface Logger {
	
	boolean isDebugEnabled();
    
    void debug( CharSequence content );

    void debug( CharSequence content, Throwable error );

    void debug( Throwable error );

    boolean isInfoEnabled();
    
    void info( CharSequence content );

    void info( CharSequence content, Throwable error );

    void info( Throwable error );

    boolean isWarnEnabled();
    
    void warn( CharSequence content );

    void warn( CharSequence content, Throwable error );

    void warn( Throwable error );

    boolean isErrorEnabled();
    
    void error( CharSequence content );

    void error( CharSequence content, Throwable error );

    void error( Throwable error );
}
