package dev1.alexkjam64.SpringBootProject.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

@Repository
public class ClientEmailRepository {
    private final NamedParameterJdbcTemplate template;

    public ClientEmailRepository(NamedParameterJdbcTemplate template){
        this.template = template;
    }

    private static final String batchQuery = """
            SELECT "id", "email"
            FROM "Nintendo"."Email"
            WHERE "id" IN (:ID)
            """;

    public List<ClientEmail> getBatchEmails(List<Integer> ids){
        return template.query(batchQuery, new MapSqlParameterSource("ID", ids), new ClientEmailMapper());
    }

    private static final String query = """
            SELECT "id", "email"
	        FROM "Nintendo"."Email"
            WHERE "id" = :ID
            """;

    public ClientEmail getEmail(int id){
        try{
            return template.queryForObject(query, new MapSqlParameterSource("ID", id), new ClientEmailMapper());
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    private static final String insertQuery = """
            INSERT INTO "Nintendo"."Email" ("id","email")
            VALUES (:ID, :EMAIL)
            """;

    // Inserting new data into database
    public void addClient(ClientEmail newClient, int id){
        template.update(insertQuery, newClient.mapInfo(id));
    }

    // Updating data into database
    private static final String updateQuery = """
            UPDATE "Nintendo"."Email"
            SET "email" = :EMAIL
            WHERE "id" = :ID
            """;

    public void updateClient(ClientEmail updateClient, int id){
        template.update(updateQuery, updateClient.mapInfo(id));
    }

    // Deleting data from database
    private static final String deleteQuery = """
            DELETE FROM "Nintendo"."Email"
            WHERE "id" = :ID
            """;

    public void deleteClient(int id){
        template.update(deleteQuery, new MapSqlParameterSource("ID", id));
    }
}
