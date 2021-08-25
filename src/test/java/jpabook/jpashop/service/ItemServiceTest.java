package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;


    @Test
    public void 상품저장() throws Exception {
        //given
        Item item = new Item();

        item.addStock(2);

        //when
        Long tmpItemId = itemService.saveItem(item);

        //then
        assertEquals(tmpItemId, itemRepository.findOne(tmpItemId).getId());
    }

    @Test
    public void 재고추가() throws Exception {
        //given
        Item item = new Item();
        int tmpStockQuantity = item.getStockQuantity();
        int stock = 2;

        item.addStock(stock);

        //when
        Long tmpItemId = itemService.saveItem(item);

        //then
        assertEquals(tmpStockQuantity + stock , itemRepository.findOne(tmpItemId).getStockQuantity());
    }

    @Test
    public void 재고삭제() throws Exception {
        //given
        Item item = new Item();
        item.addStock(3);
        int tmpStockQuantity = item.getStockQuantity();
        int stock = 2;

        item.removeStock(stock);

        //when
        Long tmpItemId = itemService.saveItem(item);

        //then
        assertEquals(tmpStockQuantity - stock , itemRepository.findOne(tmpItemId).getStockQuantity());
    }
}