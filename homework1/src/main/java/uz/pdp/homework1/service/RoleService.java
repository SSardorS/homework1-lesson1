package uz.pdp.homework1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.homework1.aop.CheckPermissionForPostAndCommet;
import uz.pdp.homework1.entity.Role;
import uz.pdp.homework1.payload.ApiResponse;
import uz.pdp.homework1.payload.RoleDto;
import uz.pdp.homework1.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @CheckPermissionForPostAndCommet(permission = "ADD_ROLE")
    public ApiResponse addRole(RoleDto roleDto) {
        if (roleRepository.existsByName(roleDto.getName())) {
            return new ApiResponse("bunday lavozim bor", false);
        }
        Role role = new Role(roleDto.getName(), roleDto.getPermissionList(), roleDto.getDescription());
        roleRepository.save(role);

        return new ApiResponse("saved", true);
    }

    @CheckPermissionForPostAndCommet(permission = "EDIT_ROLE")
    public ApiResponse editRole(RoleDto roleDto, Long id) {
        Optional<Role> byId = roleRepository.findById(id);
        boolean existsRole = roleRepository.existsByName(roleDto.getName());

        if (!byId.isPresent() && existsRole)
            return new ApiResponse("This role id is not found or this rolNmae is already exists",false);

        Role role = byId.get();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setRoleList(roleDto.getPermissionList());
        roleRepository.save(role);
        return new ApiResponse("sucessfullEdited",true);
    }
}
