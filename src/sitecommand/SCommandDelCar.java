package sitecommand;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import exception.MyException;
import service.StationService;
import beans.Order;

public class SCommandDelCar extends SCommand
{
	private static final Logger log = Logger.getLogger(SCommandDelCar.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		int carid = Integer.parseInt(request.getParameter("car"));
		
		try
		{
			
			
			StationService service = StationService.getService();
			List<Order> orders = service.getOrdersByCarId(carid);
			if(orders.isEmpty())
			{
				service.deleteCar(carid);
			}
			else
			{	
				
				try 
				{
					response.sendRedirect("SiteCont?operation=search&name="+request.getParameter("name")+
							"&surname="+request.getParameter("surname"));
					return;
				} 
				
				catch (IOException e) 
				{
					
					log.error("Something failed", e);
				}
			}
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
			response.sendRedirect("SiteCont?operation=search&name="+request.getParameter("name")+
					"&surname="+request.getParameter("surname"));
		} 
		catch (IOException e) 
		{
			log.error("Something failed", e);
		}
	}
}
