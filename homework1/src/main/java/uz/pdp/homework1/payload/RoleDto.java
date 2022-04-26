package uz.pdp.homework1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.homework1.entity.enums.Permission;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDto {

    /**
     *  @NotBlank  qiymatla  kevotganida odiy probel yoki vergulga  oxshagan belgilarga  ham xatolik beradi @NotNull  esa bunday qilmaydi
     */
    @NotBlank
    private String name;

    private String description;

    private List<Permission> permissionList;
}
