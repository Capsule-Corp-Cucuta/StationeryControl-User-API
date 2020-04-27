package co.gov.ids.stationerycontrol.user.application.exceptions;

/**
 * Class to describe a NotFound exception.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
