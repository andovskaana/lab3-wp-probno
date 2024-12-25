package mk.ukim.finki.wp.lab3.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mk.ukim.finki.wp.lab3.service.SongService;
import mk.ukim.finki.wp.lab3.model.Song;

@Controller
@RequestMapping("/songDetails")
public class SongDetailsController {

    private final SongService songService;

    public SongDetailsController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public String getSongDetails(@RequestParam Long Id, Model model) {
        Song song = songService.findById(Id);
        model.addAttribute("song", song);
        return "songDetails"; // Points to songDetails.html
    }
    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        //model.addAttribute("bodyContent", "access-denied");
        return "access-denied";
    }
}

