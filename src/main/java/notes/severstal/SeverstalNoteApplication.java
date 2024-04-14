package notes.severstal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"notes.severstal"})
public class SeverstalNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeverstalNoteApplication.class, args);
	}

}
