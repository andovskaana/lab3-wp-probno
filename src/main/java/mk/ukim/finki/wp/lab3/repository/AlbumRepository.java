package mk.ukim.finki.wp.lab3.repository;



import mk.ukim.finki.wp.lab3.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
