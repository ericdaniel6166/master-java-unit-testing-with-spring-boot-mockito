package com.example.springbootapp.api;

import com.example.springbootapp.dto.ItemDTO;
import com.example.springbootapp.entity.Item;
import com.example.springbootapp.error.ResourceNotFoundException;
import com.example.springbootapp.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/get-item")
    public Item getItem() {
        return new Item(1L, "Item No 1", BigDecimal.valueOf(1000), 10L);
    }

    @GetMapping(value = "/get-all-item")
    public List<Item> getAllItemsUsingItemService() {
        return itemService.findAll();
    }

    @GetMapping(value = "/get-item-by-id/{id}")
    public Item getItemUsingItemService(@PathVariable long id) {
        Item itemResponse;
        try {
            itemResponse = itemService.findById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e.getCause());
        }
        return itemResponse;
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateItem(@PathVariable long id, @RequestBody ItemDTO itemDTORequest) {
        ItemDTO itemDTOResponse;
        try {
            itemDTOResponse = itemService.updateItem(id, itemDTORequest);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e.getCause());
        }
        return ResponseEntity.ok(itemDTOResponse);
    }
}
