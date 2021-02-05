package co.gov.ids.stationerycontrol.user.domain.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.apache.commons.text.RandomStringGenerator;
import co.gov.ids.stationerycontrol.user.domain.dto.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import co.gov.ids.stationerycontrol.user.domain.dto.Email;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import co.gov.ids.stationerycontrol.user.domain.repository.IUserRepository;
import co.gov.ids.stationerycontrol.user.domain.repository.IEmailRepository;

@Service
public class UserService {

    private final IUserRepository repository;
    private final BCryptPasswordEncoder bCrypt;
    private final IEmailRepository IEmailRepository;

    public UserService(IEmailRepository IEmailRepository, IUserRepository repository, BCryptPasswordEncoder bCrypt) {
        this.bCrypt = bCrypt;
        this.repository = repository;
        this.IEmailRepository = IEmailRepository;
    }

    public User create(User user) {
        user.setEnable(true);
        return repository.create(user, bCrypt.encode(user.getIdentificationCard()));
    }

    public User update(User user) {
        return repository.update(user);
    }

    public boolean disable(String username) {
        return findByUsername(username).map(user -> {
            user.setEnable(false);
            return repository.update(user) != null;
        }).orElse(false);
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<List<User>> findAll(int page) {
        return repository.findAll(page);
    }

    public Optional<List<User>> findByName(String name, int page) {
        return repository.findByName(name, page);
    }

    public Optional<List<User>> findByIdentificationCard(String identificationCard, int page) {
        return repository.findByIdentificationCard(identificationCard, page);
    }

    public boolean changePassword(String username, String oldPass, String newPass) {
        return findByUsername(username).map(user -> {
            if (BCrypt.checkpw(oldPass, repository.getPassword(username))) {
                repository.changePassword(user, bCrypt.encode(newPass));
                return true;
            }
            return false;
        }).orElse(false);
    }

    public boolean recoverPassword(String username) {
        return findByUsername(username).map(user -> {
            // Update Password
            RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(48, 90)
                    .build();
            String newPass = pwdGenerator.generate(12);
            repository.changePassword(user, bCrypt.encode(newPass));
            // Send email
            Email email = new Email();
            email.setTo(user.getEmail());
            email.setSubject("Cambio de contraseña");
            email.setContent("Con esta nueva contraseña podrá ingresar a la plataforma: \n " +
                    "\t " + newPass +
                    "\n recomendamos cambie esta contraseña desde la plataforma apenas ingrese a la misma.");
            IEmailRepository.sendEmail(email);
            return true;
        }).orElse(false);
    }

    public long countUsers() {
        return repository.countUsers();
    }

}
