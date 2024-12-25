package mk.ukim.finki.wp.lab3.service;

import org.springframework.stereotype.Service;

import java.util.List;
import mk.ukim.finki.wp.lab3.model.Artist;
//@Service
public interface ArtistService{
    List<Artist> listArtists();
    Artist findById(Long id);

    public List<Artist> findArtistsByIds(List<Long> artistIds);
}