package service;

import java.util.List;

import beans.Car;
import beans.Client;
import beans.Order;
import dao.StationDao;
import exception.MyException;



public class StationService 
{
	
		StationDao stationDao;
		
		private static StationService thisService; 
		
		
		public StationService() throws MyException
		{
			stationDao = StationDao.getDao();
		}
		
		public static  StationService getService() throws MyException
		{
			if(thisService == null)
				thisService = new StationService();
			return thisService;
		}
		 public void addClient(Client client)throws MyException
		{
			stationDao.addClient(client);
			
		}
		 
		 public Client getClientByNameSurname(String name, String surname)throws MyException
		 {
			 return stationDao.getClientByNameSurname(name, surname);
		 }
		
		 public void addCar(Car car)throws MyException
			{
			 	stationDao.addCar(car);
			}
		 public List<Car> getCarByClientId(int clientid)throws MyException
		 {
			 return stationDao.getCarByClientId(clientid);
		 }
		 public List<Order> getOrdersByCarId(int carid)throws MyException
		 {
			 return stationDao.getOrdersByCarId(carid);
		 }
		 public Car getCarById(int id)throws MyException
			{
			 	return stationDao.getCarById(id);
			}
		 public void addOrder(Order order)throws MyException
			{
			 	stationDao.addOrder(order);
			}
		 public void deleteOrder(int id)throws MyException
		 {
			 stationDao.deleteOrder(id);
		 }
		 public void deleteCar(int id)throws MyException
		 {
			 stationDao.deleteCar(id);
		 }
		 public void updateOrder(Order order)throws MyException
			{
			  stationDao.updateOrder(order);
			}
		 public void updateCar(Car car)throws MyException
			{
			 	stationDao.updateCar(car);
			}
			
}
