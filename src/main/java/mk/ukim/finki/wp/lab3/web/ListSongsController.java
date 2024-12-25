package mk.ukim.finki.wp.lab3.web;

import mk.ukim.finki.wp.lab3.model.Album;
import mk.ukim.finki.wp.lab3.model.Artist;
import mk.ukim.finki.wp.lab3.model.Song;
import mk.ukim.finki.wp.lab3.service.AlbumService;
import mk.ukim.finki.wp.lab3.service.ArtistService;
import mk.ukim.finki.wp.lab3.service.SongService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class ListSongsController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public ListSongsController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    // Display all songs
    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("songs", songService.listSongs());
        model.addAttribute("error", error);
        return "listSongs"; // Points to the songs list view
    }

    // Show form to add a new song
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddSongPage(Model model) {
        model.addAttribute("formAction", "/songs/add");
        model.addAttribute("song", null); // Empty song for adding
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("artists", artistService.listArtists());
        return "add-song"; // Points to the form for adding/editing a song
    }

    // Save a new song
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveSong(@RequestParam String title,
                         //  @RequestParam String trackId,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId,
                           @RequestParam(required = false) List<Artist> artists) {
        Album album = albumService.findById(albumId);
        if (album != null) {
            Song newSong= new Song(title, genre, releaseYear, album, artists);
            //String title, String genre, int releaseYear, Album album, List<Artist> performers
            songService.save(newSong);
        }
        return "redirect:/songs";
    }

    // Show form to edit an existing song
    @GetMapping("/edit/{songId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditSongPage(@PathVariable Long songId, Model model) {
        Song song = songService.findById(songId);
        if (song == null) {
            return "redirect:/songs?error=SongNotFound";
        }
        model.addAttribute("formAction", "/songs/edit/" + songId);
        model.addAttribute("song", song); // Pre-filled song for editing
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("artists", artistService.listArtists());
        return "add-song"; // Points to the form for adding/editing a song
    }

    // Edit an existing song
    @PostMapping("/edit/{songId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editSong(@PathVariable Long songId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId,
                           @RequestParam(required = false) List<Long> artistIds) {
        Album album = albumService.findById(albumId); // Fetch the Album object using the ID
        List<Artist> performers = artistService.findArtistsByIds(artistIds); // Fetch the Artist objects

        // Update the existing song
        Song newsong = new Song(title, genre, releaseYear, album, performers);
        newsong.setId(songId); // Set the ID to ensure the existing record is updated
        songService.update(newsong.getId(), newsong);

        return "redirect:/songs";
    }


    // Delete a song by ID
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSong(@PathVariable Long id) {
        songService.delete(id);
        return "redirect:/songs";
    }

//    @GetMapping("/access_denied")
//    public String getAccessDeniedPage(Model model) {
//        //model.addAttribute("bodyContent", "access-denied");
//        return "access-denied";
//    }
}
