package com.br.com.nava.focus.domain.service.user;

import com.br.com.nava.focus.adapter.dto.user.CreateUserRequestDto;
import com.br.com.nava.focus.adapter.dto.user.CreateUserResponseDto;
import com.br.com.nava.focus.adapter.dto.user.UserResponseDto;
import com.br.com.nava.focus.domain.model.Address;
import com.br.com.nava.focus.domain.model.Role;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.model.User;
import com.br.com.nava.focus.domain.repository.RoleRepository;
import com.br.com.nava.focus.domain.repository.UserRepository;
import com.br.com.nava.focus.domain.service.address.AddressService;
import com.br.com.nava.focus.domain.service.store.StoreService;
import com.br.com.nava.focus.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StoreService storeService;
    private final AddressService addressService;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, StoreService storeService, AddressService addressService, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.storeService = storeService;
        this.addressService = addressService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public CreateUserResponseDto createUser(CreateUserRequestDto dto, UUID storeId) {
        try{
            log.info("Encontrar um usuário pelo email: {}", dto.getEmail());

            var existsByEmail = userRepository.existsByEmail(dto.getEmail());

            User userEntity = userMapper.createUserRequestDtoToUserEntity(dto);

            if (existsByEmail){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuário com esse email: " + dto.getEmail());
            }

            // Buscar Store
            log.info("Buscar store");
            var storeDto = storeService.getStoreById(storeId);
            Store storeEntity = storeDto.toEntity(userEntity, storeId);

            // Buscar Role
            log.info("Buscar role");
            Role role = roleRepository.findByName(Role.Values.EMPLOYEE.name());

            // Buscar e Salvar Endereço
            log.info("Buscar e salvar endereço");
            var addressResponse = addressService.getAddressByCep(dto.getAddress().getCep());
            Address addressSaved = addressService.saveAddress(addressResponse.toRequestDto(), userEntity);

            userEntity.setAddress(addressSaved);
            userEntity.setRoles(Set.of(role));
            userEntity.setStore(storeEntity);
            userEntity.setCreationAt(Instant.now());
            userEntity.setUpdateAt(Instant.now());
            userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

            log.info("Usuário criado no banco de dados. {}", userEntity);
            userRepository.save(userEntity);

            return userMapper.userEntityToCreateUserResponseDto(userEntity);


        }catch (Exception e){
            log.error("Não foi possível criar um usuário no banco. {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Cacheable(cacheNames = "users")
    public List<UserResponseDto> getUsers() {
        var users = userRepository.findAll();

        return users.stream()
                .map(userMapper::userEntityToUserResponseDto)
                .collect(Collectors.toList());
    }


}
