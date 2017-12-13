package edu.umsl.esi.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.esi.dao.ESIregistry_entryDao;
import edu.umsl.esi.domain.RegistryEntry;
import static java.awt.SystemColor.window;
import static java.lang.System.console;

/**
 * Servlet implementation class UpdateRegistryEntryServlet
 */
@WebServlet("/updateEntry")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		JsonObject jsonObject = null;

		String input = request.getParameter("updatentry");
		
		try {
			ESIregistry_entryDao regentrdao = new ESIregistry_entryDao();
			int regentid = 0;
			String errormsg = "";

			if (input != null && input.length() > 0) {
				JsonReader reader = Json.createReader(new StringReader(input));

				JsonObject entryObject = reader.readObject();

				reader.close();

				int hasid = regentrdao.hasRegistryId(entryObject.getInt("id"));

				 System.out.println("hasid: = " + hasid);
				//console.log(hasid);
				if (hasid==0) {
                                    regentid=hasid;
                                     errormsg = "This id not exists";
					
				} else {
                                    
                                   
                                    
                                    RegistryEntry regentr = new RegistryEntry();

					regentr.setScope(entryObject.getString("scope"));
					regentr.setName(entryObject.getString("name"));
					regentr.setValue(entryObject.getString("value"));
                                         
                                        String updaconf = entryObject.getString("confidential");

					if (updaconf.equals("true")) 
					 {
						regentr.setConfidential(1);
					} else {
						regentr.setConfidential(0);
					}

					regentrdao.updateRegistryEntry(regentr);

					regentid =hasid;
					
				}
				jsonObject = Json.createObjectBuilder().add("regentid", regentid).add("errormsg", errormsg).build();
                                // jsonObject = Json.createObjectBuilder().add("errormsg", errormsg).build();
		
                        }

			regentrdao = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.print(jsonObject);
		out.flush();
		out.close();
	}

}
