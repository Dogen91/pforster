package ch.pforster.quiz.service;

import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
	
	public String uploadMedia(MultipartFile file);

	public void moveMedia(String source, String target);
}
