package com.br.com.nava.focus.domain.service;

import com.br.com.nava.focus.adapter.dto.address.AddressRequestDto;
import com.br.com.nava.focus.domain.model.Address;
import com.br.com.nava.focus.domain.model.Store;
import com.br.com.nava.focus.domain.repository.AddressRepository;
import com.br.com.nava.focus.domain.repository.AddressService;
import com.br.com.nava.focus.domain.repository.ViaCepIntegration;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService, ViaCepIntegration {

    @Autowired
    private AddressRepository addressRepository;


    @Override
    @Transactional
    public Address saveAddress(AddressRequestDto addressRequestDto, Store store) {
        try {

            var addressEntity = getAddressByCep(addressRequestDto.cep());
            addressEntity.setStore(store);

            log.info("Endereço salvado com sucesso no banco: {}", addressEntity);
            return addressRepository.save(addressEntity);

        } catch (Exception e) {
            log.error("Não foi possível salvar endereço no banco: {}", e.getMessage());
            return new Address();
        }
    }


    @Override
    public Address getAddressByCep(String cep) {
        try {

            Gson gson = new Gson();

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/" +
                            cep + "/json/"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();

            if (!responseBody.isBlank()) {
                log.info("Object Java serializado: {}", responseBody);
                return gson.fromJson(responseBody, Address.class);
            }

            log.info("Não foi possível serializar o object Java para Json");
            return new Address();

        } catch (Exception e) {
            log.error("Erro ao serializar o object Java para Json: {}", e.getMessage());
            return new Address();
        }
    }
}
