package edu.jhu.oris.portal.dao;

import java.util.List;

import edu.jhu.oris.portal.model.Person;

public interface PersonDAO {
	
    List<Person> findAllPersons();
    
    public void deletePersonById(String id);
    
    public void savePerson(Person person);

}
