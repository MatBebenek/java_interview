package pl.irdis.interview.company;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDAO extends JpaRepository<Company,Long> {

    Company findByName(String name);
}
