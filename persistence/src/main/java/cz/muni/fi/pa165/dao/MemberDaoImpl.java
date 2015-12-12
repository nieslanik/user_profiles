package cz.muni.fi.pa165.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.entity.Member;

/**
 * Data access object for Member entity
 *
 * @author Jakub Peschel 
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
    public List<Member> findByName(String name) {
        return em.createQuery("from Member where givenName = :name or surname = :name", Member.class).setParameter("name", name).getResultList();
    }

    @Override
    public void delete(Member member) {
        em.remove(findById(member.getId()));
    }
    
}
