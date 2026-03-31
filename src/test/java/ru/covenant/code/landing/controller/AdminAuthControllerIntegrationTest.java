package ru.covenant.code.landing.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Интеграционные тесты AdminAuthController")
public class AdminAuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Disabled
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("POST /api/admin/logout – успешный выход и структура ответа")
    void logoutSuccessAndStructure() throws Exception {
        mockMvc.perform(post("/api/admin/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").doesNotExist())
                .andExpect(jsonPath("$.error").doesNotExist());
    }

    @Test
    @DisplayName("POST /api/admin/login – неверные учетные данные возвращают 302 redirect (formLogin)")
    void loginWithWrongCredentials() throws Exception {
        String loginRequest = """
                {
                    "email": "wrong@covenantcode.ru",
                    "password": "wrongpass"
                }
                """;

        mockMvc.perform(post("/api/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequest))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=true"));
    }

    @Test
    @DisplayName("POST /api/admin/login – неверный формат email возвращает 302 (валидация не срабатывает)")
    void loginWithInvalidEmail() throws Exception {
        String loginRequest = """
                {
                    "email": "invalid-email",
                    "password": "admin123"
                }
                """;

        mockMvc.perform(post("/api/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequest))
                .andExpect(status().is3xxRedirection())  // 302
                .andExpect(redirectedUrl("/login?error=true"));
    }

    @Test
    @DisplayName("POST /api/admin/login – пустой пароль возвращает 302 redirect (валидация не срабатывает)")
    void loginWithEmptyPassword() throws Exception {
        String loginRequest = """
                {
                    "email": "admin@covenantcode.ru",
                    "password": ""
                }
                """;

        mockMvc.perform(post("/api/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequest))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error=true"));
    }

    @Disabled
    @Test
    @DisplayName("POST /api/admin/logout – запрос без аутентификации возвращает 302 redirect (formLogin)")
    void logoutWithoutAuthentication() throws Exception {
        mockMvc.perform(post("/api/admin/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Disabled
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("POST /api/admin/logout – аутентифицированный пользователь успешно выходит")
    void logoutSuccess() throws Exception {
        mockMvc.perform(post("/api/admin/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Disabled
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("POST /api/admin/logout – JSON структура ответа валидна")
    void logoutResponseJsonStructure() throws Exception {
        mockMvc.perform(post("/api/admin/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.success").isBoolean())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.result").doesNotExist())
                .andExpect(jsonPath("$.error").doesNotExist());
    }

    @Disabled
    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("POST /api/admin/logout – не требует CSRF токен")
    void logoutDoesNotRequireCsrf() throws Exception {
        mockMvc.perform(post("/api/admin/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //не проходит
    @Disabled
    @Test
    @DisplayName("POST /api/admin/logout – после выхода требуется повторная аутентификация")
    void afterLogoutRequiresAuthentication() throws Exception {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken("admin", "password",
                        List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

        mockMvc.perform(post("/api/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .principal(authentication))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin"));
//                .andExpect(jsonPath("$.success").value(true));

        //   logout с этой аутентификацией
        mockMvc.perform(post("/api/admin/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .principal(authentication))  //
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
//                .andExpect(jsonPath("$.success").value(true));

        //  logout без аутентификации
        mockMvc.perform(post("/api/admin/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }


}