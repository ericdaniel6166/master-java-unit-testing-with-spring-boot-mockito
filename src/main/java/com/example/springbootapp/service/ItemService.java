package com.example.springbootapp.service;

import com.example.springbootapp.dto.ItemDTO;
import com.example.springbootapp.entity.Item;
import com.example.springbootapp.error.ResourceDuplicatedException;
import com.example.springbootapp.error.ResourceNotFoundException;
import com.example.springbootapp.error.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> findAll();

    Item findById(Long id) throws ResourceNotFoundException;

    ItemDTO updateItem(Long id, ItemDTO itemDTORequest) throws ResourceNotFoundException;

    ItemDTO createItem(ItemDTO itemDTORequest) throws ResourceDuplicatedException;
}
