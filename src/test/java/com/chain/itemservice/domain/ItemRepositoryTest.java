package com.chain.itemservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void tearDown() {
        itemRepository.clearStore();
    }

    @DisplayName("상품을 저장할 수 있다")
    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        assertThat(itemRepository.findById(savedItem.getId())).isEqualTo(savedItem);
    }

    @DisplayName("저장된 모든 상품을 조회할 수 있다")
    @Test
    void findAll() {
        // given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items).contains(itemA, itemB);
        assertThat(items.size()).isEqualTo(2);
    }

    @DisplayName("저장된 상품의 id를 알면 해당 상품을 수정할 수 있다.")
    @Test
    void update() {
        // given
        Item itemA = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(itemA);

        // when
        itemRepository.update(savedItem.getId(),
                new Item("itemB", 20000, 20));

        // then
        assertThat(savedItem.getItemName()).isEqualTo("itemB");
        assertThat(savedItem.getPrice()).isEqualTo(20000);
        assertThat(savedItem.getQuantity()).isEqualTo(20);
    }
}