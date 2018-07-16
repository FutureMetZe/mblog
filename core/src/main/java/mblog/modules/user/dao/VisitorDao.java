package mblog.modules.user.dao;

import mblog.modules.user.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VisitorDao extends JpaRepository<Visitor, Long>, JpaSpecificationExecutor<Visitor> {
}
