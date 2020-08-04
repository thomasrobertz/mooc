package com.mantiso;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by kevinj.
 */
public class Repeat extends SimpleTagSupport {
    private int repeat;

    public void setRepeat(int repeat){

        this.repeat = repeat;
    }

    @Override
    public void doTag() throws JspException, IOException {
        for(int i = 0; i < repeat; i++){
        	
        	// invoke: Executes the fragment and directs all output to the given Writer, or the JspWriter returned by the getOut() method of the JspContext associated with the fragment if out is null.
            getJspBody().invoke(null);
        }
    }
}
