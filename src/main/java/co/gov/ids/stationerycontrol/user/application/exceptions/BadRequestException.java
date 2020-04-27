package co.gov.ids.stationerycontrol.user.application.exceptions;

/**
 * Class to describe a BadRequest exception.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}
