package mk.ukim.finki.wp.lab3.service.impl;


import mk.ukim.finki.wp.lab3.model.Artist;
import mk.ukim.finki.wp.lab3.repository.ArtistRepository;
import mk.ukim.finki.wp.lab3.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return artistRepository.findById(id).orElse(null);
    }

    @Override
    public List<Artist> findArtistsByIds(List<Long> artistIds) {
        if (artistIds == null || artistIds.isEmpty()) {
            return List.of(); // Return an empty list if no IDs are provided
        }
        return artistIds.stream()
                .map(this::findById) // Use the existing findById method
                .filter(artist -> artist != null) // Filter out nulls for non-existing IDs
                .toList();
    }
}
