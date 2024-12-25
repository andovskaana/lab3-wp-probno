package mk.ukim.finki.wp.lab3.web;

import mk.ukim.finki.wp.lab3.model.Artist;
import mk.ukim.finki.wp.lab3.model.Song;
import mk.ukim.finki.wp.lab3.service.ArtistService;
import mk.ukim.finki.wp.lab3.service.SongService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String listArtists(@RequestParam(required = false) Long Id, Model model) {
        //Song selectedSong = (Id != null) ? songService.findById(Id) : null;
        List<Artist> artists = artistService.listArtists();

        model.addAttribute("artists", artists);
        assert Id != null;
        model.addAttribute("Id", Id);

        return "artistList"; // Points to artistsList.html
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String addArtistToSong(@RequestParam Long artistId,
                                  @RequestParam String trackId,
                                  @RequestParam Long Id) {
        Artist artist = artistService.findById(artistId);
        Song song = songService.findById(Id);

        if (artist != null && song != null) {
            songService.addArtistToSong(artist, song);
        }

        return "redirect:/songDetails?trackId=" + trackId;
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
       // model.addAttribute("bodyContent", "access-denied");
        return "access-denied";
    }
}