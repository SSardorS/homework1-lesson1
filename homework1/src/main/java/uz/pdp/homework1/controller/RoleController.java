package uz.pdp.homework1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.homework1.aop.CheckPermission;
import uz.pdp.homework1.payload.ApiResponse;
import uz.pdp.homework1.payload.RoleDto;

import uz.pdp.homework1.service.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto roleDto) {
        ApiResponse apiResponse =roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }

    @CheckPermission(role = "EDIT_ROLE")
    @PutMapping("/{id}")
    public HttpEntity<?> editRole(@Valid @RequestBody RoleDto roleDto, @PathVariable Long id) {
        ApiResponse apiResponse =roleService.editRole(roleDto, id);
        return ResponseEntity.status(apiResponse.isSucces()?200:409).body(apiResponse);
    }
}
