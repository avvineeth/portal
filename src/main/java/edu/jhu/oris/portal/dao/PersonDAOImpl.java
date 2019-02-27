package edu.jhu.oris.portal.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.stereotype.Repository;

import edu.jhu.oris.portal.common.utils.DBEngine;
import edu.jhu.oris.portal.common.utils.DBEngineConstants;
import edu.jhu.oris.portal.common.utils.DBException;
import edu.jhu.oris.portal.common.utils.OutParameter;
import edu.jhu.oris.portal.model.Person;
import oracle.jdbc.internal.OracleTypes;

@Repository("personDao")
public class PersonDAOImpl extends AbstractDAO<Integer, Person> implements PersonDAO {
	protected static Logger logger = Logger.getLogger(PersonDAOImpl.class.getName());
	private DBEngine dBEngine;
	
	public PersonDAOImpl () {
		dBEngine = new DBEngine();
	}

	public List<Person> findAllPersons() {
		        // Person should be the class name not table name
				List<Person> list = (List<Person>) getSession().createQuery("from Person").list();
		        return list;
	}
	
	public void deletePersonById(String id) {
		Query query = getSession().createSQLQuery("delete from Person where id = :id");
        query.setString("id", id);
        query.executeUpdate();
	}

	public void savePerson(Person person) {
		getPersonList();
		getSession().save(person);
	}
	
	
	
	/*
	 * <----------------Procedure call example using JDBC , this method calling following sample procedure --------------------------->
       create or replace PROCEDURE GET_PERSON(
       CUR_GENERIC OUT SYS_REFCURSOR
	   )
       IS
       BEGIN
       OPEN CUR_GENERIC FOR
       SELECT ID,NAME,COUNTRY
       FROM PERSON;       
       END;
	 */
	 public List<HashMap<String, Object>> getPersonData() {
		ArrayList<OutParameter> outputParam = new ArrayList<>();
		outputParam.add(new OutParameter("resultset", DBEngineConstants.TYPE_RESULTSET));
		ArrayList<HashMap<String, Object>> result = null;
		try {
			result = dBEngine.executeProcedure("GET_PERSON", outputParam);
		} catch (DBException e) {
			logger.info("DBException in getPersonData"+ e);
		} catch (IOException e) {
			logger.info("IOException in getPersonData"+ e);
		} catch (SQLException e) {
			logger.info("SQLException in getPersonData"+ e);
		}
		return result;
	}
	 
	 
	 /*Calling same Proceedure using Hibernate*/
	 public List<Person> getPersonList() {
		 
		 StoredProcedureQuery procedureQuery = 
				 getSession().createStoredProcedureQuery("GET_PERSON", Person.class);
		 	procedureQuery.registerStoredProcedureParameter("CUR_GENERIC", void.class, ParameterMode.REF_CURSOR);
		 	procedureQuery.execute();
		 	List<Person> resultList = procedureQuery.getResultList();
		 	return resultList;
	 }
	 
	 
	 
	

}
