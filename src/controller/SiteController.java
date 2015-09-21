package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sitecommand.SCommand;
import sitecommand.SCommandAdd;
import sitecommand.SCommandAddCar;
import sitecommand.SCommandAddOrder;
import sitecommand.SCommandDelCar;
import sitecommand.SCommandDelOrder;
import sitecommand.SCommandEditCar;
import sitecommand.SCommandEditOrder;
import sitecommand.SCommandOrder;
import sitecommand.SCommandSearch;
import org.apache.log4j.*;


@WebServlet("/SiteCont")

public class SiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SiteController.class);
	
    public SiteController() 
    {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String operation = request.getParameter("operation");
		
		SCommand com = null;
		
		if(operation == null)
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/start.jsp");
			
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
		else if(operation.equals("add"))
		{
			
			com = new SCommandAdd();
			com.execute(request, response);
			
		}
		else if (operation.equals("search"))
		{
			com = new SCommandSearch();
			com.execute(request, response);
		}
		else if (operation.equals("addCar"))
		{
			com = new SCommandAddCar();
			com.execute(request, response);
		}
		else if (operation.equals("order"))
		{
			com = new SCommandOrder();
			com.execute(request, response);
		}
		else if (operation.equals("addOrder"))
		{
			com = new SCommandAddOrder();
			com.execute(request, response);
		}
		else if (operation.equals("editOrder"))
		{
			com = new SCommandEditOrder();
			com.execute(request, response);
		}
		else if (operation.equals("editCar"))
		{
			com = new SCommandEditCar();
			com.execute(request, response);
		}
		else if (operation.equals("deleteOrder"))
		{
			com = new SCommandDelOrder();
			com.execute(request, response);
		}
		else if (operation.equals("deleteCar"))
		{
			com = new SCommandDelCar();
			com.execute(request, response);
		}
	
		
		
	 }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
