package com.br.com.nava.focus.adapter.dto.store;

import com.br.com.nava.focus.adapter.dto.address.AddressResponseDto;
import com.br.com.nava.focus.domain.model.Employee;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class StoreResponseDto {

    private String name;
    private String phoneNumber;
    private String email;
    private String cnpj;
    private AddressResponseDto address;

    public StoreResponseDto(String phoneNumber, String email, String cnpj, AddressResponseDto address) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cnpj = cnpj;
        this.address = address;
    }

    public Store toUserEntity(User user, UUID storeId){
        Store store = new Store();

        store.setStoreId(storeId);
        store.setUsers(List.of(user));
        store.setName(this.getName());
        store.setPhoneNumber(this.getPhoneNumber());
        store.setEmail(this.getEmail());
        store.setCnpj(this.getCnpj());
        store.setAddress(this.getAddress().toEntity());

        return store;
    }
    public Store toEmployeeEntity(User user, Employee employee, UUID storeId){
        Store store = new Store();

        store.setStoreId(storeId);
        store.setUsers(List.of(user));
        store.setName(this.getName());
        store.setPhoneNumber(this.getPhoneNumber());
        store.setEmail(this.getEmail());
        store.setCnpj(this.getCnpj());
        store.setAddress(this.getAddress().toEntity());
        store.setEmployees(List.of(employee));

        return store;
    }

}
