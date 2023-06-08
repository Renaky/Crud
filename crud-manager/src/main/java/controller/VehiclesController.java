package controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Company;
import model.ModelException;
import model.Vehicle;
import model.dao.DAOFactory;
import model.dao.VehicleDAO;


@WebServlet(urlPatterns = {"/vehicles", "/vehicle/form", "/vehicle/insert","/vehicle/delete", "/vehicle/update"})
public class VehiclesController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getRequestURI();
		//retorna: "crud-manager/vehicle/form"

		switch (action) {
		case "/crud-manager/vehicle/form": {
			CommonsController.listUsers(req);
			CommonsController.listCompanies(req);
			req.setAttribute("action", "insert");
			//forward: é interno: não tem navegador
			ControllerUtil.forward(req, resp, "/form-vehicle.jsp");		
			break;
		}
		case "/crud-manager/vehicle/delete": {

			deleteVehicle(req, resp);

			break;
		}

		case "/crud-manager/vehicle/update": {
			String idStr = req.getParameter("vehicleId");
			int idVehicle = Integer.parseInt(idStr);

			VehicleDAO dao = DAOFactory.createDAO(VehicleDAO.class);

			Vehicle vehicle = null;
			try {
				vehicle = dao.findById(idVehicle);
			} catch (ModelException e) {
				e.printStackTrace();
			}

			CommonsController.listUsers(req);
			CommonsController.listCompanies(req);
			req.setAttribute("action", "update");
			req.setAttribute("vehicle", vehicle);
			ControllerUtil.forward(req, resp, "/form-vehicle.jsp");			
			break;
		}
		default:
			listVehicles(req);

			ControllerUtil.transferSessionMessagesToRequest(req);

			ControllerUtil.forward(req, resp, "/vehicles.jsp");
		}
	}

	private void listVehicles(HttpServletRequest req) {
		// TODO Auto-generated method stub
		VehicleDAO dao = DAOFactory.createDAO(VehicleDAO.class);

		List<Vehicle> vehicles = null;
		try {
			vehicles = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}

		if (vehicles != null)
			req.setAttribute("vehicles", vehicles);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = req.getRequestURI();


		switch (action) {
		case "/crud-manager/vehicle/insert": {

			insertVehicle(req, resp);
			break;
		}

		case "/crud-manager/vehicle/delete": {

			deleteVehicle(req, resp);

			break;
		}

		case "/crud-manager/vehicle/update": {

			updateVehicle(req, resp);

			break;
		}
		default:
			System.out.println("URL inválida " + action);
		}
		//redireciona a pagina
		ControllerUtil.redirect(resp, req.getContextPath()+"/vehicles");
	}

	private void updateVehicle(HttpServletRequest req, HttpServletResponse resp) {

		String vehicleIdStr = req.getParameter("vehicleId");
		String color = req.getParameter("color");
		String brand = req.getParameter("brand");
		String model = req.getParameter("model");
		String type = req.getParameter("type");
		String plate = req.getParameter("plate");
		Integer companyId = Integer.parseInt(req.getParameter("company"));
		
		Vehicle vehicle = new Vehicle(Integer.parseInt(vehicleIdStr));
		vehicle.setColor(color);
		vehicle.setBrand(brand);
		vehicle.setModel(model);
		vehicle.setType(type);
		vehicle.setNumber_plate(plate);
		vehicle.setCompany(new Company(companyId));

		VehicleDAO dao = DAOFactory.createDAO(VehicleDAO.class);

		try {
			if (dao.update(vehicle)) {
				ControllerUtil.sucessMessage(req, "Veiculo '" + vehicle.getModel() + "' atualizado com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Veiculo '" + vehicle.getModel() + "' não pode ser atualizado.");
			}
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}

	private void deleteVehicle(HttpServletRequest req, HttpServletResponse resp) {
		
		String VehicleIdParameter = req.getParameter("id");

		int vehicleId = Integer.parseInt(VehicleIdParameter);

		VehicleDAO dao = DAOFactory.createDAO(VehicleDAO.class);		

		try {
			Vehicle vehicle = dao.findById(vehicleId);

			if (vehicle == null)
				throw new ModelException("Veiculo não encontrado para exclusão.");

			if (dao.delete(vehicle)) {
				ControllerUtil.sucessMessage(req, "Veiculo '" + vehicle.getModel() + "Placa:" + vehicle.getNumber_plate() + "' deletada com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Veiculo '" + vehicle.getModel() + "' não pode ser deletado. Há dados relacionados ao veículo.");
			}
		} catch (ModelException e) {
			// log no servidor
			if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
				ControllerUtil.errorMessage(req, e.getMessage());
			}
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}

	}

	private void insertVehicle(HttpServletRequest req, HttpServletResponse resp) {
		//pega dados do form
		String color = req.getParameter("color");
		String brand = req.getParameter("brand");
		String model = req.getParameter("model");
		String type = req.getParameter("type");
		String plate = req.getParameter("plate");
		//vehicle: nome do select
		Integer companyId = Integer.parseInt(req.getParameter("company"));
		
		
 
		Vehicle vehi = new Vehicle();
		vehi.setColor(color);
		vehi.setBrand(brand);
		vehi.setModel(model);
		vehi.setType(type);
		vehi.setNumber_plate(plate);
		vehi.setCompany(new Company(companyId));
		//persistencia
		VehicleDAO dao = DAOFactory.createDAO(VehicleDAO.class);

		try {
			if (dao.save(vehi)) {
				ControllerUtil.sucessMessage(req, "Veiculo '" + vehi.getModel() + "' salvo com sucesso.");
			}
			else {
				ControllerUtil.errorMessage(req, "Veiculo '" + vehi.getModel() + "' não pode ser salva.");
			}
		} catch (ModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ControllerUtil.errorMessage(req, e.getMessage());
		}
	}
}
