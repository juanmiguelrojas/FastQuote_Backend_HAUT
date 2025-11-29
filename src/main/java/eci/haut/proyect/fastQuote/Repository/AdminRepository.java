package eci.haut.proyect.fastQuote.Repository;

import eci.haut.proyect.fastQuote.Model.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<AdminUser, String> {
    Optional<AdminUser> findByUsername(String username);
}