package com;

import java.io.IOException;
import com.FundRequesting;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FundManagementAPI
 */
@WebServlet("/FundManagementAPI")
public class FundManagementAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FundRequesting Fobj = new FundRequesting();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundManagementAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 String output = Fobj.insertDetails(request.getParameter("rID"),
		         request.getParameter("rName"),
		         request.getParameter("rAge"),
		         request.getParameter("rAddress"),
		         request.getParameter("rEmail"),
		         request.getParameter("rPhoneNum"),
		         request.getParameter("projName"),
		         request.getParameter("projDesc"),
		         request.getParameter("fund"));
		        response.getWriter().write(output); 
		
	}
	
	 /**
     * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
     */
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map paras = getParasMap(request); 
        String output = Fobj.updateResearcher(paras.get("hidProductIDSave").toString(),  
                 paras.get("rName").toString(), 
                 paras.get("rAge").toString(), 
                 paras.get("rAddress").toString(),
                 paras.get("rEmail").toString(),
                 paras.get("rPhoneNum").toString(),
                 paras.get("projName").toString(),
                 paras.get("projDesc").toString(),
                 paras.get("fund").toString()); 
        
        System.out.println("put method '"+output+"'");
        response.getWriter().write(output); 
        
    }
    
    /**
     * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

 

        Map paras = getParasMap(request); 
        String output = Fobj.deleteResearcher(paras.get("rID").toString()); 
        response.getWriter().write(output);
    
        System.out.println("delete method '"+output+"'");
        
        
        
        
    }
    
    private static Map getParasMap(HttpServletRequest request) { 
     
    	Map<String, String> map = new HashMap<String, String>(); 
    	
        try { 
        	
          Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
          String queryString = scanner.hasNext() ? 
          scanner.useDelimiter("\\A").next() : ""; 
          scanner.close(); 
          String[] params = queryString.split("&"); 
          for (String param : params) {
        	  
            String[] p = param.split("=");
            map.put(p[0], p[1]); 
      } 
     }  catch (Exception e) 
     { 
     } 
    return map; 
    }

}
