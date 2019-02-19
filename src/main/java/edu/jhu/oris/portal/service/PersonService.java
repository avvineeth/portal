package edu.jhu.oris.portal.service;

import java.util.List;

import edu.jhu.oris.portal.model.Person;

public interface PersonService {
	
    List<Person> findAllPersons(); 
    
    public void deletePerson(String id);

}
