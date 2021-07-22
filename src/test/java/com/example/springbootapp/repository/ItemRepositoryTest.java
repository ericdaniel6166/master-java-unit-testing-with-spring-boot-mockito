package com.example.springbootapp.repository;

import com.example.springbootapp.entity.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//////junit 4
//@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    private Item itemEntity1;


    @BeforeEach
    void setUp() {
        itemRepository.deleteAll();
        itemEntity1 = initItemEntity();
        Item itemEntity2 = initItemEntity();
        itemEntity2.setName("Item Test No 2");

        itemRepository.saveAndFlush(itemEntity1);
        itemRepository.saveAndFlush(itemEntity2);
    }

    private Item initItemEntity() {
        Item item = new Item();
        item.setName("Item Test No 1");
        item.setPrice(BigDecimal.valueOf(1000));
        item.setQuantity(10L);
        return item;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void whenFindByName_returnItemOptional_isPresentTrue() {
        String name = itemEntity1.getName();
        Optional<Item> itemOptional = itemRepository.findByName(name);
        assertTrue(itemOptional.isPresent());
    }

    @Test
    void whenFindByName_returnItemOptional_isPresentFalse() {
        String name = "Item test not present";
        Optional<Item> itemOptional = itemRepository.findByName(name);
        assertFalse(itemOptional.isPresent());
    }
}