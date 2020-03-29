package in.co.najah.najahhr;

import in.co.najah.najahhr.entity.audit.AuditorAwareImpl;
import in.co.najah.najahhr.entity.audit.LocalDateTimeProvider;
import in.co.najah.najahhr.util.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware",dateTimeProviderRef = "dateTimeProvider",modifyOnCreate = false)
public class NajahhrApplication {

	private final Path rootPath = Paths.get(Constants.ROOT_DIRC);

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

	@Bean
	public DateTimeProvider dateTimeProvider(){return new LocalDateTimeProvider();
	}

	public static void main(String[] args) {
		SpringApplication.run(NajahhrApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*");
			}
		};
	}

	@PostConstruct
	public void init() {
		try {
			if(!Files.isDirectory(rootPath))
			{
				Files.createDirectory(rootPath);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
