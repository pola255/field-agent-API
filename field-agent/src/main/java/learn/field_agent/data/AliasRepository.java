package learn.field_agent.data;

import learn.field_agent.models.Alias;

public interface AliasRepository {

    Alias add(Alias alias);

    boolean update(Alias alias);

    boolean deleteById(int aliasId);

    Alias findByNamePersona(String name, String persona);
}
