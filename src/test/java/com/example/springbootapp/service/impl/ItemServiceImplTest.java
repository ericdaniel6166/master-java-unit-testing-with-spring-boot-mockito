package com.example.springbootapp.service.impl;

import com.example.springbootapp.dto.ItemDTO;
import com.example.springbootapp.entity.Item;
import com.example.springbootapp.enums.ErrorCode;
import com.example.springbootapp.error.ResourceNotFoundException;
import com.example.springbootapp.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

//////junit 4
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ModelMapper modelMapper;

    private Item itemEntity1;
    private ItemDTO itemDTORequest;
    private ItemDTO itemDTOResponse;
    private Item itemModelMap1;
    private List<Item> itemList;

    @BeforeEach
    void setUp() {
        itemEntity1 = initItemEntity();

        itemDTORequest = initItemDTO();
        itemDTOResponse = initItemDTO();
        itemDTOResponse.setId(1L);

        itemModelMap1 = initItemModelMap();

        Item itemEntity2 = initItemEntity();
        itemEntity2.setId(2L);

        itemList = new ArrayList<>();
        itemList.add(itemEntity1);
        itemList.add(itemEntity2);

    }

    private Item initItemModelMap() {
        Item item = new Item();
        item.setName("Item No 1");
        item.setPrice(BigDecimal.valueOf(1000));
        item.setQuantity(10L);
        return item;
    }

    private ItemDTO initItemDTO() {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("Item No 1");
        itemDTO.setPrice(BigDecimal.valueOf(1000));
        itemDTO.setQuantity(10L);
        return itemDTO;
    }


    private Item initItemEntity() {
        Item item = new Item();
        item.setId(1L);
        item.setName("Item No 1");
        item.setPrice(BigDecimal.valueOf(1000));
        item.setQuantity(10L);
        return item;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        System.out.println(itemList.toString());
    }

    @Test
    void findById() {
    }

    @Test
    void whenUpdateItem_thenReturnItemDTO() throws ResourceNotFoundException {
        //given
        Long id = itemEntity1.getId();

        Optional<Item> itemOptional = Optional.of(itemEntity1);
        when(itemRepository.findById(id)).thenReturn(itemOptional);

        when(modelMapper.map(itemDTORequest, Item.class)).thenReturn(itemModelMap1);

        when(itemRepository.saveAndFlush(itemEntity1)).thenReturn(itemEntity1);
        when(modelMapper.map(itemEntity1, ItemDTO.class)).thenReturn(itemDTOResponse);

        ItemDTO result;
        //when
        result = itemService.updateItem(id, itemDTORequest);

        //then
        assertNotNull(result);
    }

    @Test
    void whenUpdateItem_thenThrowResourceNotFoundException() {

        //given
        Long id = itemEntity1.getId();
        String message = String.format("%s does not exist", "id: " + id);

        Optional<Item> itemOptional = Optional.empty();
        when(itemRepository.findById(id)).thenReturn(itemOptional);

        ItemDTO result = null;
        //when

        try {
            result = itemService.updateItem(id, itemDTORequest);
        } catch (Exception e) {
            assert e instanceof ResourceNotFoundException;
            assertEquals(ErrorCode.RESOURCE_NOT_FOUND_ERROR.name(), ((ResourceNotFoundException) e).getErrorCode());
            assertEquals(message, e.getMessage());
        }
        //then
        assertNull(result);
    }
}