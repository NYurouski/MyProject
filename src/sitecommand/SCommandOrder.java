package sitecommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import exception.MyException;
import beans.Car;
import beans.Order;
import service.StationService;

public class SCommandOrder extends SCommand
{
	private static final Logger log = Logger.getLogger(SCommandOrder.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		
		int carid = Integer.parseInt(request.getParameter("carid"));
		Car car = new Car();
		ArrayList<Order> orders = new ArrayList<Order>();
		try
		{
			StationService service = StationService.getService();
			
			 car = service.getCarById(carid);
			
			 orders = new ArrayList<Order>();
			
			orders = (ArrayList<Order>) service.getOrdersByCarId(car.getId());
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
		
		request.setAttribute("orders", orders);
		request.setAttribute("name", name);
		request.setAttribute("surname", surname);
		request.setAttribute("car", car);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/orders.jsp");
		try 
		{
			dispatcher.forward(request, response);
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


}
