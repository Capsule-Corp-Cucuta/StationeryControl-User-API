package co.gov.ids.stationerycontrol.user.infraestructure.resources;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.gov.ids.stationerycontrol.user.domain.User;
import co.gov.ids.stationerycontrol.user.application.services.IUserService;

/**
 * Class to represent a RESTController - User Resource.
 *
 * @author Sergio Rodriguez
 * @version 0.0.3
 * @since 2020
 */
@RestController
@Api(tags = "user")
@RequestMapping("/api/user")
public class UserResource {

    private final IUserService service;

    public UserResource(IUserService service) {
        this.service = service;
    }

    /**
     * POST Method for create a new User.
     *
     * @param user User to will be persist.
     * @return User created, 201 code.
     */
    @PostMapping
    @ApiOperation(value = "Create a user", notes = "Service for create a new user")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "User was created correctly"),
            @ApiResponse(code = 400, message = "Invalid Request")})
    public ResponseEntity<User> create(@RequestBody User user) {
        return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);
    }

    /**
     * PUT Method that update an User.
     *
     * @param identification number of the document that identified the user.
     * @param user           User to will be updated.
     * @return User updated, 200 code.
     */
    @PutMapping("/{identification}")
    @ApiOperation(value = "Update a user", notes = "Service for update the info of an user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User was updated correctly"),
            @ApiResponse(code = 404, message = "User was not found")})
    public ResponseEntity<User> update(@PathVariable("identification") String identification, @RequestBody User user) {
        User response = service.update(identification, user);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * DELETE Method for delete an User.
     *
     * @param identification number of the document that identified the user.
     * @return 200 code.
     */
    @DeleteMapping("/{identification}")
    @ApiOperation(value = "Delete user", notes = "Service for delete an user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User was deleted"),
            @ApiResponse(code = 404, message = "User was not found")})
    public ResponseEntity delete(@PathVariable("identification") String identification) {
        service.delete(identification);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * GET Method for list all users on pages of 20 items.
     *
     * @param page number of page to list.
     * @return List of 20 users.
     */
    @GetMapping("/all/{page}")
    @ApiOperation(value = "List all users", notes = "Service for list all users")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Users listed correctly"),
            @ApiResponse(code = 404, message = "Users not found")})
    public ResponseEntity<List<User>> findAll(@PathVariable("page") int page) {
        return ResponseEntity.ok(service.findAll(page));
    }

    /**
     * GET Method for find an User by identification card number.
     *
     * @param identification number of the document that identified the user.
     * @return User identified by param.
     */
    @GetMapping("/{identification}")
    @ApiOperation(value = "Find a user by identification", notes = "Service for find an user by identification card")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User was found"),
            @ApiResponse(code = 404, message = "User was not found")})
    public ResponseEntity<User> findByIdentification(@PathVariable("identification") String identification) {
        User response = service.findByIdentificationCard(identification);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * GET Method for find Users by name.
     *
     * @param page number of page to list.
     * @param name name of User to find.
     * @return List of 20 Users or less named like param.
     */
    @GetMapping("/name/{name}/{page}")
    @ApiOperation(value = "List users by name", notes = "Service for list users by a specific name")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User listed correctly"),
            @ApiResponse(code = 404, message = "Users not found")})
    public ResponseEntity<List<User>> findByName(@PathVariable("page") int page, @PathVariable("name") String name) {
        return ResponseEntity.ok(service.findByName(name, page));
    }

    /**
     * PUT Method for update the password of an User.
     *
     * @param identification number of the document that identified the user.
     * @param oldPass        String that represents the actual password of the User.
     * @param newPass        String that represents the new password of the User.
     * @return 200 code.
     */
    @PostMapping("/{identification}/change-password")
    @ApiOperation(value = "Change Password", notes = "Service for change the password of an user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Password was changed correctly"),
            @ApiResponse(code = 400, message = "Invalid Request")})
    public ResponseEntity changePassword(@PathVariable("identification") String identification, String oldPass, String newPass) {
        service.changePassword(identification, oldPass, newPass);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * PUT Method for request for a mail of recover password.
     *
     * @param identification number of the document that identified the user.
     * @return 200 code.
     */
    @PutMapping("/{identification}/recover-password")
    @ApiOperation(value = "Recover Password", notes = "Service for send a mail to recover the password of an user")
    public ResponseEntity recoverPassword(@PathVariable("identification") String identification) {
        service.recoverPassword(identification);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * GET Method for count users registered on the system.
     *
     * @return number of users registered.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.countUsers());
    }

}
