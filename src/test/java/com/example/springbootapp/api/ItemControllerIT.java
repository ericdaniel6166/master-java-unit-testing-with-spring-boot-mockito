package com.example.springbootapp.api;

import com.example.springbootapp.entity.Item;
import com.example.springbootapp.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//////JUnit 4 Code
//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIT {

    public static final String URL = "/item";

    @Autowired
    private TestRestTemplate testRestTemplate;

    // using @MockBean
    @MockBean
    private ItemRepository itemRepository;

    private Item initItemEntity() {
        Item item = new Item();
        item.setId(1L);
        item.setName("Item No 1");
        item.setPrice(BigDecimal.valueOf(1000));
        item.setQuantity(10L);
        return item;
    }

    @Test
    void contextLoads() {
        Item item = this.testRestTemplate.getForObject(URL + "/get-item", Item.class);
        assertNotNull(item);
    }
}
