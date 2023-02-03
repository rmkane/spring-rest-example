package org.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import java.math.BigDecimal;
import java.util.Map;
import org.example.domain.entity.Book;
import org.example.domain.repository.BookRepository;
import org.springdoc.core.SpringDocUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@OpenAPIDefinition(
    info =
        @Info(
            title = "Library API",
            version = "2.0",
            description = "A Spring Boot REST API example"))
public class StartBookApplication {

  static {
    // Allow Map<String, Object> to be used as a @RequestBody
    SpringDocUtils.getConfig().removeRequestWrapperToIgnore(Map.class);
  }

  // Start everything
  public static void main(String[] args) {
    SpringApplication.run(StartBookApplication.class, args);
  }

  // Run this only on profile 'demo', avoid run this in test
  @Profile("demo")
  @Bean
  CommandLineRunner initDatabase(BookRepository bookRepository) {
    return args -> {
      bookRepository.save(
          new Book("A Guide to the Bodhisattva Way of Life", "Santideva", new BigDecimal("15.41")));
      bookRepository.save(
          new Book("The Life-Changing Magic of Tidying Up", "Marie Kondo", new BigDecimal("9.69")));
      bookRepository.save(
          new Book(
              "Refactoring: Improving the Design of Existing Code",
              "Martin Fowler",
              new BigDecimal("47.99")));
    };
  }
}
