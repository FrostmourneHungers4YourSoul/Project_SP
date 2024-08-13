package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   private final UserDao userDao;
   private final CarService carService;

   @Autowired
   public UserServiceImp(UserDao userDao, CarService carService) {
      this.userDao = userDao;
       this.carService = carService;
   }

   @Transactional
   @Override
   public void add(User user, int series) {
      Car car = carService.findCarBySeries(series);
      user.setCar(car);
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      return userDao.findUserByCar(model, series);
   }
}