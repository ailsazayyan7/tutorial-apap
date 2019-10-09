package apap.tutorial.gopud.repository;

import apap.tutorial.gopud.model.RestoranModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface RestoranDB extends JpaRepository<RestoranModel, Long>{
    Optional<RestoranModel> findByIdRestoran(Long idRestoran);

}
