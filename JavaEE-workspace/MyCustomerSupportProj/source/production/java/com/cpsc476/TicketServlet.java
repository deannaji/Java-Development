package com.cpsc476;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TicketServlet
 */
@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private Map<Integer, Ticket> ticketsDB = new HashMap<>();
    private int key=0;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(request.getSession().getAttribute("username")==null){
			response.sendRedirect("login");
			return;
		}
		
		String action = request.getParameter("action");
		if (action==null){
			action ="list";
		}
		switch(action){
		 case "create":
			loadTicketForm(request, response);
			break;
			
		 case "list":	
	  	 default:
		    viewTickets(request, response);
		    break;
	    }
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action){
		case "create":
			createTicket(request,response);
			break;
		default:  
		}
	}
	
	public void viewTickets(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setAttribute("ticketsDB", ticketsDB);
		Ticket ticket = new Ticket();
		request.setAttribute("Ticket", ticket);
		
		request.getRequestDispatcher("/WEB-INF/jsp/view/tickets.jsp").forward(request, response);
		
	}
	
	public void loadTicketForm(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	     request.getRequestDispatcher("/WEB-INF/jsp/view/form.jsp").forward(request, response);
	}
	
	public void createTicket(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	    Ticket ticket = new Ticket();
	    ticket.setName(request.getParameter("text1"));
	    key++;
	    ticketsDB.put(key, ticket);
	    viewTickets(request, response);
	}

}
