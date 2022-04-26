package uz.pdp.homework1.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.homework1.entity.User;
import uz.pdp.homework1.exeptions.ForbiddenException;

/**
 * @Aspect yozgan anantasiyamizni natijasini korsatish uchun  ishlatilinadi
 */

@Component
@Aspect
public class CheckPermissionExecutor {

    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.role())) {
                exist = true;
                break;
            }
        }

        if (!exist)
            throw new ForbiddenException(checkPermission.role(), "Ruhsat yoq");
    }

    @Before(value = "@annotation(checkPermissionForPostAndCommet)")
    public void CheckPermissionForPostAndCommetMethod(CheckPermissionForPostAndCommet checkPermissionForPostAndCommet) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist = false;

        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermissionForPostAndCommet.permission())) {
                exist = true;
                break;
            }
        }
        if (!exist)
            throw  new ForbiddenException(checkPermissionForPostAndCommet.permission(), "Ruhsat yoq");
    }

}
