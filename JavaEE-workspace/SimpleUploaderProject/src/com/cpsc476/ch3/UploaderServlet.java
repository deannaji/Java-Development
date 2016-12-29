package com.cpsc476.ch3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploaderServlet
 */
@WebServlet(name="UploaderServlet",
		urlPatterns= {"/upload"})

@MultipartConfig(
        fileSizeThreshold = 5_242_880, //5MB
        maxFileSize = 20_971_520L, //20MB
        maxRequestSize = 41_943_040L //40MB
)
public class UploaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	File file = new File();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.append("<html>\n")
		      .append("<h2>Upload your files:</h2>\n")
		      .append("<form method='POST' action='upload' enctype='multipart/form-data'>\n")
		      .append("<input type='file' name='file1' />\n")
		      .append("<input type='submit' />\n")
		      .append("</form>\n")
		      .append("</html>\n");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//File uploading:
		Part filePart = request.getPart("file1");
		upload(filePart);
		System.out.println("File size is: "+file.getContent().length+" bytes.");
		
		
		//File downloading:
		download(response);
		
	}
	
	
	public void upload(Part filePart) throws IOException{
		InputStream inputStream = filePart.getInputStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] bytes = new byte[1024];
		int read;
		
		while( (read=inputStream.read(bytes)) != -1){
            outputStream.write(bytes, 0, read);			
		}
		
		file.setContent(outputStream.toByteArray());
		file.setName(filePart.getSubmittedFileName());
	}
	
	
	public void download(HttpServletResponse response) throws IOException{
		response.setHeader("Content-Disposition",
                "attachment; filename="+file.getName());
        response.setContentType("application/octet-stream");
		ServletOutputStream stream = response.getOutputStream();
		stream.write(file.getContent());
		
	}

}
