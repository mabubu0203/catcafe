package mabubu0203.com.github.catcafe.common.source.jpa;

import mabubu0203.com.github.catcafe.common.source.jpa.entity.BaseTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface TableSource<D extends BaseTable, ID> extends JpaRepository<D, ID> {
}