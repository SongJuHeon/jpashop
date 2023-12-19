package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    //영속성 상태이기 때문에 @Transactional 에 의해 업데이트 된다.
    //보통 많이 하는 방법
    //merge는 아래 코드를 제공하는 기능인데, 모든 속성이 변경되므로 실무에선 필요한 필드만 셋해서 아래와같이 사용한다.
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {   //파라미터로 넘어온 준영 상태의 엔티티
        Item findItem = itemRepository.findOne(itemId); //영속성 엔티티에서 같은 엔티티를 조회
        findItem.setName(name);                         //데이터 수정, set보다는 의미있는 메소드를 생성헤서 엔티티에서 하게끔 하자
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
