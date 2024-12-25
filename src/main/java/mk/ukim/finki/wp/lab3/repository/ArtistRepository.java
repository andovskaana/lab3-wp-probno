package mk.ukim.finki.wp.lab3.repository;


import mk.ukim.finki.wp.lab3.model.Artist;
import mk.ukim.finki.wp.lab3.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long> {
}