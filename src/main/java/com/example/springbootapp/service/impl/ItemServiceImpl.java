package com.example.springbootapp.service.impl;

import com.example.springbootapp.dto.ItemDTO;
import com.example.springbootapp.entity.Item;
import com.example.springbootapp.error.ResourceDuplicatedException;
import com.example.springbootapp.error.ResourceNotFoundException;
import com.example.springbootapp.repository.ItemRepository;
import com.example.springbootapp.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Item> findAll() {
        log.info("Start FIND_ALL_ITEM");
        List<Item> itemList = itemRepository.findAll();
        log.info("End FIND_ALL_ITEM, size: {}, itemList: {}", itemList.size(), itemList.toString());
        return itemList;
    }

    @Override
    public Item findById(Long id) throws ResourceNotFoundException {
        log.info("Start FIND_ITEM_BY_ID, id: {}", id);
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (!itemOptional.isPresent()) {
            log.warn("Item not found, id: {}", id);
            throw new ResourceNotFoundException("id: " + id);
        }
        Item item = itemOptional.get();
        log.info("End FIND_ITEM_BY_ID, item: {}", item.toString());
        return item;
    }

    @Override
    public ItemDTO updateItem(Long id, ItemDTO itemDTORequest) throws ResourceNotFoundException {
        log.info("Start UPDATE_ITEM_BY_ID, id: {}, itemDTORequest: {}", id, itemDTORequest);
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (!itemOptional.isPresent()) {
            log.warn("Item not found, id: {}", id);
            throw new ResourceNotFoundException("id: " + id);
        }
        Item itemRequest = modelMapper.map(itemDTORequest, Item.class);
        itemRequest.setId(id);
        Item result = itemRepository.saveAndFlush(itemRequest);
        ItemDTO itemDTOResponse = modelMapper.map(result, ItemDTO.class);
        log.info("End UPDATE_ITEM_BY_ID, itemDTOResponse: {}", itemDTOResponse);
        return itemDTOResponse;
    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTORequest) throws ResourceDuplicatedException {
        log.info("Start CREATE_ITEM, itemDTORequest: {}", itemDTORequest);
        Optional<Item> itemOptional = itemRepository.findByName(itemDTORequest.getName());
        if (itemOptional.isPresent()){
            log.warn("Item is duplicated, name: {}", itemDTORequest.getName());
            throw new ResourceDuplicatedException("name: " + itemOptional.get().getName());
        }
        Item itemRequest = modelMapper.map(itemDTORequest, Item.class);
        Item result = itemRepository.saveAndFlush(itemRequest);
        ItemDTO itemDTOResponse = modelMapper.map(result, ItemDTO.class);
        log.info("End CREATE_ITEM, itemDTOResponse: {}", itemDTOResponse);
        return itemDTOResponse;
    }
}
