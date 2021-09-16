package ru.nosov.dry_cleaning.services.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nosov.dry_cleaning.dto.in.ClientInDTO;
import ru.nosov.dry_cleaning.dto.out.ClientOutDTO;
import ru.nosov.dry_cleaning.entities.ClientEntity;
import ru.nosov.dry_cleaning.entities.OrderEntity;
import ru.nosov.dry_cleaning.exceptions.DryCleaningApiException;
import ru.nosov.dry_cleaning.repositories.ClientRepository;
import ru.nosov.dry_cleaning.repositories.OrderRepository;
import ru.nosov.dry_cleaning.services.ClientService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private static final String THERE_IS_NO_SUCH_CLIENT = "There is no such Client!";
    private static final String DTO_MUST_NOT_BE_NULL_MESSAGE = "DTO must not be null: ";
    private static final String NO_SUCH_ENTITY = "There are no such entity ";

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final ObjectMapper mapper;


    @Override
    @Transactional
    public ClientEntity create(ClientInDTO dto) {
        if (dto == null) {
            throw new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE + dto);
        }

        List<OrderEntity> orderEntityList = new ArrayList<>();
        for (Long orderId : dto.getOrderId()) {
            OrderEntity orderEntity = orderRepository.findById(orderId)
                    .orElseThrow(() -> new DryCleaningApiException(
                            NO_SUCH_ENTITY + OrderEntity.class + " " + orderId));
            orderEntityList.add(orderEntity);
        }

        ClientEntity entity = mapper.convertValue(dto, ClientEntity.class);
        entity.setOrders(orderEntityList);

   return clientRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        log.debug(String.format("Deleting client by id %s.%n", id));
        ClientEntity entity = clientRepository.findById(id)
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + ClientEntity.class + " " + id));

        entity.setActive(false);
        clientRepository.save(entity);
        //        clientRepository.deleteById(id);

    }

    @Override
    public ClientEntity getById(Long id) {
        log.debug(String.format("Getting client by id: %s.%n", id));


        Optional<ClientEntity> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get();
        } else {
            throw new DryCleaningApiException(NO_SUCH_ENTITY + ClientEntity.class + " " + id);
        }
    }

    @Override
    public List<ClientEntity> getAll() {

        log.debug(String.format("Getting all clients.%n"));
        return clientRepository.findAll();
    }

    @Override
    public ClientEntity update(ClientInDTO dto) {

        if (dto == null) {
            throw new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE + dto);
        }

        log.debug(String.format("Updating client: %s",
                dto));

        clientRepository.findById(dto.getId())
                .orElseThrow(() -> new DryCleaningApiException(
                        NO_SUCH_ENTITY + ClientEntity.class + " " + dto.getId()));


        List<OrderEntity> orderEntityList = new ArrayList<>();
        for (Long orderId : dto.getOrderId()) {
            OrderEntity orderEntity = orderRepository.findById(orderId)
                    .orElseThrow(() -> new DryCleaningApiException(
                            NO_SUCH_ENTITY + OrderEntity.class + " " + orderId));

            orderEntityList.add(orderEntity);
        }

        ClientEntity entity = mapper.convertValue(dto, ClientEntity.class);
        entity.setOrders(orderEntityList);
//        return clientRepository.save(inDTOToEntity(dto));
        return clientRepository.save(entity);
    }

    @Override
    public ClientInDTO toInDTO(ClientEntity clientEntity) {
        return Optional.ofNullable(clientEntity)
                .map(ent -> mapper.convertValue(ent, ClientInDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }

    @Override
    public ClientOutDTO toOutDTO(ClientEntity clientEntity) {
        return Optional.ofNullable(clientEntity)
                .map(ent -> mapper.convertValue(ent, ClientOutDTO.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));
    }


    public ClientEntity inDTOToEntity(ClientInDTO dto) {

        return Optional.ofNullable(dto)
                .map(ent -> mapper.convertValue(dto, ClientEntity.class))
                .orElseThrow(() -> new DryCleaningApiException(DTO_MUST_NOT_BE_NULL_MESSAGE));

    }


}
