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

@WebServlet("/addRegistryEntry")
public class AddRegistryEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		JsonObject jsonObject = null;

		String input = request.getParameter("crtentry");
		
		try {
			ESIregistry_entryDao regentrdao = new ESIregistry_entryDao();
			int regentid = 0;
			String errormsg = "";

			if (input != null && input.length() > 0) {
				JsonReader reader = Json.createReader(new StringReader(input));

				JsonObject entryObject = reader.readObject();

				reader.close();

				int hasid = regentrdao.hasRegistryEntry(entryObject.getString("scope"), entryObject.getString("name"));

				// System.out.println("hasid: = " + hasid);
				
				if (hasid == 0) {
					RegistryEntry regentr = new RegistryEntry();

					regentr.setScope(entryObject.getString("scope"));
					regentr.setName(entryObject.getString("name"));
					regentr.setValue(entryObject.getString("value"));
                                        
                                        String addconf = entryObject.getString("confidential");

					if (addconf.equals("true")) 
						regentr.setConfidential(1);
					else 
						regentr.setConfidential(0);
					

					regentrdao.addRegistryEntry(regentr);

					regentid = regentrdao.getMaxRegistryEntryId();
				} else {
					errormsg = "This registry entry already exists.";
				}
				
				jsonObject = Json.createObjectBuilder().add("regentid", regentid).add("errormsg", errormsg).build();
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
