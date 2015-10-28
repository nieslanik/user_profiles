package cz.muni.fi.pa165.dao;

import java.util.List;

import cz.muni.fi.pa165.entity.Member;

public interface MemberDao {
    void create(Member member);

    void update(Member member);

    Member findById(Long id);

    List<Member> findAll();

    void delete(Member member);
}
