package in.co.najah.najahhr;

import in.co.najah.najahhr.entity.audit.AuditorAwareImpl;
import in.co.najah.najahhr.util.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware",dateTimeProviderRef = "dateTimeProvider")
public class NajahhrApplication {

	private final Path rootPath = Paths.get(Constants.ROOT_DIRC);

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(NajahhrApplication.class, args);
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
