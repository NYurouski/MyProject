package sitecommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class SCommand 
{
	abstract public void execute(HttpServletRequest request,HttpServletResponse response);
}
