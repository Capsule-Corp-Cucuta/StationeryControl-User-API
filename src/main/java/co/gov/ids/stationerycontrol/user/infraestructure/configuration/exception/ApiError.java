package co.gov.ids.stationerycontrol.user.infraestructure.configuration.exception;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to map API Errors.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public class ApiError {

    @JsonProperty("status")
    private HttpStatus status;
    @JsonProperty("message")
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
    }

    /**
     * Function to get errors.
     *
     * @return errors.
     */
    public List<String> getErrors() {
        return new ArrayList<>(errors);
    }

    /**
     * Function to get status error.
     *
     * @return status error.
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Function to get message error.
     *
     * @return message error.
     */
    public String getMessage() {
        return this.message;
    }

}
