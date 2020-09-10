package co.gov.ids.stationerycontrol.user.infraestructure.mail;

public interface EmailPort {

    public boolean sendEmail(EmailBody emailBody);

}
