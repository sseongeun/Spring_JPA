package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item){
        if(item.getId()==null){
            em.persist(item); //만약 아이템의 id가 없으면 저장한다
        }else{
            em.merge(item); //이미 있는 경우 update함
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);//id값을 기준으로 item을 찾아 반환
    }

    public List<Item> findAll(){
        return em.createQuery("select m from Member m", Item.class)
                .getResultList();
    }

}

