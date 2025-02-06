package com.backend.la.backendloveafrica.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.backend.la.backendloveafrica.security.config.filter.JwtTokenValidator;
import com.backend.la.backendloveafrica.utils.JwtUtils;

@Configuration
@EnableWebSecurity
// @EnableWebMvc
public class SecurityConfig {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private AuthenticationEntryPoint authenticationEntryPoint;

  @Autowired
  private AccessDeniedHandler accessDeniedHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(csrf -> csrf.disable())
        .httpBasic(basic -> Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
        .authorizeHttpRequests(http -> {
          http.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();

          http.requestMatchers(HttpMethod.GET, "/suppliers/simplest").hasAnyAuthority("CREATE_FABRIC", "UPDATE_FABRIC");
          http.requestMatchers(HttpMethod.GET, "/suppliers/**").hasAnyRole("ADMIN", "MANAGER");
          http.requestMatchers(HttpMethod.DELETE, "/suppliers/**").hasAuthority("DELETE_SUPPLIER");
          http.requestMatchers(HttpMethod.PUT, "/suppliers/**").hasAuthority("UPDATE_SUPPLIER");
          http.requestMatchers(HttpMethod.POST, "/suppliers/**").hasAuthority("CREATE_SUPPLIER");

          // /fabrics
          http.requestMatchers(HttpMethod.POST, "/fabrics/upload-img/{key}").authenticated();
          http.requestMatchers(HttpMethod.GET, "/fabrics/searchByString").hasRole("ADMIN");
          http.requestMatchers(HttpMethod.GET, "/fabrics/search-dto-ByString").hasAuthority("READ_FABRIC");
          http.requestMatchers(HttpMethod.GET, "/fabrics/page/{page}/{cant}").hasRole("ADMIN");
          http.requestMatchers(HttpMethod.GET, "/fabrics/page-dto/{page}/{cant}").hasAuthority("READ_FABRIC");
          http.requestMatchers(HttpMethod.GET, "/fabrics/seasons").hasAuthority("READ_NEW_COLLECTION");
          http.requestMatchers(HttpMethod.GET, "/fabrics/**").hasAuthority("READ_FABRIC");
          http.requestMatchers(HttpMethod.PUT, "/fabrics/**").hasAuthority("UPDATE_FABRIC");
          http.requestMatchers(HttpMethod.POST, "/fabrics/**").hasAuthority("CREATE_FABRIC");
          http.requestMatchers(HttpMethod.DELETE, "/fabrics/**").hasAuthority("CREATE_FABRIC");

          http.requestMatchers(HttpMethod.GET, "/apliques/**", "/argollas/**", "/correderas/**", "/elasticos/**",
              "/etiquetas/**", "/ganchos/**").hasAuthority("READ_INPUT");
          http.requestMatchers(HttpMethod.POST, "/apliques/**", "/argollas/**", "/correderas/**", "/elasticos/**",
              "/etiquetas/**", "/ganchos/**").hasAuthority("CREATE_INPUT");
          http.requestMatchers(HttpMethod.DELETE, "/apliques/**", "/argollas/**", "/correderas/**", "/elasticos/**",
              "/etiquetas/**", "/ganchos/**").hasAuthority("DELETE_INPUT");
          http.requestMatchers(HttpMethod.PUT, "/apliques/**", "/argollas/**", "/correderas/**", "/elasticos/**",
              "/etiquetas/**", "/ganchos/**").hasAuthority("UPDATE_INPUT");
          http.requestMatchers(HttpMethod.GET, "/tiposGancho").hasAnyAuthority("CREATE_INPUT", "UPDATE_INPUT");

          // /models
          http.requestMatchers(HttpMethod.GET, "/models/**").hasAuthority("READ_MODEL");
          http.requestMatchers(HttpMethod.POST, "/models/**").hasAuthority("CREATE_MODEL");
          http.requestMatchers(HttpMethod.DELETE, "/models/**").hasAuthority("DELETE_MODEL");
          http.requestMatchers(HttpMethod.PUT, "/models/**").hasAuthority("UPDATE_MODEL");

          // /tipoPrenda
          http.requestMatchers(HttpMethod.GET, "/tiposPrenda").hasAuthority("READ_MODEL");

          // /tiras
          http.requestMatchers(HttpMethod.GET, "/tiras").hasAuthority("CREATE_MODEL");

          // /productos

          http.requestMatchers(HttpMethod.GET, "/productos/product-cost/{id}").hasRole("ADMIN");
          http.requestMatchers(HttpMethod.GET, "/productos/page/card-dto/by-string").hasAuthority("READ_PRODUCT");
          http.requestMatchers(HttpMethod.GET, "/productos/page/card-dto").hasAuthority("READ_PRODUCT");
          http.requestMatchers(HttpMethod.GET, "/productos/page").hasRole("ADMIN");
          http.requestMatchers(HttpMethod.GET, "/productos/page/search").hasRole("ADMIN");
          http.requestMatchers(HttpMethod.GET, "/productos/find-by-string/{string}").hasRole("ADMIN");
          http.requestMatchers(HttpMethod.GET, "/productos/dto/{string}").hasAuthority("READ_PRODUCT");
          http.requestMatchers(HttpMethod.GET, "/productos/**").hasAuthority("READ_PRODUCT");
          http.requestMatchers(HttpMethod.POST, "/productos/**").hasAuthority("CREATE_PRODUCT");
          http.requestMatchers(HttpMethod.DELETE, "/productos/**").hasAuthority("DELETE_PRODUCT");
          http.requestMatchers(HttpMethod.PUT, "/productos/**").hasAuthority("UPDATE_PRODUCT");

          // /lotes

          http.requestMatchers(HttpMethod.PUT, "/lotes/update-state-cutter/{id}/{status}").hasRole("CUTTER");
          http.requestMatchers(HttpMethod.PUT, "/lotes/update-state-controller/{id}/{status}").hasRole("CONTROLLER");
          http.requestMatchers(HttpMethod.PUT, "/lotes/update-state-workshop/{id}/{status}").hasRole("WORKSHOP");
          http.requestMatchers(HttpMethod.PUT, "/lotes/update-state/{id}/{status}").hasRole("ADMIN");
          // http.requestMatchers(HttpMethod.PUT,
          // "/lotes/update-state-cutter/{id}/{status}").hasRole("CUTTER");
          http.requestMatchers(HttpMethod.GET, "/lotes/lotes-workshop").hasAuthority("READ_LOTE_WORKSHOP");
          http.requestMatchers(HttpMethod.PUT, "/lotes/lotes-workshop").hasAuthority("UPDATE_LOTE_WORKSHOP");
          // http.requestMatchers(HttpMethod.GET, "/lotes/**").hasRole("WORKSHOP");
          http.requestMatchers(HttpMethod.GET, "/lotes/**").hasAuthority("READ_LOTE");
          http.requestMatchers(HttpMethod.POST, "/lotes/**").hasAuthority("CREATE_LOTE");
          http.requestMatchers(HttpMethod.DELETE, "/lotes/**").hasAuthority("DELETE_LOTE");
          http.requestMatchers(HttpMethod.PUT, "/lotes/**").hasAuthority("UPDATE_LOTE");

          // /cutSpreadSheet
          // http.requestMatchers(HttpMethod.GET,
          // "/cut-spreadsheets/{id}").hasAuthority("READ_CUT_SPREADSHEET");
          http.requestMatchers(HttpMethod.GET, "/cut-spreadsheets/**").hasAuthority("READ_CUT_SPREADSHEET");
          http.requestMatchers(HttpMethod.POST, "/cut-spreadsheets/**").hasAuthority("CREATE_CUT_SPREADSHEET");
          // http.requestMatchers(HttpMethod.DELETE,
          // "/cut-spreadsheets/**").hasAuthority("DELETE_CUT_SPREADSHEET");
          http.requestMatchers(HttpMethod.PUT, "/cut-spreadsheets/**").hasAuthority("UPDATE_CUT_SPREADSHEET");

          // /preparationSpreadSheet
          http.requestMatchers(HttpMethod.GET, "/preparation-spreadsheet/dto/{id}")
              .hasAnyAuthority("READ_PREPARATION_SPREADSHEET");
          http.requestMatchers(HttpMethod.PUT, "/preparation-spreadsheet/put-img/{id}")
              .hasAnyAuthority("UPDATE_PREPARATION_SPREADSHEET");
          http.requestMatchers(HttpMethod.PUT, "/preparation-spreadsheet/**")
              .hasAnyAuthority("UPDATE_PREPARATION_SPREADSHEET");

          // /input-quantity
          http.requestMatchers(HttpMethod.GET, "/input-quantity/{id}").hasAuthority("UPDATE_PREPARATION_SPREADSHEET");

          // /workshop-spreadsheet
          http.requestMatchers(HttpMethod.GET, "/workshop-spreadsheet/**").hasAuthority("READ_WORKSHOP_SPREADSHEET");
          http.requestMatchers(HttpMethod.PUT, "/workshop-spreadsheet/**").hasAuthority("UPDATE_WORKSHOP_SPREADSHEET");

          // /control-spreadsheet
          http.requestMatchers(HttpMethod.GET, "/control-spreadsheet/**").hasAuthority("READ_CONTROL_SPREADSHEET");
          http.requestMatchers(HttpMethod.PUT, "/control-spreadsheet/**").hasAuthority("UPDATE_CONTROL_SPREADSHEET");

          // /workshops
          http.requestMatchers(HttpMethod.GET, "/workshops/**").hasAnyAuthority("READ_WORKSHOP");
          http.requestMatchers(HttpMethod.POST, "/workshops/**").hasAnyAuthority("CREATE_WORKSHOP");
          http.requestMatchers(HttpMethod.DELETE, "/workshops/**").hasAnyAuthority("DELETE_WORKSHOP");
          http.requestMatchers(HttpMethod.PUT, "/workshops/**").hasAnyAuthority("UPDATE_WORKSHOP");

          // /warehouse
          http.requestMatchers(HttpMethod.GET, "/warehouse/**").hasAuthority("READ_WAREHOUSE");
          http.requestMatchers(HttpMethod.PUT, "/warehouse/**").hasAuthority("UPDATE_WAREHOUSE");

          // /product-set

          http.requestMatchers(HttpMethod.GET, "/product-set/{id}").hasAuthority("READ_PRODUCT");
          http.requestMatchers(HttpMethod.GET, "/product-set/**").hasAuthority("READ_PRODUCT");
          http.requestMatchers(HttpMethod.POST, "/product-set/**").hasAuthority("CREATE_PRODUCT_SET");
          http.requestMatchers(HttpMethod.DELETE, "/product-set/**").hasAuthority("DELETE_PRODUCT_SET");
          http.requestMatchers(HttpMethod.PUT, "/product-set/**").hasAuthority("UPDATE_PRODUCT_SET");

          // http.requestMatchers(HttpMethod.POST, "/permissions/**").authenticated();
          http.requestMatchers(HttpMethod.POST, "/permissions").hasRole("ADMIN");
          http.requestMatchers(HttpMethod.GET, "/permissions").hasRole("ADMIN");
          // http.requestMatchers(HttpMethod.POST, "/roles").authenticated();
          http.requestMatchers(HttpMethod.POST, "/roles").hasRole("ADMIN");
          http.requestMatchers(HttpMethod.GET, "/roles/**").hasRole("ADMIN");
          http.requestMatchers(HttpMethod.PUT, "/roles/**").hasRole("ADMIN");
          // http.requestMatchers(HttpMethod.GET, "/roles/**").hasRole("ADMIN");
          http.requestMatchers(HttpMethod.PUT, "/users/change-pass").authenticated();
          http.requestMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN");
          http.requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN");
          // http.requestMatchers(HttpMethod.PUT, "/users/**").authenticated();
          http.requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN");
          // http.requestMatchers(HttpMethod.GET, "/permissions/{id}").permitAll();
          http.anyRequest().denyAll();
        })

        .formLogin(login -> login.permitAll())
        .exceptionHandling(exceptionConfig -> {
          exceptionConfig.authenticationEntryPoint(authenticationEntryPoint);
          exceptionConfig.accessDeniedHandler(accessDeniedHandler);
          // exceptionConfig.JwtTokenExpiredException(authenticationEntryPoint);
        })
        // .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .build();
  }

  // @Bean
  // CorsConfigurationSource corsConfigurationSource() {

  // CorsConfiguration config = new CorsConfiguration();
  // config.setAllowedOrigins(Arrays.asList("http://la-app.s3-website-sa-east-1.amazonaws.com"));
  // config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
  // config.setAllowedMethods(Arrays.asList("*"));
  // config.setAllowCredentials(true);

  // UrlBasedCorsConfigurationSource source = new
  // UrlBasedCorsConfigurationSource();
  // source.registerCorsConfiguration("/**", config);
  // return source;
  // }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todas las rutas
            .allowedOrigins("https://www.myback.com.ar", "https://myback.com.ar", "http://localhost:5173") // Orígenes permitidos
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
            .allowedHeaders("*") // Headers permitidos
            .allowCredentials(true); // Permite el uso de cookies
      }
    };
  }

  // @Bean
  // FilterRegistrationBean<CorsFilter> corsFilter() {
  // FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new
  // CorsFilter(corsConfigurationSource()));
  // bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
  // return bean;
  // }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(userDetailsService);
    return provider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
