package agroscience.fields.dao.repositories;

import agroscience.fields.dto.field.CoordinatesWithFieldId;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JbdcDao {

    private final JdbcTemplate jdbcTemplate;
    private final String GET_ORG_ID_BY_FIELD_ID = """
            SELECT f.organization_id as orgId 
            FROM soil s
            JOIN fields f ON s.field_id = f.id
            WHERE s.id = ?
            """;

    private final String GET_ALL_COORDINATES = """
            SELECT 
                id as fieldId, 
                ST_X(ST_Centroid(geom)) as longitude, 
                ST_Y(ST_Centroid(geom)) as latitude 
            FROM fields
            """;
    public List<CoordinatesWithFieldId> getAllCoordinates() {

        return jdbcTemplate.query(
                GET_ALL_COORDINATES,
                (rs, rowNum) -> new CoordinatesWithFieldId(rs.getLong("fieldId"),
                        rs.getDouble("longitude"),
                        rs.getDouble("latitude"))
        );
    }

    public Long getOrgIdByFieldId(Long fieldId){
        return jdbcTemplate.queryForObject(
                GET_ORG_ID_BY_FIELD_ID,
                Long.class,
                fieldId
        );
    }
}
