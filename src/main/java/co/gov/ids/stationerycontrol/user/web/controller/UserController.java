package co.gov.ids.stationerycontrol.user.web.controller;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.gov.ids.stationerycontrol.user.domain.dto.User;
import co.gov.ids.stationerycontrol.user.domain.service.UserService;

@RestController
@Api(tags = "user")
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Post user", notes = "Service for create a new user")
    @ApiResponse(code = 201, message = "User was created correctly")
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);
    }

    @PutMapping("/{username}")
    @ApiOperation(value = "Put user", notes = "Service for update the info of an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was updated correctly"),
            @ApiResponse(code = 404, message = "User was not found")
    })
    public ResponseEntity<User> update(@PathVariable("username") String username, @RequestBody User user) {
        if (service.findByUsername(username).isPresent()) {
            return new ResponseEntity<>(service.update(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{username}/disable")
    @ApiOperation(value = "Disable user", notes = "Service for disable an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was disabled"),
            @ApiResponse(code = 404, message = "User was not found")
    })
    public ResponseEntity disable(@PathVariable("username") String username) {
        if (service.disable(username)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{username}")
    @ApiOperation(value = "Get user by username", notes = "Service for find an user by username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was found"),
            @ApiResponse(code = 404, message = "User was not found")
    })
    public ResponseEntity<User> findByUsername(@PathVariable("username") String username) {
        return service.findByUsername(username).map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all/{page}")
    @ApiOperation(value = "Get all users", notes = "Service for list all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Users listed correctly"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    public ResponseEntity<List<User>> findAll(@PathVariable("page") int page) {
        return service.findAll(page).map(users -> {
            System.out.println(users);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/identificationCard/{identificationCard}/{page}")
    @ApiOperation(value = "Get users by identificationCard", notes = "Service for list users by a specific id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User listed correctly"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    public ResponseEntity<List<User>> findByIdentificationCard(
            @PathVariable("page") int page,
            @PathVariable("identificationCard") String identificationCard) {
        return service.findByIdentificationCard(identificationCard, page)
                .map(users -> new ResponseEntity<>(users, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}/{page}")
    @ApiOperation(value = "Get users by name", notes = "Service for list users by a specific name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User listed correctly"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    public ResponseEntity<List<User>> findByName(@PathVariable("page") int page, @PathVariable("name") String name) {
        return service.findByName(name, page).map(users -> new ResponseEntity<>(users, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{username}/change-password")
    @ApiOperation(value = "Change Password", notes = "Service for change the password of an user by username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Password was changed correctly"),
            @ApiResponse(code = 400, message = "Invalid Request")
    })
    public ResponseEntity changePassword(@PathVariable("id") String id, String oldPass, String newPass) {
        if (service.changePassword(id, oldPass, newPass)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{username}/recover-password")
    @ApiOperation(value = "Recover Password", notes = "Service for send a mail to recover the password of an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Password was changed correctly and email was sent"),
            @ApiResponse(code = 400, message = "Invalid Request")
    })
    public ResponseEntity recoverPassword(@PathVariable("username") String username) {
        if (service.recoverPassword(username)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    @ApiOperation(value = "Get count", notes = "Service for get how many users was be registered.")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(service.countUsers(), HttpStatus.OK);
    }

}
