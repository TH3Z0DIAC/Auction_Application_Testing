package ch.noseryoung.uk.domainModels.authority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    // It exists to show the basic syntax of the generated queries
    public Authority findByName(String name);

}
