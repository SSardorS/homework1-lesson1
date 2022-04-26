package uz.pdp.homework1.aop;

import java.lang.annotation.*;

/**
 * @Retention(RetentionPolicy.RUNTIME) bu  anantatsiya programma run bolganda ishlashini tamilidi
 * @Documented  anantantiya ochish uchun ishlatilinadi
 * @Target(ElementType.METHOD)  yozgan anatatsiya qayerda ishlatilishini korsatadi
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission {
    String role(); //-> bu anatatsiya ichiga kiruvchi  parametr


}
