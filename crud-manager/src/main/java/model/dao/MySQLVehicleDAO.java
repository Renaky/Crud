package model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Company;
import model.ModelException;
import model.User;
import model.Vehicle;

public class MySQLVehicleDAO implements VehicleDAO {

	@Override
	public boolean save(Vehicle vehicle) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlInsert = "INSERT INTO vehicles VALUES (DEFAULT, ?, ?, ?, ?, ?,?);";
		
		db.prepareStatement(sqlInsert);
		
		db.setString(1, vehicle.getColor());
		db.setString(2, vehicle.getBrand());
		db.setString(3, vehicle.getModel());
		db.setString(4, vehicle.getType());
		db.setString(5, vehicle.getNumber_plate());
			
		db.setInt(6, vehicle.getCompany().getId());
		
		return db.executeUpdate() > 0;	
	}

	@Override
	public boolean update(Vehicle vehicle) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlUpdate = "UPDATE vehicles "
				+ " SET color = ?, " //1
				+ " brand = ?, "     //2
				+ " model = ?, "     //3
				+ " type = ?, "		 //4
				+ " number_plate = ?, "//5
				+ "companies_id = ? "	  //6	
				+ " WHERE idvehicles = ?; "; //7 
		
		db.prepareStatement(sqlUpdate);
		
		db.setString(1, vehicle.getColor());
		db.setString(2, vehicle.getBrand());
		db.setString(3, vehicle.getModel());
		db.setString(4, vehicle.getType());
		db.setString(5, vehicle.getNumber_plate());
			
		db.setInt(6, vehicle.getCompany().getId());
		db.setInt(7, vehicle.getVehicleid());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Vehicle vehicle) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sqlDelete = " DELETE FROM vehicles "
		         + " WHERE idvehicles = ?;";

		db.prepareStatement(sqlDelete);		
		db.setInt(1, vehicle.getVehicleid());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public List<Vehicle> listAll() throws ModelException {
		DBHandler db = new DBHandler();
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
			
		// Declara uma instrução SQL
		String sqlQuery = "SELECT v.idvehicles AS 'vehicle_id', v.*, c.*, u.* \n"
		        + "FROM vehicles v \n"
		        + "INNER JOIN companies c ON v.companies_id = c.id \n"
		        + "INNER JOIN users u ON c.user_id = u.id;";
		
		db.createStatement();
	
		db.executeQuery(sqlQuery);

		while (db.next()) {
			User user = new User(db.getInt("id"));
			user.setName(db.getString("nome"));
			user.setGender(db.getString("sexo"));
			user.setEmail(db.getString("email"));
			
			Company company = new Company(db.getInt("id"));
			company.setName(db.getString("name"));
			company.setRole(db.getString("role"));
			company.setStart(db.getDate("start"));
			company.setEnd(db.getDate("end"));
			company.setUser(user);
			
			Vehicle vehicle = new Vehicle(db.getInt("vehicle_id"));
			vehicle.setColor(db.getString("color"));
			vehicle.setBrand(db.getString("brand"));
			vehicle.setModel(db.getString("model"));
			vehicle.setType(db.getString("type"));
			vehicle.setNumber_plate(db.getString("number_plate"));
			vehicle.setCompany(company);
			
			vehicles.add(vehicle);
		}
		
		return vehicles;
	}

	@Override
	public Vehicle findById(int id) throws ModelException {
		DBHandler db = new DBHandler();
		
		String sql = "SELECT * FROM vehicles WHERE idvehicles = ?;";
		
		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();
		
		Vehicle v = null;
		while (db.next()) {
			v = new Vehicle(id);
			v.setColor(db.getString("color"));
			v.setBrand(db.getString("brand"));
			v.setModel(db.getString("model"));
			v.setType(db.getString("type"));
			v.setNumber_plate(db.getString("number_plate"));
			
			CompanyDAO companyDAO = DAOFactory.createDAO(CompanyDAO.class); 
			Company company = companyDAO.findById(db.getInt("companies_id"));
			v.setCompany(company);
			
			break;
		}
		
		return v;
	}


	
}
