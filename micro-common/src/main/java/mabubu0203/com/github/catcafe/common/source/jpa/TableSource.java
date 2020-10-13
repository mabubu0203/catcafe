package mabubu0203.com.github.catcafe.common.source.jpa;

import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;

@NoRepositoryBean
public interface TableSource<D extends BaseTable, ID> extends JpaRepository<D, ID>, JpaSpecificationExecutor<D> {

    default D insert(D entity, LocalDateTime localDateTime) {
        entity.setCreatedDateTime(localDateTime);
        entity.setVersion(0);
        return save(entity);
    }

    default D update(D entity, LocalDateTime localDateTime) {
        entity.setUpdatedDateTime(localDateTime);
        return save(entity);
    }

    default D logicalDelete(D entity, LocalDateTime localDateTime) {
        entity.setDeletedDateTime(localDateTime);
        entity.setDeletedFlag(true);
        return save(entity);
    }

}