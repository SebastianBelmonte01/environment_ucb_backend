package bo.ucb.edu.environment.Dao;

import bo.ucb.edu.environment.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select sr.name from User su, UserGroup sug, Group sg, RoleGroup srg, Role sr\n" +
            "where su.userId = :userId\n" +
            "AND su.userId = sug.srUser.userId " +
            "AND sug.srGroup.groupId = sg.groupId " +
            "AND sg.groupId = srg.srGroup.groupId " +
            "AND srg.srRole.roleId = sr.roleId")
    List<String> findAllRolesByUserId(@Param("userId") Long id);

}
