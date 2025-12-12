package com.project.course_work;

import com.project.course_work.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionTest implements CommandLineRunner {

    private final FacilityRepository repository;

    @Value("${server.port:8080}")
    private String port;

    public DatabaseConnectionTest(FacilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n----------------------------------------------------------");
        System.out.println("СЕРВЕР ЗАПУЩЕНО УСПІШНО!");
        System.out.println("----------------------------------------------------------");

        try {
            long count = repository.count();
            System.out.println("З'єднання з базою встановлено.");
            System.out.println("Кількість об'єктів (Facilities): " + count);
        } catch (Exception e) {
            System.out.println("ПОМИЛКА з'єднання з БД: " + e.getMessage());
        }

        String baseUrl = "http://localhost:" + port + "/api";

        System.out.println("\nНАТИСНІТЬ НА ПОСИЛАННЯ, ЩОБ ПОБАЧИТИ JSON:");
        System.out.println("Об'єкти:      " + baseUrl + "/facilities");
        System.out.println("Аварії:       " + baseUrl + "/emergencies");
        System.out.println("Заходи:       " + baseUrl + "/countermeasures");
        System.out.println("Дози:         " + baseUrl + "/dose-measures");
        System.out.println("Вплив:        " + baseUrl + "/impacts");
        System.out.println("Витрати:      " + baseUrl + "/costs");

        System.out.println("----------------------------------------------------------\n");
    }
}