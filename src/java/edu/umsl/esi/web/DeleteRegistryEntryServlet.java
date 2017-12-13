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

@WebServlet("/delRegistryEntry")
public class DeleteRegistryEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		JsonArray jsonarr = null;
		String errormsg = "";
                int regentid = 0;
                JsonObject jsonObject = null;

		String input = request.getParameter("dentry");
		
		System.out.println("input: = " + input);
		
		try {
			ESIregistry_entryDao regentrdao = new ESIregistry_entryDao();
			
			if (input != null && input.length() > 0) {
				JsonReader reader = Json.createReader(new StringReader(input));

				JsonObject entryObject = reader.readObject();

				reader.close();
				
                                int hasid = regentrdao.hasRegistryId(entryObject.getInt("reid"));
				
				if(hasid>0)
				
					{regentrdao.delRegistryEntry(hasid);
					regentid=hasid;
					}
				else
				{
					errormsg = "This Id does not exists";
                                        regentid=hasid;
                                        
				}
                           jsonObject = Json.createObjectBuilder().add("regentid", regentid).add("errormsg", errormsg).build();
                           
			}
                        regentrdao = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//JsonObject jsonObject = Json.createObjectBuilder().add("resultmsg", resultmsg).add("results", jsonarr).build();
		          
		// System.out.println("jsonObject: = " + jsonObject);
		
		out.print(jsonObject);
		out.flush();
		out.close();
	}

}
