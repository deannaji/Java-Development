package com.cpsc476.site;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

//@SessionAttributes("username")
@Controller
public class UrlShortnerPublicController {
	private UrlDaoInterface urlDaoInterface;
    private UserDaoInterface userDaoInterface;   
    
    @Inject
	public void setUserDaoInterface(UserDaoInterface userDaoInterface) {
		this.userDaoInterface = userDaoInterface;
	}
    
    @Inject
	public void setUrlDaoInterface(UrlDaoInterface urlDaoInterface) {
		this.urlDaoInterface = urlDaoInterface;
	}

	
	@RequestMapping(value={"/home"}, method= RequestMethod.GET)
	public View home(HttpServletRequest request){
		String viewName="";
		if (request.getSession().getAttribute("username")==null){
	         viewName="/guest";
		}
		else{
             viewName="/private?action=view";    
		}
		return new RedirectView(viewName,true); 
	}
	
	@RequestMapping(value={"/guest"}, method= RequestMethod.GET)
	public String guest(){
		return "guest";
	}
	
	@RequestMapping(value={"/guest"}, method= RequestMethod.POST)
	public String publicContent(Map<String,Object> model, @RequestParam("shortUrl") String shortUrl){
		String originalUrl="";
		if(shortUrl == ""){
			
			model.put("blank", true);
			model.put("emptyDB", false);
			model.put("itemnotfound", false);
			
		}else if(urlDaoInterface.checkifUrlExists(shortUrl).geturlCount()!=0){
			
			Url lrl = (Url)urlDaoInterface.getlongurl(shortUrl);
	    	originalUrl = lrl.getLUrl();
	    	if (!originalUrl.equals("")){	    
	    	    model.put("url", originalUrl);
	    	    model.put("emptyDB", false);
	    	    model.put("itemnotfound", false);
	    	    model.put("blank", false);
	    	    }
	    	
		}//finally, if the requested URL is not found in the DB:
    	else{
    		model.put("itemnotfound", true);
    		model.put("emptyDB", false);
    		model.put("blank", false);
    	}
		
		return "public";
	}
}
