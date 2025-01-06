package com.example.PandaCoffee.config;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Đánh dấu lớp này là một lớp cấu hình cho Spring Context.
@EnableWebSecurity // Kích hoạt tính năng bảo mật web của Spring Security.
@RequiredArgsConstructor // Lombok tự động tạo constructor có tham số cho tất cả các trường final.
public class SecurityConfig {

//    @Autowired
//    private  OAuthAuthenicationSuccessHandler handler;



//    @Bean
//    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
//        return http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/**")
//                        .permitAll() // Cho phép truy cập không cần xác thực.
//                        //.requestMatchers("/movies/add", "/movies/edit/**", "/movies/delete/**")
//                        //.hasAnyAuthority("ADMIN") // Chỉ cho phép ADMIN truy cập.
//                        //.requestMatchers("/api/**")
//                        //.permitAll() // API mở cho mọi người dùng.
//                        .anyRequest().authenticated() // Bất kỳ yêu cầu nào khác cần xác thực.
//                )
////                .logout(logout -> logout
////                        .logoutUrl("/logout")
////                        .logoutSuccessUrl("/login") // Trang chuyển hướng sau khi đăng xuất.
////                        .deleteCookies("JSESSIONID") // Xóa cookie.
////                        .invalidateHttpSession(true) // Hủy phiên làm việc.
////                        .clearAuthentication(true) // Xóa xác thực.
////                        .permitAll()
////                )
////                .formLogin(formLogin -> formLogin
////                        .loginPage("/login") // Trang đăng nhập.
////                        .loginProcessingUrl("/login") // URL xử lý đăng nhập.
////                        .defaultSuccessUrl("/") // Trang sau đăng nhập thành công.
////                        .failureUrl("/login?error") // Trang đăng nhập thất bại.
////                        .permitAll()
////                )
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .accessDeniedPage("/403") // Trang báo lỗi khi truy cập không được phép.
//                )
//                .sessionManagement(sessionManagement -> sessionManagement
//                        .maximumSessions(1) // Giới hạn số phiên đăng nhập.
//                        .expiredUrl("/login") // Trang khi phiên hết hạn.
//                )
//                .httpBasic(httpBasic -> httpBasic
//                        .realmName("hutech") // Tên miền cho xác thực cơ bản.
//                )
//                .build(); // Xây dựng và trả về chuỗi lọc bảo mật.
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Thay thế cách cấu hình cũ
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }


}
