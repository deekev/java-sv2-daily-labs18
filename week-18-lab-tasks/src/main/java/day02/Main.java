package day02;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/bookstore?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("diuska");
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot reach database!", sqle);
        }

        Flyway flyway = Flyway.configure().locations("db/migration/bookstore").dataSource(dataSource).load();
//        flyway.clean();
        flyway.migrate();

        BooksRepository booksRepository = new BooksRepository(dataSource);
//        booksRepository.insertBook("Fekete István", "Vuk", 3400, 10);
//        booksRepository.insertBook("Fekete István", "Téli berek", 3600, 5);
//        booksRepository.insertBook("Fekete Péter", "Kártyatrükkök", 1200, 2);
        booksRepository.updatePieces(1, 30);
        System.out.println(booksRepository.findBooksByAuthor("Fekete"));


    }
}
