package Study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity() {
        Team teamA = new Team("A");
        Team teamB = new Team("B");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("m1", 10, teamA);
        Member member2 = new Member("m2", 20, teamA);
        Member member3 = new Member("m3", 30, teamB);
        Member member4 = new Member("m4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        //초기화
        em.flush();
        em.clear();

        //확인
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        for(Member m: members) {
            System.out.println("member=" + m);
            System.out.println("-> member.team = "+m.getTeam());
        }
    }
}