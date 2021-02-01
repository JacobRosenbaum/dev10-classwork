package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasServiceTest {
    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repository;


    @Test
    void shouldFindByAgentId() {
        Alias alias = new Alias();
        alias.setAliasId(1);
        alias.setAgentId(1);

        List<Alias> all = new ArrayList<>();
        all.add(alias);

        when(repository.findByAgentId(1)).thenReturn(all);
        List<Alias> result = service.findById(1);

        assertNotNull(result);
        assertEquals(1, result.get(0).getAliasId());
    }

    @Test
    void shouldNotFindMissingId() {
        Alias alias = new Alias();
        alias.setAliasId(1);
        alias.setAgentId(1);

        List<Alias> all = new ArrayList<>();
        all.add(alias);

        when(repository.findByAgentId(1)).thenReturn(all);
        List<Alias> result = service.findById(15);

        assertEquals(result.size(), 0);
    }


    @Test
    void shouldAddValidAlias() {
        Alias aliasIn = new Alias();
        aliasIn.setName("Test");
        aliasIn.setPersona("Test");
        aliasIn.setAgentId(2);
        Alias aliasOut = new Alias();

        when(repository.add(aliasIn)).thenReturn(aliasOut);

        Result<Alias> result = service.add(aliasIn);

        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(aliasOut, result.getPayload());
    }

    @Test
    void shouldNotAddIfIdProvided() {
        Alias aliasIn = new Alias();
        aliasIn.setAliasId(1);
        aliasIn.setName("Test");
        aliasIn.setPersona("Test");
        aliasIn.setAgentId(2);
        Alias aliasOut = new Alias();

        when(repository.add(aliasIn)).thenReturn(aliasOut);

        Result<Alias> result = service.add(aliasIn);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals(result.getMessages().get(0), "aliasId cannot be set for `add` operation");
    }

    @Test
    void shouldNotAddIfNameIsMissing() {
        Alias aliasIn = new Alias();
        aliasIn.setPersona("Test");
        aliasIn.setAgentId(2);
        Alias aliasOut = new Alias();

        when(repository.add(aliasIn)).thenReturn(aliasOut);

        Result<Alias> result = service.add(aliasIn);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals(result.getMessages().get(0), "alias name is required");
    }

    @Test
    void shouldNotAddNullSecurityClearance() {
        Alias aliasIn = null;
        Alias aliasOut = new Alias();

        when(repository.add(aliasIn)).thenReturn(aliasOut);

        Result<Alias> result = service.add(aliasIn);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals(result.getMessages().get(0), "alias cannot be null");
    }

    @Test
    void shouldUpdate() {
        Alias aliasIn = new Alias();
        aliasIn.setAliasId(1);
        aliasIn.setName("Test");
        aliasIn.setPersona("Test");
        aliasIn.setAgentId(2);

        when(repository.update(aliasIn)).thenReturn(true);

        Result<Alias> result = service.update(aliasIn);

        assertTrue(result.isSuccess());
        assertEquals(aliasIn, result.getPayload());
    }

    @Test
    void shouldNotUpdateBadId() {
        Alias aliasIn = new Alias();
        aliasIn.setName("Test");
        aliasIn.setPersona("Test");
        aliasIn.setAgentId(2);

        when(repository.update(aliasIn)).thenReturn(true);

        Result<Alias> result = service.update(aliasIn);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals(result.getMessages().get(0), "aliasId must be set for `update` operation");
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