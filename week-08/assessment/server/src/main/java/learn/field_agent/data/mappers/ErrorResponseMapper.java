package learn.field_agent.data.mappers;

import learn.field_agent.models.ErrorResponse;
import learn.field_agent.models.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ErrorResponseMapper implements RowMapper<ErrorResponse> {

    @Override
    public ErrorResponse mapRow(ResultSet resultSet, int i) throws SQLException {
        ErrorResponse errorResponse = new ErrorResponse(
                resultSet.getTimestamp("time_stamp"),
                resultSet.getInt("error_status"),
                resultSet.getString("http_error"),
                resultSet.getString("message")
        );

        errorResponse.setErrorResponseId(resultSet.getInt("error_id"));

        return errorResponse;
    }
}
