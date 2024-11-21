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
                registry.addMapping("/**")  // Permite todas las rutas
                        .allowedOrigins("http://localhost:5173", "https://charming-purpose-production.up.railway.app") // Orígenes permitidos
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                        .allowedHeaders("*") // Encabezados permitidos
                        .allowedCredentials(true); // Permite credenciales como cookies y cabeceras de autenticación
            }
        };
    }
}
