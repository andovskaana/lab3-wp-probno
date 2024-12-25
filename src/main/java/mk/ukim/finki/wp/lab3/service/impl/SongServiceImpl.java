package mk.ukim.finki.wp.lab3.service.impl;


import mk.ukim.finki.wp.lab3.model.Artist;
import mk.ukim.finki.wp.lab3.model.Song;
import mk.ukim.finki.wp.lab3.repository.ArtistRepository;
import mk.ukim.finki.wp.lab3.repository.SongRepository;
import mk.ukim.finki.wp.lab3.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    public SongServiceImpl(SongRepository songRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    //@Transactional
    public Artist addArtistToSong(Artist artist, Song song) {
        // Check if the artist exists in the Artist repository
        Artist existingArtist = artistRepository.findById(artist.getId()).orElse(null);
        if (existingArtist != null) {
            // Retrieve the song from the database
            Song existingSong = songRepository.findById(song.getId()).orElse(null);
            if (existingSong != null) {
                // Add the artist to the song's performers
                existingSong.getPerformers().add(existingArtist);
                songRepository.save(existingSong); // Persist the changes
                return existingArtist;
            }
        }
        return null; // Return null if the artist or song does not exist
    }


    @Override
    public Song findById(Long trackId) {
        return songRepository.findById(trackId).orElse(null);
    }

    @Override
    public void save(Song song) {
        // Save a new song
        songRepository.save(song);
    }

    @Override
    public void update(Long id, Song updatedSong) {
        // Find the song to update
        Song existingSong = findById(id);
        if (existingSong != null) {
            // Remove the old song and add the updated one
            songRepository.delete(existingSong);
            updatedSong.setId(id); // Ensure the ID remains the same
            songRepository.save(updatedSong);
        }
    }

    @Override
    public void delete(Long id) {
        // Remove the song with the specified ID
        songRepository.deleteById(id);
    }
    @Override
    public List<Song> findAllByAlbumId(Long albumId) {
        return songRepository.findAllByAlbum_Id(albumId);
    }
}
