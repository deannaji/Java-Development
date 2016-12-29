package com.cpsc476;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import freemarker.core.TemplateConfiguration;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


@WebServlet("/public")
public class Servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//bootstrap yourself with Configuration:
	Configuration config = new Configuration(Configuration.VERSION_2_3_25);
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//looking for templates in the classpath (resources folder):
		//config.setClassForTemplateLoading(Servlet1.class,"/");
		
		//looking for templates in a specific (user defined) path:
		config.setServletContextForTemplateLoading(this.getServletContext(), "WEB-INF/ftl");
		
		//get your writer:
		PrintWriter writer = response.getWriter();
		
		//create your Template, and tell it where the template file is:
		Template helloTemplate = config.getTemplate("Hello.ftl");
		
		//this is the name placeholder in the Hello.ftl file, should be in a map, or a java bean. 
		Map<String, Object> root = new HashMap<>();
		root.put("name", "Dean");
		root.put("fullname", "Dean Naji");
		root.put("dob", "02/05/1984");
		
		String[] classes={"476","589"};
		root.put("classes", classes);
		
		try {
			//finally, feed your template obj. with the map and the writer:
			helloTemplate.process(root, writer);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
