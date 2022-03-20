//package lk.eyepax.demo.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import lk.eyepax.demo.entity.CustomUser;
//import lk.eyepax.demo.entity.UserEntity;
//import lk.eyepax.demo.repository.OAuthDao;
//
//@Service
//public class CustomDetailsService implements UserDetailsService {
//   @Autowired
//   OAuthDao oauthDao;
//
//   @Override
//   public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
//      UserEntity userEntity = null;
//      try {
//         userEntity = oauthDao.getUserDetails(username);
//         CustomUser customUser = new CustomUser(userEntity);
//         return customUser;
//      } catch (Exception e) {
//         e.printStackTrace();
//         throw new UsernameNotFoundException("User " + username + " was not found in the database");
//      }
//   }
//} 