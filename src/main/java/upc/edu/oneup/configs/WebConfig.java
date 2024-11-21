package upc.edu.oneup.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Asegúrate de que solo los orígenes necesarios estén permitidos
                registry.addMapping("/**")
                        .allowedOrigins(
                                "https://smartupc.up.railway.app" // Origen de producción
                                "http://smartupc.up.railway.app"
                                // Agregar más orígenes si es necesario (por ejemplo, en desarrollo)
                                // "http://localhost:4200" // Solo si lo necesitas en desarrollo
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                        .allowedHeaders("Content-Type", "Authorization") // Solo los encabezados necesarios
                        .allowCredentials(true); // Permitir credenciales
            }
        };
    }
}
