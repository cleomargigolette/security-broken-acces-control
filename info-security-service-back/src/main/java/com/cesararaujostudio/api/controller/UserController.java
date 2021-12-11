package com.cesararaujostudio.api.controller;

import com.cesararaujostudio.api.bases.PageReq;
import com.cesararaujostudio.api.bases.PageRes;
import com.cesararaujostudio.api.dto.req.UserReqDTO;
import com.cesararaujostudio.api.dto.res.UserResDTO;
import com.cesararaujostudio.api.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService service;

    @PreAuthorize("hasAuthority('ADMIN')")
//    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "FindAll with page users in dataBase", response = PageRes.class)
    @GetMapping
    public PageRes<UserResDTO> index(PageReq query) {
        return this.service.findAll(query);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
//    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Create new User", response = UserResDTO.class)
    @PostMapping
    public UserResDTO store(@Valid @RequestBody UserReqDTO dto) {
        return this.service.create(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
//    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "FindById user", response = UserResDTO.class)
    @GetMapping("/{id}")
    public UserResDTO show(@PathVariable("id") Long id) {
        return this.service.findById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
//    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Update in user", response = UserResDTO.class)
    @PutMapping("/{id}")
    public UserResDTO update(@PathVariable("id") Long id, @Valid @RequestBody UserReqDTO dto) {
        return this.service.update(id, dto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
//    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Logical Exclusion in dataBase", response = void.class)
    @DeleteMapping("/{id}")
    public void logicalExclusion(@PathVariable("id") Long id) {
        this.service.logicalExclusion(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
//    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Restore user deleted in dataBase", response = void.class)
    @PutMapping("/restore/{id}")
    public void restoreDeleted(@PathVariable("id") Long id) {
        this.service.restoreDeleted(id);
    }

}
