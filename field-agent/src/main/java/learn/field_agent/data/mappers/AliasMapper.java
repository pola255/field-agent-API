package learn.field_agent.data.mappers;

import learn.field_agent.models.Alias;
import learn.field_agent.models.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AliasMapper implements RowMapper<Alias> {
    @Override
    public Alias mapRow(ResultSet resultSet, int i) throws SQLException {
        Alias alias = new Alias(
                resultSet.getInt("alias_id"),
                resultSet.getString("name"),
                resultSet.getString("persona"),
                resultSet.getInt("agent_id")
        );

        return alias;
    }
}
