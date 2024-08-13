package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      int series_1 = 77893;
      int series_2 = 6789;
      int series_3 = 78904;
      int series_5 = 5467;

      carService.add(new Car("Skyline", series_1));
      carService.add(new Car("Supra",  series_2));
      carService.add(new Car("Mark ||",  series_3));
      carService.add(new Car("Chaser",  series_5));

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"), series_1);
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"), series_2);
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"), series_3);
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"), series_5);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = "+ user.getEmail());
         System.out.println("Car: " + user.getCar().getModel() + " " + user.getCar().getSeries());
         System.out.println();
      }

      context.close();
   }
}
