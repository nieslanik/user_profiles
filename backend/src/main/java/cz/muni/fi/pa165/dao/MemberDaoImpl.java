package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data access object for Member entity 
 * 
 * @author Jakub Peschel
 * @email jakub.peschel@studentagency.cz
 */
@Transactional
@Repository
public class MemberDaoImpl implements MemberDao {
    @PersistenceUnit
    private EntityManager em;
    
    @Override
    public void create(Member member) {
        em.persist(member);
    }

    @Override
    public void update(Member member) {
        em.merge(member);    
    }

    @Override
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("from Member", Member.class).getResultList();
    }

    @Override
    public void delete(Member member) {
        em.remove(findById(member.getId()));
    }
    
}
