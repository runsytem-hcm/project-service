package jp.gmo.project.repository;

import jp.gmo.project.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

    Optional<Rank> findByIdAndDeleteFlag(int id, int deleteFlag);

    List<Rank> findByDeleteFlag(int deleteFlag);
}
