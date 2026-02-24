package com.br.com.nava.focus.domain.service.employee;

import com.br.com.nava.focus.adapter.dto.employee.CreateEmployeeRequestDto;
import com.br.com.nava.focus.adapter.dto.employee.CreateEmployeeResponseDto;
import com.br.com.nava.focus.domain.model.*;
import com.br.com.nava.focus.domain.repository.EmployeeRepository;
import com.br.com.nava.focus.domain.repository.RoleRepository;
import com.br.com.nava.focus.domain.repository.StoreRepository;
import com.br.com.nava.focus.domain.repository.UserRepository;
import com.br.com.nava.focus.domain.service.address.AddressService;
import com.br.com.nava.focus.mapper.EmployeeMapper;
import com.br.com.nava.focus.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final StoreRepository storeRepository;
    private final AddressService addressService;
    private final BCryptPasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper, UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository, StoreRepository storeRepository, AddressService addressService, BCryptPasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.storeRepository = storeRepository;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public CreateEmployeeResponseDto createEmployee(CreateEmployeeRequestDto dto, UUID storeId) {
        try{
            log.info("Encontrar um employee pelo email: {}", dto.getUserDto().getEmail());

            var existsByEmail = userRepository.existsByEmail(dto.getUserDto().getEmail());
            var existsByCpf = userRepository.existsByCpf(dto.getUserDto().getCpf());

            if (existsByEmail){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um funcionário criado com esse email: " +
                        dto.getUserDto().getEmail());
            }
            if (existsByCpf) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um funcionário criado com esse cpf: " +
                        dto.getUserDto().getCpf());
            }

            User userEntity = userMapper.createUserRequestDtoToUserEntity(dto.getUserDto());
            Employee employeeEntity = new Employee();

            // Buscar Store
            log.info("Buscar store");
            Store storeEntity = storeRepository.findById(storeId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loja não encontrada. "));

            // Buscar Role
            log.info("Buscar role");
            Role role = roleRepository.findByName(Role.Values.EMPLOYEE.name())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário encontrado."));

            // Buscar e Salvar Endereço
            log.info("Buscar e salvar endereço");
            Address addressSaved = addressService.saveAddress(dto.getUserDto().getAddress(), userEntity);

            // Dados de usuário
            userEntity.setAddress(addressSaved);
            userEntity.setRoles(Set.of(role));
            userEntity.setStore(storeEntity);
            userEntity.setCreationAt(Instant.now());
            userEntity.setUpdateAt(Instant.now());
            userEntity.setPassword(passwordEncoder.encode(dto.getUserDto().getPassword()));

            // Dados do Funcionário
            employeeEntity.setUser(userEntity);
            userEntity.setEmployee(employeeEntity);

            employeeEntity.setStore(storeEntity);
            employeeEntity.setHireDate(LocalDate.now());
            employeeEntity.setSalary(dto.getSalary());

            log.info("Funcionário criado no banco de dados. {}", employeeEntity);
            employeeRepository.save(employeeEntity);

            return employeeMapper.employeeEntityToCreateEmployeeResponseDto(employeeEntity);
        }catch (Exception e){
            log.error("Não foi possível criar um funcionário no banco.", e);
            throw new RuntimeException(e);
        }
    }
}
