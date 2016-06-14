package de.hs_furtwangen.informatik.meldeauskunft.repository;

import java.util.List;

import de.hs_furtwangen.informatik.meldeauskunft.domain.Resident;

public class ResidentRepositoryStub implements ResidentRepository
{
	private List<Resident> residents;
	
	public ResidentRepositoryStub(List<Resident> liste)
	{
		residents=liste;
	}

	public List<Resident> getResidents() 
	{
		return residents;
	}

}
