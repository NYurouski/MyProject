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

public class SCommandAddCar extends SCommand
{
	private static final Logger log = Logger.getLogger(SCommandAddCar.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String year = request.getParameter("year");
		String vin = request.getParameter("vin");
		String clientid = request.getParameter("clientid");
		
		
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
			request.setAttribute("clientid", clientid);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/addCar.jsp");
			
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
		
		Car car = new Car();
		car.setClientId(Integer.parseInt(clientid));
		car.setMake(make);
		car.setModel(model);
		car.setYear(year);
		car.setVin(vin);;
		
		StationService service;
		try {
			service = StationService.getService();
			service.addCar(car);
		} catch (MyException e1) 
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
			response.sendRedirect("SiteCont?operation=search&name="+request.getParameter("name")+"&surname="+request.getParameter("surname"));
		} 
		catch (IOException e) 
		{
			log.error("Something failed", e);
		}

	}
}
