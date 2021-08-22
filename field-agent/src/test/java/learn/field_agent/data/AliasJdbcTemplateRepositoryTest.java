package learn.field_agent.data;

import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcTemplateRepositoryTest {
    final static int NEXT_ALIAS_ID = 4;

    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void shouldAdd() {
        Alias alias = makeAlias();
        Alias actual = repository.add(alias);
        assertNotNull(actual);
        assertEquals(NEXT_ALIAS_ID, actual.getAliasId());
    }

    @Test
    void shouldUpdate() {
        Alias alias = makeAlias();
        alias.setAliasId(1);
        assertTrue(repository.update(alias));
        alias.setAliasId(16);
        assertFalse(repository.update(alias));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(3));
    }

    @Test
    void shouldFindByNamePersona() {
        Alias alias = new Alias(1, "river", null, 1);
        Alias actual = repository.findByNamePersona("river", null);
        assertEquals(alias, actual);

        alias = new Alias(2, "river", "tedy", 1);
        assertEquals(alias, repository.findByNamePersona("river", "tedy"));

        assertNull(repository.findByNamePersona("river", "rock"));

    }

    Alias makeAlias() {
        Alias alias = new Alias(0, "Test Alias", "Rob", 1);
        return alias;
    }

}
