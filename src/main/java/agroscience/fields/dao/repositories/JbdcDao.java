package agroscience.fields.dao.repositories;

import agroscience.fields.dto.field.CoordinatesWithFieldId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

@Repository
public class JBDCFieldDao {

    private final JdbcTemplate jdbcTemplate;

    public JBDCFieldDao(DriverManagerDataSource dataSource) {
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
}
