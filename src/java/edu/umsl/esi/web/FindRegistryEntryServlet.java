package edu.umsl.esi.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.umsl.esi.dao.ESIregistry_entryDao;
import edu.umsl.esi.domain.RegistryEntry;

@WebServlet("/findRegistryEntry")
public class FindRegistryEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		JsonArray jsonarr = null;
		String resultmsg = "";

		String input = request.getParameter("myentry");
		
		// System.out.println("input: = " + input);
		
		try {
			ESIregistry_entryDao regentrdao = new ESIregistry_entryDao();
			
			if (input != null && input.length() > 0) {
				JsonReader reader = Json.createReader(new StringReader(input));

				JsonObject entryObject = reader.readObject();

				reader.close();
				
				String myscope = entryObject.getString("scope");
				String myname = entryObject.getString("name");
				String myinheri = entryObject.getString("useInheritance");
				if (myscope != null && myscope.length() > 0 && myname != null && myname.length() == 0) {
					List<RegistryEntry> regentrlist = regentrdao.listRegistryEntriesByScope(myscope);
					
                                        if (myinheri.equals("true")) {
						regentrlist = regentrdao.listRegistryEntriesByScopeInheri(myscope);
					} else {
						regentrlist = regentrdao.listRegistryEntriesByScope(myscope);
					}
					if (regentrlist.isEmpty()) {
						resultmsg = "No search result is found.";
					} else {
						JsonArrayBuilder jsonarrbld = Json.createArrayBuilder();
						
						
						for (RegistryEntry regentr : regentrlist) {
							JsonObject jsonobj = Json.createObjectBuilder().add("id", regentr.getId())
									.add("scope", regentr.getScope())
									.add("name", regentr.getName())
									.add("value", regentr.getValue())
									.add("confidential", regentr.getConfidential()).build();
							
							jsonarrbld.add(jsonobj);
						}
						
						jsonarr = jsonarrbld.build();
						
						// System.out.println("jsonarr: = " + jsonarr);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JsonObject jsonObject = Json.createObjectBuilder().add("resultmsg", resultmsg).add("results", jsonarr).build();
		
		// System.out.println("jsonObject: = " + jsonObject);
		
		out.print(jsonObject);
		out.flush();
		out.close();
	}

}
