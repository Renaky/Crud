package controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import model.Company;
import model.ModelException;
import model.User;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.CompanyDAO;
public class CommonsController {
	
	public static void listUsers(HttpServletRequest req) {
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		
		List<User> listUsers = null;
		try {
			listUsers = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		if (listUsers != null)
			req.setAttribute("users", listUsers);		
	}
	
	public static void listCompanies(HttpServletRequest req) {
		CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
		
		List<Company> listCompanies = null;
		try {
			listCompanies = dao.listAll();
		} catch (ModelException e) {
			// Log no servidor
			e.printStackTrace();
		}
		
		if (listCompanies != null)
			req.setAttribute("companies", listCompanies);		
	}
}
