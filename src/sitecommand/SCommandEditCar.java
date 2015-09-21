package sitecommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import exception.MyException;
import service.StationService;
import beans.Car;

public class SCommandEditCar extends SCommand
{
	private static final Logger log = Logger.getLogger(SCommandEditCar.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String year = request.getParameter("year");
		String vin = request.getParameter("vin");
				
		
		if(make == null || model == null || make == "" || model == "" || year == ""|| year == null ||
				vin == null || vin == "")
		{	
			String error = "You should fill in all form.";
			
			if (make == null || make =="")
			{
				error += " Fill in field Make.";
			}
			else
			{
				request.setAttribute("make", make);
			}
			if (model == null || model =="")
			{
				error += " Fill in field Model.";
			}
			else
			{
				request.setAttribute("model", model);
			}
			if (year == null || year =="")
			{
				error += " Fill in field Date of year.";
			}
			else
			{
				request.setAttribute("year", year);
			}
			if (vin == null || vin =="")
			{
				error += " Fill in field VIN.";
			}
			else
			{
				request.setAttribute("vin", vin);
			}
			
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/editCar.jsp");
			
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
		
		
		
		
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		
				
		
		Car car = new Car();
		
		car.setId(Integer.parseInt(request.getParameter("carid")));
		car.setMake(make);
		car.setModel(model);
		car.setVin(vin);
		car.setYear(year);
		try
		{
			StationService service = StationService.getService();
			service.updateCar(car);
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
			response.sendRedirect("SiteCont?operation=search&name="+name+
					"&surname="+surname);
		} 
		catch (IOException e) 
		{
			log.error("Something failed", e);
		}

	}

}
