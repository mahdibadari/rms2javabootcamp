package com.mycompany.app;

import java.util.List;

//CRUD operations
public interface UnitDAO {
	
	//Create
	public void save(Unit unit);
	//Read
	public Unit getById(int unitId);
	//Update
	public void update(Unit unit);
	//Delete
	public void deleteById(int unitId);
	//Get All
	public List<Unit> getAll();
}