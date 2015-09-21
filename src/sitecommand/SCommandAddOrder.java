package sitecommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import exception.MyException;
import service.StationService;
import beans.Order;

public class SCommandAddOrder extends SCommand
{
	private static final Logger log = Logger.getLogger(SCommandAddOrder.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
	{
		String status = request.getParameter("status");
		String date = request.getParameter("date");
		String amount = request.getParameter("amount");
		int amountRes = -1; 
		
		if (amount != "" && amount != null)
		{
			amountRes = Integer.parseInt(amount);
		}
		
		if(date == null || date == "" ||  amount == ""|| amount == null || amountRes < 0 || amountRes > 10000)
		{	
			String error = "You should fill in all form.";
			
			if (date == null || date =="")
			{
				error += " Fill in field Date.";
			}
			else
			{
				request.setAttribute("date", date);
			}
			if (amount == null || amount =="")
			{
				error += " Fill in field Amount.";
			}
			else
			{
				amountRes = Integer.parseInt(amount);
				
				if(amountRes <= 10000 && amountRes >= 0)
				{
					request.setAttribute("amount", amount);
				}
				else
				{
					error += " Amount should be from 0 to 10,000";
				}
				
			}
			
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/addorder.jsp");
			
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
		
		Order order = new Order();
		int carid = Integer.parseInt(request.getParameter("carid"));
		order.setStatus(status);
		order.setAmount(amountRes);
		order.setDate(date);
		order.setCarid(carid);
		
		StationService service;
		try {
			service = StationService.getService();
			service.addOrder(order);
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
			response.sendRedirect("SiteCont?operation=order&name="+request.getParameter("name")+
					"&surname="+request.getParameter("surname")+"&carid="+carid);
		} 
		catch (IOException e) 
		{
			log.error("Something failed", e);
		}

		
	}
}
