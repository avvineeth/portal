package edu.jhu.oris.portal.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.jhu.oris.portal.dao.PersonDAO;
import edu.jhu.oris.portal.model.Person;

@Service("personService")
@Transactional
public class PersonServiceImpl implements PersonService{
	
	 @Autowired
	 private PersonDAO dao;

	public List<Person> findAllPersons() {
		return dao.findAllPersons();
	}
	
	public void deletePerson(String id) {
		 dao.deletePersonById(id);
	}

	public void savePerson(Person person) {
		dao.savePerson(person);
	}

}
