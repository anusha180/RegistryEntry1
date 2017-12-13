package edu.umsl.esi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.umsl.esi.domain.RegistryEntry;

public class ESIregistry_entryDao {
	private Connection connection;
	private PreparedStatement hasEntry, addEntry, getMaxId, listEntries,delEntry,upEntry,hasId;

	public ESIregistry_entryDao() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/re", "root", "root");

		hasEntry = connection.prepareStatement("SELECT reid FROM registry_entry WHERE scope = ? AND name = ?");
                
                hasId = connection.prepareStatement("SELECT reid FROM registry_entry WHERE reid=?");

		addEntry = connection.prepareStatement(
				"INSERT INTO registry_entry (" + "scope, name, value,  confidential) VALUES (?, ?, ?, ?)");
         delEntry= connection.prepareStatement("DELETE from registry_entry WHERE reid=?");
		getMaxId = connection.prepareStatement("SELECT MAX(reid) FROM registry_entry");
		upEntry = connection.prepareStatement("UPDATE registry_entry set scope=? ,name=? ,value=? where reid=?");

		listEntries = connection.prepareStatement("SELECT * FROM registry_entry WHERE scope = ?");
	}

	public void addRegistryEntry(RegistryEntry entr) throws SQLException {
		addEntry.setString(1, entr.getScope());
		addEntry.setString(2, entr.getName());
		addEntry.setString(3, entr.getValue());
		addEntry.setInt(4, entr.getConfidential());

		addEntry.executeUpdate();
	}

	public int hasRegistryEntry(String scp, String nm) throws SQLException {
		int reid = 0;

		hasEntry.setString(1, scp);
		hasEntry.setString(2, nm);

		ResultSet results = hasEntry.executeQuery();

		if (results.next()) {
			reid = results.getInt(1);
		}

		return reid;
	}
        
        public int hasRegistryId(int id) throws SQLException {
		int reid = 0;

		hasId.setInt(1,id);
		

		ResultSet results = hasId.executeQuery();

		if (results.next()) {
			reid = results.getInt(1);
		}

		return reid;
	}

	public int getMaxRegistryEntryId() throws SQLException {
		int maxid = 0;

		ResultSet results = getMaxId.executeQuery();

		if (results.next()) {
			maxid = results.getInt(1);
		}

		return maxid;
	}

        
        	public List<RegistryEntry> listRegistryEntriesByScopeInheri(String scp) throws SQLException {
		List<RegistryEntry> entryList = new ArrayList<RegistryEntry>();

		String[] scpArr = scp.split("/");

		String[] scopeArray = new String[scpArr.length - 1];

		String crtstr = "";

		for (int i = 1; i < scpArr.length; i++) {
			crtstr = crtstr + "/" + scpArr[i];

			scopeArray[i - 1] = crtstr;
		}

		List<String> nameList = new ArrayList<String>();

		for (int j = scopeArray.length - 1; j >= 0; j--) {
			List<RegistryEntry> tmplist = this.listRegistryEntriesByScope(scopeArray[j]);

			for (RegistryEntry crtentry : tmplist) {
				String myname = crtentry.getName();

				if (!nameList.contains(myname)) {
					nameList.add(myname);
					entryList.add(crtentry);
				}
			}
		}

		return entryList;
	}

	public List<RegistryEntry> listRegistryEntriesByScope(String scp) throws SQLException {
		List<RegistryEntry> entryList = new ArrayList<RegistryEntry>();

		listEntries.setString(1, scp);

		ResultSet results = listEntries.executeQuery();

		while (results.next()) {
			RegistryEntry entr = new RegistryEntry();

			entr.setId(results.getInt(1));
			entr.setScope(results.getString(2));
			entr.setName(results.getString(3));
			entr.setValue(results.getString(4));
			entr.setConfidential(results.getInt(5));

			entryList.add(entr);
		}

		return entryList;
	}
        
	/*public List<RegistryEntry> listRegistryEntriesByScope(String scp) throws SQLException {
		List<RegistryEntry> entryList = new ArrayList<RegistryEntry>();

		listEntries.setString(1, scp);
		
		ResultSet results = listEntries.executeQuery();

		while (results.next()) {
			RegistryEntry entr = new RegistryEntry();

			entr.setId(results.getInt(1));
			entr.setScope(results.getString(2));
			entr.setName(results.getString(3));
			entr.setValue(results.getString(4));
			entr.setConfidential(results.getInt(5));

			entryList.add(entr);
		}

		return entryList;
	}*/

	protected void finalize() {
		try {
			addEntry.close();
			getMaxId.close();
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}



	


	public void updateRegistryEntry(RegistryEntry regentr) throws SQLException {
		// TODO Auto-generated method stub
		upEntry.setString(1, regentr.getScope());
		upEntry.setString(2, regentr.getName());
		upEntry.setString(3, regentr.getValue());
		upEntry.setInt(4, regentr.getId());

		upEntry.executeUpdate();
		}

	public void delRegistryEntry(int reid) throws SQLException {
		// TODO Auto-generated method stub
		delEntry.setInt(1, reid);
		delEntry.executeUpdate();
		
	}

	

	

	
}
