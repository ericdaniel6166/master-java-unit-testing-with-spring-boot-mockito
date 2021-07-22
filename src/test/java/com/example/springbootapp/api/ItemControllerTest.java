package com.example.springbootapp.api;

import com.example.springbootapp.dto.ItemDTO;
import com.example.springbootapp.entity.Item;
import com.example.springbootapp.error.ResourceNotFoundException;
import com.example.springbootapp.service.ItemService;
import com.example.springbootapp.utils.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//////junit 4
//@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
class ItemControllerTest {
    public static final String URI_TEMPLATE = "/item";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ItemService itemService;

    private Item itemEntity1;
    private List<Item> itemList;

    private ItemDTO itemDTORequest;
    private ItemDTO itemDTOResponse;

    @BeforeEach
    void setUp() {
        itemEntity1 = initItemEntity();

        Item itemEntity2 = initItemEntity();
        itemEntity2.setId(2L);

        itemList = new ArrayList<>();
        itemList.add(itemEntity1);
        itemList.add(itemEntity2);

        itemDTORequest = initItemDTO();
        itemDTOResponse = initItemDTO();
        itemDTOResponse.setId(1L);

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
    void whenGetItem_thenReturnItem() throws Exception {
        //given
//        String expected = objectMapper.writeValueAsString(itemEntity1);
        String expected = CommonUtils.asJsonString(itemEntity1);
        //when
        RequestBuilder request = MockMvcRequestBuilders
                .get(URI_TEMPLATE + "/get-item")
                .accept(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void whenGetAllItems_thenReturnListItem() throws Exception {
        //given
        when(itemService.findAll()).thenReturn(itemList);
//        String expected = objectMapper.writeValueAsString(itemList);
        String expected = CommonUtils.asJsonString(itemList);
        //when
        RequestBuilder request = MockMvcRequestBuilders
                .get(URI_TEMPLATE + "/get-all-item")
                .accept(MediaType.APPLICATION_JSON);

        //then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void whenGetItemById_thenReturnItem() throws Exception {
        //given
        Long id = itemEntity1.getId();
        when(itemService.findById(id)).thenReturn(itemEntity1);
//        String expected = objectMapper.writeValueAsString(itemEntity1);
        String expected = CommonUtils.asJsonString(itemEntity1);

        //when
        RequestBuilder request = MockMvcRequestBuilders
                .get(URI_TEMPLATE + "/get-item-by-id/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        //then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void whenUpdateItem_thenReturnItemDTO() throws Exception {
        //given
        Long id = itemEntity1.getId();
        when(itemService.updateItem(id, itemDTORequest)).thenReturn(itemDTOResponse);
//        String expected = objectMapper.writeValueAsString(itemDTOResponse);
        String expected = CommonUtils.asJsonString(itemDTOResponse);

        String itemDTO1jsonContent = objectMapper.writeValueAsString(itemDTORequest);
        //when
        RequestBuilder request = MockMvcRequestBuilders
                .put(URI_TEMPLATE + "/update/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(itemDTO1jsonContent);

        //then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(expected));
    }

    @Test
    void whenUpdateItem_thenThrow() throws Exception {
        //given
        Long id = itemEntity1.getId();
        when(itemService.updateItem(id, itemDTORequest)).thenThrow(ResourceNotFoundException.class);
        String expected = objectMapper.writeValueAsString(itemDTOResponse);

        String itemDTO1jsonContent = objectMapper.writeValueAsString(itemDTORequest);
        //when
        RequestBuilder request = MockMvcRequestBuilders
                .put(URI_TEMPLATE + "/update/"+id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(itemDTO1jsonContent);

        //then
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }
}