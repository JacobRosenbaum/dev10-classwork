package learn.field_agent.data;

import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceJdbcTemplateRepositoryTest {

    @Autowired
    SecurityClearanceJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<SecurityClearance> actual = repository.findAll();
        assertNotNull(actual);
        assertTrue(actual.size() >= 5);
    }

    @Test
    void shouldFindById() {
        SecurityClearance actual = repository.findById(1);
        assertNotNull(actual);
        assertEquals("Secret", actual.getName());
    }

    @Test
    void shouldAdd() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Super Classified");
        SecurityClearance actual = repository.add(securityClearance);
        assertNotNull(actual);
        assertEquals(6, actual.getSecurityClearanceId());
    }

    @Test
    void shouldUpdate() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setSecurityClearanceId(2);
        securityClearance.setName("Not That Classified");
        assertTrue(repository.update(securityClearance));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(5));
        assertFalse(repository.deleteById(5));
    }

    @Test
    void shouldNotDeleteWhenScInUseByAgent() {
        assertFalse(repository.deleteById(1));
    }
}