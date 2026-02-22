package com.br.com.nava.focus.domain.service.user;

import com.br.com.nava.focus.adapter.dto.address.AddressRequestDto;
import com.br.com.nava.focus.adapter.dto.address.AddressResponseDto;
import com.br.com.nava.focus.adapter.dto.store.StoreResponseDto;
import com.br.com.nava.focus.adapter.dto.user.CreateUserRequestDto;
import com.br.com.nava.focus.adapter.dto.user.CreateUserResponseDto;
import com.br.com.nava.focus.domain.model.Address;
import com.br.com.nava.focus.domain.model.Role;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.model.User;
import com.br.com.nava.focus.domain.repository.RoleRepository;
import com.br.com.nava.focus.domain.repository.UserRepository;
import com.br.com.nava.focus.domain.service.address.AddressService;
import com.br.com.nava.focus.domain.service.store.StoreService;
import com.br.com.nava.focus.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private StoreService storeService;
    @Mock
    private AddressService addressService;
    @Mock
    private UserMapper userMapper;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("[1 - Scenario]: Should create a new user")
    void Scenario1_create_user_with_all_date() {
        //Given
        UUID storeId = UUID.randomUUID();
        CreateUserRequestDto request =
                new CreateUserRequestDto("Lucas Germano", "lucas.germano@gmail.com", "steve3571", "52725320828",
                        "(11) 97130-2771", Date.from(Instant.now()), Date.from(Instant.now()),
                        new AddressRequestDto("03221300", "Avenida", "casa 7", "6061", "Jardim Angela",
                                "São Paulo", "SP", "São Paulo"),
                        Set.of());
        User userEntity = new User();

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);

        when(userMapper.createUserRequestDtoToUserEntity(request)).thenReturn(userEntity);

        var storeResponse = mock(StoreResponseDto.class);
        Store storeEntity = new Store();
        when(storeService.getStoreById(storeId)).thenReturn(storeResponse);
        when(storeResponse.toUserEntity(any(), eq(storeId))).thenReturn(storeEntity);

        Role role = new Role();
        role.setName(Role.Values.EMPLOYEE.name());

        when(roleRepository.findByName(anyString())).thenReturn(role);

        var addressResponse = mock(AddressResponseDto.class);
        Address addressEntity = new Address();
        var addressRequest = new AddressRequestDto();

        when(addressService.getAddressByCep(request.getAddress().getCep())).thenReturn(addressResponse);
        when(addressResponse.toRequestDto()).thenReturn(addressRequest);
        when(addressService.saveAddress(eq(addressRequest), any())).thenReturn(addressEntity);

        when(passwordEncoder.encode(request.getPassword())).thenReturn("senha_criptografada");

        CreateUserResponseDto responseExpected = new CreateUserResponseDto();
        when(userMapper.userEntityToCreateUserResponseDto(any())).thenReturn(responseExpected);

        CreateUserResponseDto userResponse = userService.createUser(request, storeId);

        assertNotNull(userResponse);
        verify(userRepository).save(any(User.class));
        verify(passwordEncoder).encode(request.getPassword());
    }
    @Test
    @DisplayName("[2 - Scenario]: Shouldn't create an user into database")
    void Scenario2_create_user_with_an_existing_email() {
        //Given
        UUID storeId = UUID.randomUUID();
        CreateUserRequestDto request =
                new CreateUserRequestDto("Lucas Germano", "lucas.germano@gmail.com", "steve3571", "52725320828",
                        "(11) 97130-2771", Date.from(Instant.now()), Date.from(Instant.now()),
                        new AddressRequestDto("03221300", "Avenida", "casa 7", "6061", "Jardim Angela",
                                "São Paulo", "SP", "São Paulo"),
                        Set.of());

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(Exception.class, () -> userService.createUser(request, storeId));
        verify(userRepository).existsByEmail(request.getEmail());
    }

}