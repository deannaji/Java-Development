package com.cpsc476;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antlr.collections.List;

@WebServlet("/public")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
      EntityManagerFactory factory; 

   @Override
   public void init(){
	   this.factory = Persistence.createEntityManagerFactory("EntityMappings");
   }
   @Override
   public void destroy()
   {
       super.destroy();
       this.factory.close();
   } 
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager manager = null;
		EntityTransaction transaction = null;
		try{
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		
		transaction.begin();
		
		//Getting one book, and setting it as an attribute. 
		Book book = manager.find(Book.class, 1);
		PrintWriter writer = response.getWriter();
		writer.append(book.getTitle()+", by "+book.getAuther());
		//request.setAttribute("Book", book);
	    
		CriteriaBuilder builder = manager.getCriteriaBuilder();
	    CriteriaQuery<Book> q3 = builder.createQuery(Book.class);
        /*        
	    ArrayList list= new ArrayList(); 
        list= (ArrayList) manager.createQuery(q3.select(q3.from(Book.class)) ).getResultList();
		Book book1 = (Book) list.get(0);
        System.out.println(book1.getTitle());
        */
        
        //request.getRequestDispatcher("/WEB-INF/jsp/view/entities.jsp")
        //.forward(request, response);
       
        transaction.commit();
		}
		catch (Exception e){
			if(transaction != null && transaction.isActive())
                transaction.rollback();
            e.printStackTrace(response.getWriter());
		}
		finally{
			manager.close();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
