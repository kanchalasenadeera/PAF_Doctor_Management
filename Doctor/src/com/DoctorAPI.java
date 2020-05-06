package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner; 

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Doctor;

/**
 * Servlet implementation class DoctorAPI
 */
@WebServlet("/DoctorAPI")
public class DoctorAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Doctor d1 = new Doctor();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorAPI() {
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
		/*doGet(request, response);*/
		String output = d1.insertDoctor(request.getParameter("docName"),
				request.getParameter("docAge"),
				request.getParameter("docGender"),
				request.getParameter("docSpecialization"),
				request.getParameter("hosID"));
		
		System.out.println(request.getParameter("docName")+" "+request.getParameter("docAge")+" "+request.getParameter("docGender")+" "+request.getParameter("docSpecialization")+" "+request.getParameter("hosID"));
				
		response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = d1.updateDoctor(paras.get("didDoctorIDSave").toString(),
				paras.get("docName").toString(),
				paras.get("docAge").toString(),
				paras.get("docGender").toString(),
				paras.get("docSpecialization").toString(),
				paras.get("hosID").toString());
		

		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		String output = d1.deleteDoctor(paras.get("docID").toString());
		response.getWriter().write(output); 
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
					scanner.close();
					String[] params = queryString.split("&");
					for (String param : params)
					{ 

						String[] p = param.split("=");
						map.put(p[0], p[1]);
					}
		}
		catch (Exception e)
		{
		}
		return map;
	}

}
