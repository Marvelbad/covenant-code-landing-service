package ru.covenant.code.landing.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Тесты SecurityConfig")
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SecurityFilterChain securityFilterChain;

    @Test
    @DisplayName("SecurityFilterChain должен содержать CSRF фильтр")
    void securityFilterChain_ShouldHaveCsrfFilter() {
        assertThat(securityFilterChain).isNotNull();

        boolean hasCsrfFilter = securityFilterChain.getFilters().stream()
                .anyMatch(filter -> filter instanceof CsrfFilter);

        assertThat(hasCsrfFilter).isTrue();
    }

    @Test
    @DisplayName("Endpoint /api/admin/logout должен требовать аутентификации")
    void logoutEndpoint_ShouldRequireAuthentication() throws Exception {
        mockMvc.perform(post("/api/admin/logout")
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Endpoint /api/admin/logout должен быть доступен для аутентифицированных пользователей")
    void logoutEndpoint_ShouldBeAccessibleForAuthenticatedUsers() throws Exception {
        mockMvc.perform(post("/api/admin/logout")
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Endpoint /api/admin/logout должен быть исключен из CSRF защиты (не требует CSRF токен)")
    void logoutEndpoint_ShouldNotRequireCsrfToken() throws Exception {
        mockMvc.perform(post("/api/admin/logout")
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("CSRF защита должна быть включена для других эндпоинтов")
    @WithMockUser(roles = "ADMIN")
    void csrfProtection_ShouldBeEnabledForOtherEndpoints() throws Exception {
        mockMvc.perform(post("/api/admin/some-other-endpoint")
                        .contentType("application/json"))
                .andExpect(status().isForbidden());
    }
}