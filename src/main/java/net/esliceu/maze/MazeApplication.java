package net.esliceu.maze;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class MazeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MazeApplication.class, args);
	}
}
