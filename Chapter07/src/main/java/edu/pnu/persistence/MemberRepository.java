package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Member;

// Member Table에 CRUD를 위한 interface
public interface MemberRepository extends JpaRepository<Member, String> {

}
