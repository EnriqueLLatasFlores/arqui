package upc.edu.oneup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneupApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneupApplication.class, args);
	}

	  @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permite todas las rutas
                        .allowedOrigins("*", "http://localhost:5173") // Permite todos los orígenes
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite estos métodos
                        .allowedHeaders("*"); // Permite todos los encabezados
            }



        };

    }
}
