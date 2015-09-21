package sitecommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import exception.MyException;
import service.StationService;

public class SCommandDelOrder extends SCommand
{
	private static final Logger log = Logger.getLogger(SCommandDelOrder.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		try
		{
			StationService service = StationService.getService();
			
			service.deleteOrder(orderid);
		}
		catch(MyException e1)
		{
			String error = e1.getText();
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/start.jsp");
			
			try 
			{
				dispatcher.forward(request, response);
				return;
			} 
			catch (ServletException e) 
			{
				log.error("Something failed", e);
				
			} 
			catch (IOException e) 
			{
				log.error("Something failed", e);
				
			}
		}
		
		
		try 
		{
			response.sendRedirect("SiteCont?operation=order&name="+request.getParameter("name")+
					"&surname="+request.getParameter("surname")+"&carid="+request.getParameter("carid"));
		} 
		catch (IOException e) 
		{
			log.error("Something failed", e);
		}
	}
}
