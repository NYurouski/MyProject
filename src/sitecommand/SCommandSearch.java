package sitecommand;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import exception.MyException;
import service.StationService;
import beans.Car;
import beans.Client;
import beans.Order;

public class SCommandSearch extends SCommand
{
	private static final Logger log = Logger.getLogger(SCommandSearch.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		
		try
		{
		
		Client client;
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		if(name == null || surname == null || surname == "" || name == "")
		{
			String error = "You should fill in all form.";
			
			if (name == null || name =="")
			{
				error += " Fill in field Name.";
			}
			else
			{
				request.setAttribute("name", name);
			}
			if (surname == null || surname =="")
			{
				error += " Fill in field Surname.";
			}
			else
			{
				request.setAttribute("surname", surname);
			}
			
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
		
			StationService service = StationService.getService();
			client = service.getClientByNameSurname(name, surname);
		
		
		if (client == null)
		{	
			request.setAttribute("name", name);
			request.setAttribute("surname", surname);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/addClient.jsp");
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
		else
		{ 
				ArrayList<Car> cars = new ArrayList<Car>();
				 
				 cars = (ArrayList<Car>) service.getCarByClientId(client.getId());
				 
				if(!cars.isEmpty())
				{
					ArrayList<Order> orders = new ArrayList<Order>();
					
					for(int i=0; i < cars.size(); i++)
					{
						
						orders = (ArrayList<Order>) service.getOrdersByCarId(cars.get(i).getId());
						
						if(!orders.isEmpty())
						{
							cars.get(i).setOrders(true);
						}
						else cars.get(i).setOrders(false);
					
					}
					request.setAttribute("cars", cars);
				}
					
				
			request.setAttribute("client", client);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/clientCard.jsp");
			
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
	}
}
