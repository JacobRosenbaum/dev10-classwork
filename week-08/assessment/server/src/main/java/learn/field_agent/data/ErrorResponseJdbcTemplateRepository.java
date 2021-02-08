package learn.field_agent.data;

import learn.field_agent.data.mappers.ErrorResponseMapper;
import learn.field_agent.data.mappers.SecurityClearanceMapper;
import learn.field_agent.models.ErrorResponse;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ErrorResponseJdbcTemplateRepository implements ErrorResponseRepository {

    private final JdbcTemplate jdbcTemplate;

    public ErrorResponseJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ErrorResponse> findAll() {
        final String sql = "select error_id, " +
                "time_stamp, " +
                "error_status, " +
                "http_error, " +
                "message from http_errors;";

        return jdbcTemplate.query(sql, new ErrorResponseMapper());
    }

    @Override
    public List<ErrorResponse> findByErrorCode(int errorCode) {
        final String sql = "select error_id, " +
                "time_stamp, " +
                "error_status, " +
                "http_error, " +
                "message from http_errors " +
                "where error_status = ?";

        return jdbcTemplate.query(sql, new ErrorResponseMapper(), errorCode);
    }

    @Override
    public ErrorResponse add(ErrorResponse errorResponse) {

        final String sql = "insert into http_errors (time_stamp, error_status, http_error, message) "
                + "values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, errorResponse.getTimeStamp());
            ps.setInt(2, errorResponse.getStatus());
            ps.setString(3, errorResponse.getError());
            ps.setString(4, errorResponse.getMessage());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        errorResponse.setErrorResponseId(keyHolder.getKey().intValue());
        return errorResponse;
    }
}
