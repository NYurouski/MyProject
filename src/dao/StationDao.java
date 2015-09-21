package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import beans.Car;
import beans.Client;
import beans.Order;
import exception.MyException;


public class StationDao 
{
	private Connection connection;
	private static StationDao thisDao; 
	private static final Logger log = Logger.getLogger(StationDao.class);
	  
		
	private StationDao() throws MyException
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
			}
			catch (ClassNotFoundException e)
			{
				log.error("Something failed", e);
				
				throw new MyException("Problem with connection to database");
			}
			
			String username = null;
			String password = null;
			String dbURL = null;
			String propFileName = "config.properties";
			InputStream fis;
	        Properties property = new Properties();
	        
	        try 
	        {	
	            fis = getClass().getClassLoader().getResourceAsStream(propFileName);
	            if(fis!=null)
	            {
	            	property.load(fis);
	            }
	 
	            dbURL = property.getProperty("db.host");
	            username = property.getProperty("db.login");
	            password = property.getProperty("db.password");
	 
	        } 
	        catch (IOException e) 
	        {
	        	log.error("Something failed", e);
	        	throw new MyException("Problem with connection to database");
	        }
			
			try 
			{
				connection = DriverManager.getConnection(dbURL, username, password);
			} 
			catch (SQLException e) 
			{
				
				log.error("Something failed", e);
				throw new MyException("Problem with connection to database");
			}
			
		}
		
	public static  StationDao getDao() throws MyException
		{
			if(thisDao == null)
				thisDao = new StationDao();
			return thisDao;
		}
	
	public void addClient(Client client)throws MyException
	{
		String template = "insert into clients (name, surname, birth, address, mail, phone) "
				+ "VALUES (?, ?, ?, ?, ?,?)";
		;
		try
		{
			PreparedStatement pSt = connection.prepareStatement(template);
			pSt.setString(1, client.getName());
			pSt.setString(2, client.getSurname());
			pSt.setString(3, client.getBirth());
			pSt.setString(4, client.getAddress());
			pSt.setString(5, client.getMail());
			pSt.setString(6, client.getPhone());
			pSt.executeUpdate();
		}
		
		catch(SQLException e)
		 	{
				log.error("Something failed", e);
				throw new MyException("Problem with connection to database");
		 	}
		
	}
	
	public Client getClientByNameSurname(String name, String surname) throws MyException
	{
		Client client = null;
		try 
		{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from clients where name='"+name+"'"
					+ "and surname='"+ surname +"'");
			
			if(rs.next())
			{
				client = new Client();
				client.setName(rs.getString("name"));
				client.setSurname(rs.getString("surname"));
				client.setBirth(rs.getString("birth"));
				client.setAddress(rs.getString("address"));
				client.setMail(rs.getString("mail"));
				client.setId(rs.getInt("id"));
			}
			else 
				return null;
		} 
		catch (SQLException e) 
		{
			
			log.error("Something failed", e);
			throw new MyException("Problem with connection to database");
			
		}
		return client;
	}

	public void addCar(Car car)throws MyException
	{
		String template = "insert into cars (make, model, year, vin, clientid) "
				+ "VALUES (?, ?, ?, ?, ?)";
		;
		try
		{
			PreparedStatement pSt = connection.prepareStatement(template);
			pSt.setString(1, car.getMake());
			pSt.setString(2, car.getModel());
			pSt.setString(3, car.getYear());
			pSt.setString(4, car.getVin());
			pSt.setInt(5, car.getClientId());
			pSt.executeUpdate();
		}
		
		catch(SQLException e)
		 	{
				log.error("Something failed", e);
				throw new MyException("Problem with connection to database");
		 	}
	}

	public List<Car> getCarByClientId(int clientid)throws MyException
	{
		List<Car> cars = new ArrayList<Car>();
		try 
		{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from cars where clientid='"+clientid+"'");
			
			while(rs.next())
			{
				Car car = new Car();
				car.setMake(rs.getString("make"));
				car.setModel(rs.getString("model"));
				car.setYear(rs.getString("year"));
				car.setVin(rs.getString("vin"));
				car.setClientId(rs.getInt("clientid"));
				car.setId(rs.getInt("id"));
				
				cars.add(car);
			}
		} 
		catch (SQLException e) 
		{
			
			log.error("Something failed", e);
			throw new MyException("Problem with connection to database");
			
		}
		return cars;
	}

	public List<Order> getOrdersByCarId(int carid)throws MyException
	{
		List<Order> orders = new ArrayList<Order>();
		try 
		{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from orders where carid='"+carid+"'");
			
			while(rs.next())
			{
				
				Order order = new Order();
				
				order.setDate(rs.getString("date"));
				order.setStatus(rs.getString("status"));
				order.setAmount(rs.getInt("amount"));
				order.setCarid(rs.getInt("carid"));
				order.setId(rs.getInt("id"));			
				orders.add(order);
			}
		} 
		catch (SQLException e) 
		{
			log.error("Something failed", e);
			throw new MyException("Problem with connection to database");
			
		}
		return orders;
	}
	
	public Car getCarById(int id)throws MyException
	{
		Car car = null;
		try 
		{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from cars where id='"+id+"'");
			
			if(rs.next())
			{
				car = new Car();
				
				car.setId(rs.getInt("id"));
				car.setMake(rs.getString("make"));
				car.setModel(rs.getString("model"));
				car.setVin(rs.getString("vin"));
				car.setYear(rs.getString("year"));
				car.setClientId(rs.getInt("clientid"));
				
			}
			else 
				return null;
		} 
		catch (SQLException e) 
		{
			
			log.error("Something failed", e);
			throw new MyException("Problem with connection to database");
			
		}
		return car;
	}

	public void addOrder(Order order)throws MyException
	{
		String template = "insert into orders (date, amount, status, carid) "
				+ "VALUES (?, ?, ?, ?)";
		;
		try
		{
			PreparedStatement pSt = connection.prepareStatement(template);
			pSt.setString(1, order.getDate());
			pSt.setInt(2, order.getAmount());
			pSt.setString(3, order.getStatus());
			pSt.setInt(4, order.getCarid());
			pSt.executeUpdate();
		}
		
		catch(SQLException e)
		 	{
				log.error("Something failed", e);
				throw new MyException("Problem with connection to database");
		 	}
	}

	public void deleteOrder(int id)throws MyException
	{ 
		
		String template = "delete from orders where id = ?";
		try
		{
			PreparedStatement pSt = connection.prepareStatement(template);
			pSt.setInt(1, id);
			pSt.executeUpdate();
		}
		catch (SQLException e) 
		{
			log.error("Something failed", e);
			throw new MyException("Problem with connection to database");
			
		}
				
	}

	public void deleteCar(int id)throws MyException
	{ 
		
		String template = "delete from cars where id = ?";
		try
		{
			PreparedStatement pSt = connection.prepareStatement(template);
			pSt.setInt(1, id);
			pSt.executeUpdate();
		}
		catch (SQLException e) 
		{
			log.error("Something failed", e);
			throw new MyException("Problem with connection to database");
			
		}
		
	}
	
	public void updateOrder(Order order)throws MyException
	{
		String template = "UPDATE orders set date=?, amount=?, status=?, carid=? where id=?;";
		try
		{
			PreparedStatement pSt = connection.prepareStatement(template);
			pSt.setString(1, order.getDate());
			pSt.setInt(2, order.getAmount());
			pSt.setString(3, order.getStatus());
			pSt.setInt(4, order.getCarid());
			pSt.setInt(5, order.getId());
			pSt.executeUpdate();
		}
		catch (SQLException e) 
		{

			log.error("Something failed", e);
			throw new MyException("Problem with connection to database");
		}
	}
	public void updateCar(Car car)throws MyException
	{
		String template = "UPDATE cars set make=?, model=?, year=?, vin=? where id=?;";
		try
		{
			PreparedStatement pSt = connection.prepareStatement(template);
			pSt.setString(1, car.getMake());
			pSt.setString(2, car.getModel());
			pSt.setString(3, car.getYear());
			pSt.setString(4, car.getVin());
			pSt.setInt(5, car.getId());
			pSt.executeUpdate();
		}
		catch (SQLException e) 
		{
			log.error("Something failed", e);
			throw new MyException("Problem with connection to database");
			
		}
	}
}
