package learn.field_agent.domain;

import learn.field_agent.data.LocationRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceServiceTest {

    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository repository;

    @Test
    void findAllShouldReturnProperNumberOfSc() {
        List<SecurityClearance> scOut = new ArrayList<>();
        scOut.add(new SecurityClearance());

        when(repository.findAll()).thenReturn(scOut);

        List<SecurityClearance> results = service.findAll();

        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    void findAllShouldReturnProperData() {
        List<SecurityClearance> scOut = new ArrayList<>();
        SecurityClearance newSc = new SecurityClearance();
        newSc.setSecurityClearanceId(1);
        scOut.add(newSc);

        when(repository.findAll()).thenReturn(scOut);

        List<SecurityClearance> results = service.findAll();

        assertNotNull(results);
        assertEquals(1, results.get(0).getSecurityClearanceId());
    }

    @Test
    void shouldFindById() {
        SecurityClearance newSc = new SecurityClearance();
        newSc.setSecurityClearanceId(1);

        when(repository.findById(1)).thenReturn(newSc);
        SecurityClearance result = service.findById(1);

        assertNotNull(result);
        assertEquals(1, newSc.getSecurityClearanceId());
    }

    @Test
    void shouldNotFindMissingId() {
        SecurityClearance newSc = new SecurityClearance();
        newSc.setSecurityClearanceId(1);

        when(repository.findById(1)).thenReturn(newSc);
        SecurityClearance result = service.findById(2);

        assertNull(result);
    }

    @Test
    void shouldAddValidSc() {
        SecurityClearance scIn = new SecurityClearance();
        scIn.setName("Test");
        SecurityClearance scOut = new SecurityClearance();

        when(repository.add(scIn)).thenReturn(scOut);

        Result<SecurityClearance> result = service.add(scIn);

        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(scOut, result.getPayload());
    }

    @Test
    void shouldNotAddValidIfIdProvided() {
        SecurityClearance scIn = new SecurityClearance();
        scIn.setSecurityClearanceId(1);
        scIn.setName("Test");
        SecurityClearance scOut = new SecurityClearance();

        when(repository.add(scIn)).thenReturn(scOut);

        Result<SecurityClearance> result = service.add(scIn);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals(result.getMessages().get(0), "securityClearanceId cannot be set for `add` operation");
    }

    @Test
    void shouldNotAddValidNameIsMissing() {
        SecurityClearance scIn = new SecurityClearance();
        SecurityClearance scOut = new SecurityClearance();

        when(repository.add(scIn)).thenReturn(scOut);

        Result<SecurityClearance> result = service.add(scIn);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals(result.getMessages().get(0), "security clearance name is required");
    }

    @Test
    void shouldNotAddNullSecurityClearance() {
        SecurityClearance scIn = null;
        SecurityClearance scOut = new SecurityClearance();

        when(repository.add(scIn)).thenReturn(scOut);

        Result<SecurityClearance> result = service.add(scIn);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals(result.getMessages().get(0), "security clearance cannot be null");
    }

    @Test
    void shouldUpdate() {
        SecurityClearance scIn = new SecurityClearance();
        scIn.setSecurityClearanceId(1);
        scIn.setName("Test Update");

        when(repository.update(scIn)).thenReturn(true);

        Result<SecurityClearance> result = service.update(scIn);

        assertTrue(result.isSuccess());
        assertEquals(scIn, result.getPayload());
    }

    @Test
    void shouldNotUpdateBadId() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("test");

        Result<SecurityClearance> result = service.update(securityClearance);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals(result.getMessages().get(0), "securityClearanceId must be set for `update` operation");
    }

    @Test
    void shouldDelete() {
        when(service.deleteById(1)).thenReturn(true);

        boolean result = service.deleteById(1);

        assertTrue(result);
    }

    @Test
    void shouldNotDeleteNegativeId() {
        boolean result = service.deleteById(-1);

        assertFalse(result);
    }

}