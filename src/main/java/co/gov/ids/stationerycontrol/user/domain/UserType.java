package co.gov.ids.stationerycontrol.user.domain;

/**
 * Enum of user types.
 *
 * @author Sergio Rodriguez
 * @version 0.0.1
 * @since 2020
 */
public enum UserType {

    /**
     * Administrator of the application.
     */
    ADMINISTRATOR,

    /**
     * Departmental health institute employee.
     */
    IDS,

    /**
     * National administrative department of statistics employee.
     */
    DANE,

    /**
     * Departmental employee.
     */
    DEPARTMENTAL,

    /**
     * Municipal employee.
     */
    MUNICIPAL,

    /**
     * Healthcare institute employee.
     */
    INSTITUTIONAL;

}
