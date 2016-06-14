package de.roth;
import java.util.*;

import de.hs_furtwangen.informatik.meldeauskunft.domain.Resident;
import de.hs_furtwangen.informatik.meldeauskunft.repository.ResidentRepository;
import de.hs_furtwangen.informatik.meldeauskunft.repository.ResidentRepositoryStub;
import de.hs_furtwangen.informatik.meldeauskunft.service.BaseResidentService;
import de.hs_furtwangen.informatik.meldeauskunft.service.ResidentService;
import de.hs_furtwangen.informatik.meldeauskunft.service.ResidentServiceException;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

public class ServiceTest{
@Test
public void getFilteredResidentTest() {
List<Resident> liste = new ArrayList<Resident>();
List<Resident> result = new ArrayList<Resident>();
List<Resident> result1 = new ArrayList<Resident>();
List<Resident> result2 = new ArrayList<Resident>();

Resident resident1 = new Resident();
resident1.setCity("Furtwangen");
resident1.setFamilyName("Roth");
resident1.setGivenName("Mirjam");
resident1.setStreet("Am Groﬂhausberg");
resident1.setDateOfBirth(null);

Resident resident2 = new Resident();
resident2.setCity("Ettenheim");
resident2.setFamilyName("Oth");
resident2.setGivenName("Rebekka");
resident2.setStreet("Robert-koch");
resident2.setDateOfBirth(null);

liste.add(resident1);
liste.add(resident2);
//Vorname, Nachname, Straﬂe, Stadt, Geburtsdatum

ResidentRepositoryStub stub = new ResidentRepositoryStub(liste);

BaseResidentService service = new BaseResidentService();
service.setResidentRepository(stub);

Resident filterResident = new Resident("M*","*","*","*",null);
Resident filterResident2 = new Resident("*","*","A*","*",null);
Resident filterResident3 = new Resident("*","R*","*","*",null);

result = service.getFilteredResidentsList(filterResident);
result1 = service.getFilteredResidentsList(filterResident2);
result2 = service.getFilteredResidentsList(filterResident3);

assertEquals(resident1.getGivenName(),result.get(0).getGivenName()); //hier fehler muss .getgivenname
assertEquals(resident1.getStreet(),result1.get(0).getStreet()); 
assertEquals(resident1.getFamilyName(),result2.get(0).getFamilyName());
} 
@Test
public void getUniqueResidentTest() throws ResidentServiceException{
List<Resident> liste = new ArrayList<Resident>();

Resident resident1 = new Resident();
resident1.setCity("Furtwangen");
resident1.setFamilyName("Roth");
resident1.setGivenName("Mirjam");
resident1.setStreet("Am Groﬂhausberg");

Resident resident2 = new Resident();
resident2.setCity("Ettenheim");
resident2.setFamilyName("Oth");
resident2.setGivenName("Rebekka");
resident2.setStreet("Robert-koch");
resident2.setDateOfBirth(null);

Resident resident3 = new Resident();
resident3.setCity("Immendingen");
resident3.setFamilyName("Halilaj");
resident3.setGivenName("Ilirian");
resident3.setStreet("Straﬂe");
resident3.setDateOfBirth(null);

liste.add(resident1);
liste.add(resident2);


ResidentRepositoryStub stub = new ResidentRepositoryStub(liste);

BaseResidentService service = new BaseResidentService();
service.setResidentRepository(stub);

Resident UniqueResident = new Resident("Mirjam","Roth","Am Groﬂhausberg","Furtwangen",null);
Resident UniqueResident2 = new Resident("Rebekka","Oth","Robert-koch","Ettenheim",null);
Resident UniqueResident3 = new Resident("Ilirian","Halilaj","Straﬂe","Immendingen",null);
Resident UniqueResident4 = new Resident("*","O*","*","*",null);

Resident result = service.getUniqueResident(UniqueResident);
Resident result2 = service.getUniqueResident(UniqueResident2);


assertEquals(resident1.getGivenName(),result.getGivenName()); 
assertEquals(resident2.getStreet(),result2.getStreet());


}
@Test
public void testGetFilteredResidentsList3()
{
List<Resident> list = new LinkedList<Resident>();
list.add(new Resident("Max", "Mustermann", "Musterstraﬂe", "Musterstadt", new Date()));
list.add(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date()));
list.add(new Resident("Emil", "Dick", "Waldweg", "Musterdorf", new Date()));
ResidentRepository stub = new ResidentRepositoryStub(list);

ResidentService rs = new BaseResidentService();
((BaseResidentService) rs).setResidentRepository(stub);
List<Resident> result = rs.getFilteredResidentsList(new Resident("Ma*", "Mu*", "*straﬂe", "Muster*", new Date()));

assertEquals(2, result.size());

assertEquals("Max", result.get(0).getGivenName());
assertEquals("Mustermann", result.get(0).getFamilyName());
assertEquals("Musterstraﬂe", result.get(0).getStreet());
assertEquals("Musterstadt", result.get(0).getCity());

assertEquals("Martina", result.get(1).getGivenName());
assertEquals("Mustermann", result.get(1).getFamilyName());
assertEquals("Albertstraﬂe", result.get(1).getStreet());
assertEquals("Musterdorf", result.get(1).getCity());
}
@Test
public void testGetUniqueResident1()
{
List<Resident> list = new LinkedList<Resident>();
ResidentRepository stub = new ResidentRepositoryStub(list);

ResidentService rs = new BaseResidentService();
((BaseResidentService) rs).setResidentRepository(stub);

try {
Resident result = rs.getUniqueResident(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date()));
fail("getUniqueResident on empty List should throw exception");
} catch (ResidentServiceException e) {
assertEquals("Suchanfrage lieferte kein eindeutiges Ergebnis!", e.getMessage());
}
}
@Test
public void testGetUniqueResident2()
{
List<Resident> list = new LinkedList<Resident>();
ResidentRepository stub = new ResidentRepositoryStub(list);

ResidentService rs = new BaseResidentService();
((BaseResidentService) rs).setResidentRepository(stub);

try {
Resident result = rs.getUniqueResident(new Resident("*", "*", "*", "*", new Date()));
fail("getUniqueResident with should throw exception");
} catch (ResidentServiceException e) {
assertEquals("Wildcards (*) sind nicht erlaubt!", e.getMessage());
}
}


@Test
public void testGetUniqueResident3()
{
List<Resident> list = new LinkedList<Resident>();
list.add(new Resident("Max", "Mustermann", "Musterstraﬂe", "Musterstadt", new Date()));
list.add(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date()));
list.add(new Resident("Emil", "Dick", "Waldweg", "Musterdorf", new Date()));
ResidentRepository stub = new ResidentRepositoryStub(list);

ResidentService rs = new BaseResidentService();
((BaseResidentService) rs).setResidentRepository(stub);

try {
Resident result = rs.getUniqueResident(new Resident("Max", "Mustermann", null, null, null));
assertEquals("Max", result.getGivenName());
assertEquals("Mustermann", result.getFamilyName());
assertEquals("Musterstraﬂe", result.getStreet());
assertEquals("Musterstadt", result.getCity());
} catch (ResidentServiceException e) {
fail(e.getMessage());
e.printStackTrace();
}
}

@Test
public void testGetUniqueResidentMock()
{
List<Resident> list = new LinkedList<Resident>();
list.add(new Resident("Max", "Mustermann", "Musterstraﬂe", "Musterstadt", new Date()));
list.add(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date()));
list.add(new Resident("Emil", "Dick", "Waldweg", "Musterdorf", new Date()));

ResidentRepository mock = createMock(ResidentRepository.class);
expect(mock.getResidents()).andReturn(list);
replay(mock);

ResidentService rs = new BaseResidentService();
((BaseResidentService) rs).setResidentRepository(mock);

try 
{
Resident result = rs.getUniqueResident(new Resident("Max", "Mustermann", null, null, null));
assertEquals("Max", result.getGivenName());
assertEquals("Mustermann", result.getFamilyName());
assertEquals("Musterstraﬂe", result.getStreet());
assertEquals("Musterstadt", result.getCity());
} catch (ResidentServiceException e) {
fail(e.getMessage());
e.printStackTrace();
}

verify(mock);
}
}