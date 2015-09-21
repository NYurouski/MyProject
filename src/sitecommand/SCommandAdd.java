package sitecommand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import exception.MyException;
import service.StationService;
import beans.Client;


public class SCommandAdd extends SCommand
{
	private static final Logger log = Logger.getLogger(SCommandAdd.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String birth = request.getParameter("birth");
		String address = request.getParameter("address");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		
		if(name == null || surname == null || surname == "" || name == "" || birth == ""|| birth == null ||
				address == null || address == "" || mail == null || mail == "" || phone == null || phone == "")
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
			if (birth == null || birth =="")
			{
				error += " Fill in field Date of Birth.";
			}
			else
			{
				request.setAttribute("birth", birth);
			}
			if (address == null || address =="")
			{
				error += " Fill in field Address.";
			}
			else
			{
				request.setAttribute("address", address);
			}
			if (mail == null || mail =="")
			{
				error += " Fill in field Mail.";
			}
			else
			{
				request.setAttribute("mail", mail);
			}
			if (phone == null || phone =="")
			{
				error += " Fill in field Phone.";
			}
			else
			{
				request.setAttribute("phone", phone);
			}
			
			
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/addClient.jsp");
			
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
		
		
		Client client = new Client();
		client.setName(name);
		client.setSurname(surname);
		client.setBirth(birth);
		client.setAddress(address);
		client.setMail(mail);
		client.setPhone(phone);
		
		
			StationService service;
			try {
				service = StationService.getService();
				service.addClient(client);
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
			response.sendRedirect("SiteCont?operation=search&name="+client.getName()+"&surname="+client.getSurname());
		} 
		catch (IOException e) 
		{
			log.error("Something failed", e);
		}

	}
}


