package learn.field_agent.data;

import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcTemplateRepositoryTest {

    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void shouldFindAll() {
        List<Alias> actual = repository.findAll();
        assertNotNull(actual);
        assertTrue(actual.size() >= 6);
    }

    @Test
    void shouldFindByAgentId() {
        List<Alias> actual = repository.findByAgentId(1);
        assertNotNull(actual);
        assertTrue(actual.size() >= 4);
    }

    @Test
    void shouldAdd() {
        Alias alias = new Alias();
        alias.setName("Squidward");
        alias.setPersona("Tentacles");
        alias.setAgentId(2);
        Alias actual = repository.add(alias);
        assertNotNull(actual);
        assertEquals(7, actual.getAliasId());
    }

    @Test
    void shouldUpdate() {
        Alias alias = new Alias();
        alias.setAliasId(2);
        alias.setName("Spongebob");
        alias.setPersona("Squarepants");
        alias.setAgentId(2);
        assertTrue(repository.update(alias));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(5));
        assertFalse(repository.deleteById(5));
    }

}