package cz.muni.fi.pa165.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.entity.Member;

/**
 * Data access object for Member entity
 *
 * @author Jakub Peschel <jakub.peschel@studentagency.cz>
 */
@Repository
public class MemberDaoImpl implements MemberDao {
    @PersistenceContext
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
