package edu.jhu.oris.portal.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.jhu.oris.portal.model.Person;

@Repository("personDao")
public class PersonDAOImpl extends AbstractDAO<Integer, Person> implements PersonDAO {

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


}
