package notes.severstal.service;

import notes.severstal.dto.MapperUsers;
import notes.severstal.dto.UserDto;
import notes.severstal.dto.UserRegistrationDto;
import notes.severstal.handlers.AlreadyExistEmailException;
import notes.severstal.handlers.NotFoundException;
import notes.severstal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class AdminUsersService {

    private final UserRepository userRepository;

    public AdminUsersService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Collection<UserDto> getUsers(Collection<Long> ids, Long from, Long size) {
        Pageable page = PageRequest.of(Math.toIntExact(from / size), Math.toIntExact(size));
        if (ids != null) {
            return MapperUsers.userToDtoColl(userRepository.findAllById(ids));
        }
        return MapperUsers.userToDtoColl(userRepository.findAll(page).toList());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserDto postUser(UserRegistrationDto userDto) {
        try {
            var phs = new PasswordHashAndSalt();
            userDto.setPassword(phs.hashedAndSalt(userDto.getPassword()));
            return MapperUsers.userToDto(userRepository.save(MapperUsers.dtoRegToUser(userDto)));
        } catch (Exception e) {
            throw new AlreadyExistEmailException("Email is used");
        }

    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(String.format("User with id=%s does not exist", userId));
        }
        userRepository.deleteById(userId);
    }
}
