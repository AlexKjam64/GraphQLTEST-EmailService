package dev1.alexkjam64.SpringBootProject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ClientEmailMapper implements RowMapper<ClientEmail>{
    
    @Override
    public ClientEmail mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
        return new ClientEmail(rs.getInt("id"),
                            rs.getString("email"));
    }
}
