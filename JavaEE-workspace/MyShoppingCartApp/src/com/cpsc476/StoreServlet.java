package com.cpsc476;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final Map<Integer,String> storeDB = new Hashtable<>();
    
    
    public StoreServlet(){
      this.storeDB.put(1,"Nails");
      this.storeDB.put(2,"Tape");
      this.storeDB.put(3,"Glue");
      this.storeDB.put(4,"Sandpaper");
      this.storeDB.put(5,"Paint");
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action==null){
			this.browse(request, response);
		}
		
		switch (action){
		case "addToCart":
			this.addToCart(request,response);
			break;
		case "viewCart":
			this.viewCart(request,response);
			break;
		case "emptyCart":
			this.emptyCart(request,response);
			break;
		case "browse":	
		default:
			this.browse(request,response);
			break;
		}
	}
	
	private void browse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("storeDB", this.storeDB);
		request.getRequestDispatcher("/WEB-INF/jsp/view/browse.jsp").forward(request, response);
	}
	
	
	private void viewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("storeDB", this.storeDB);
		request.getRequestDispatcher("/WEB-INF/jsp/view/viewCart.jsp").forward(request, response);
	}
	
	private void emptyCart(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute("cart");
		response.sendRedirect("store?action=viewCart");
	}
	
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int productId=Integer.parseInt(request.getParameter("id"));
		//System.out.println("id is:"+id);
    	HttpSession session = request.getSession();
		if (session.getAttribute("cart")==null){
			session.setAttribute("cart", new Hashtable<Integer,Integer>());
		}
		Map <Integer,Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
		if (!cart.containsKey(productId)){
			cart.put(productId, 0);
		}
		cart.put(productId, cart.get(productId)+1);
		response.sendRedirect("store?action=browse");
	}

}
