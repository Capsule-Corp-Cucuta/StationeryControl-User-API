package co.gov.ids.stationerycontrol.user.domain.repository;

import co.gov.ids.stationerycontrol.user.domain.dto.Email;

public interface IEmailRepository {

    boolean sendEmail(Email emailBody);

}