package agroscience.fields.dao.repositories;

import agroscience.fields.dto.field.CoordinatesWithFieldId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JbdcDao {

    private final JdbcTemplate jdbcTemplate;

    public JbdcDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public List<CoordinatesWithFieldId> getAllCoordinates() {

        return jdbcTemplate.query(
                "SELECT id as fieldId, ST_X(ST_Centroid(geom)) as longitude, ST_Y(ST_Centroid(geom)) as latitude FROM fields",
                (rs, rowNum) -> new CoordinatesWithFieldId(rs.getLong("fieldId"),
                        rs.getDouble("longitude"),
                        rs.getDouble("latitude"))
        );
    }

    public Long getOrgIdByFieldId(Long fieldId){
        return jdbcTemplate.queryForObject(
                "SELECT f.organization_id as orgId \n" +
                        "FROM soil s\n" +
                        "JOIN fields f ON s.field_id = f.id\n" +
                        "WHERE s.id = ?",
                Long.class,
                fieldId
        );
    }
}
