package com.cesararaujostudio.api.controller;

import com.cesararaujostudio.api.dto.res.UserResDTO;
import com.cesararaujostudio.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    private final UserService profileService;

    @PreAuthorize("isAuthenticated()")
//    @CrossOrigin(origins = "http://localhost:3000")
    @ApiOperation(value = "Returns Spring Security authenticated user", response = UserResDTO.class)
    @GetMapping("/me")
    public UserResDTO me() {
        return UserResDTO.of(this.profileService.findAuthenticatedUser());
    }

    @PreAuthorize("isAuthenticated()")
//    @CrossOrigin(origins = "http://localhost:3000")
    @ApiOperation(value = "Remove access token and refresh token from token store", response = Void.class)
    @DeleteMapping(value = "/logout")
    public void logout() {
        this.profileService.logout();
    }

}
