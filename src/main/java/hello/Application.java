package hello;

import hello.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

//    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired
    CustomerRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        Arrays.asList("Alice Smith", "Bob Smith", "Josh Andrew", "Honda Paro")
                .stream()
                .map(name -> {
                    String[] names = name.split(" ");
                    return new Customer(names[0],names[1]);
                })
                .forEach(customer -> repository.save(customer));

        log.info("Customer found with findAll():");
        log.info("-----------------");
        for( Customer customer : repository.findAll()) {
            log.info(customer.toString());
        }

        log.info("Customer found with firstName:");
        log.info("-----------------");
        log.info(repository.findByFirstName("Alice").toString());

        log.info("Customer found with lastName:");
        log.info("-----------------");
        repository.findByLastName("Smith").stream()
                .forEach(customer -> log.info(customer.toString()));


//        log.info("creating tables");
//
//        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
//        jdbcTemplate.execute("CREATE TABLE customers(" +
//                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
//
//        log.info(Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").toString());
//        log.info(Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream().map(name -> name.split(" ")).toString());
//
//
//        List<Object[]> splitNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
//                .map(name -> name.split(" "))
//                .collect(Collectors.toList());
//        log.info(splitNames.toString());
//
//        splitNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));
//
//        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES(?,?)", splitNames);
//
//        log.info("Querying for customer records where first_name = Josh:");
//
//        jdbcTemplate.query("SELECT id, first_name, last_name FROM customers WHERE first_name =?", new Object[]{ "Josh"},
//                (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
//        ).forEach(customer -> log.info(customer.toString()));
    }
}
