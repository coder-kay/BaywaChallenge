package de.bnd.coding.sample.demo.service;

import de.bnd.coding.sample.demo.data.UserEntity;
import de.bnd.coding.sample.demo.data.UserRepository;
import de.bnd.coding.sample.demo.view.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Is a special variant of the @Component annotation which tells Spring to create this as a singleton
@Service
public class UserService {

    // Here we want to use the repository to store data
    private final UserRepository userRepository;

    // Autowired will fetch the correct dependencies from all present singletons.
    // See https://de.wikipedia.org/wiki/Dependency_Injection
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // For a more detailed description of the Service layer, check the Readme

    public UserDto getUserById( Integer id ) {
        // getById is a method inherited from the JpaRepository.
        UserEntity userEntity = userRepository.getById(id);
        return new UserDto(userEntity.getUserName(), userEntity.getUserPassword() ,userEntity.getFirstName(), userEntity.getLastName(), userEntity.getZipCode());
    }

    public List<UserDto> getUserByLastName( String lastName ) {
        List<UserEntity> userEntities = userRepository.findByLastName(lastName);
        // This is a so called Lambda Expression - easy to use for operations on Lists
        return userEntities
                .stream()
                .map( x -> new UserDto(x.getUserName(), x.getUserPassword(), x.getFirstName(), x.getLastName(), x.getZipCode()))
                .collect(Collectors.toList());
    }

    public UserDto getUserByUserName( String userName ) {
        UserEntity userEntity = userRepository.findByUserName(userName);
        // This is a so called Lambda Expression - easy to use for operations on Lists
        return new UserDto(userEntity.getUserName(), userEntity.getUserPassword(), userEntity.getFirstName(), userEntity.getLastName(), userEntity. getZipCode());
    }


    public void saveAll(List<UserDto> userDtoList) {
        // saveAll is a method inherited from the JpaRepository.

        userRepository.saveAll(
                userDtoList
                        .stream()
                        // Notice the difference in field access here:
                        // a standard getter reads getLastName, however, here we access a Record
                        //  and the automatically generated methods for its fields.
                        .map(x -> new UserEntity(x.userName(), x.userPassword(), x.firstName(),x.lastName())
                        )
                        .collect(Collectors.toList())
        );
    }

    public void updateUserZipCode(UserDto userDto) {
        UserEntity oldUserEntity = userRepository.findByUserName(userDto.userName());
        oldUserEntity.setZipCode(userDto.zipCode());
        userRepository.save(oldUserEntity);
    }
}
