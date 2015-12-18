
package ch.pforster.quiz.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ch.pforster.quiz.service.MediaService;

@RestController
@RequestMapping("media")
public class MediaController {
	
	private MediaService mediaService;
	
	@Inject
	public MediaController(MediaService mediaService) {
		this.mediaService = mediaService;
	}

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadMedia(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
    	if(file == null) {
    		return null;
    	}
    	mediaService.uploadMedia(file);
    	return file.getOriginalFilename();
    }
    
}
