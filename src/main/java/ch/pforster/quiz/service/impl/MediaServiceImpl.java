package ch.pforster.quiz.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ch.pforster.quiz.service.MediaService;

@Service
public class MediaServiceImpl implements MediaService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MediaServiceImpl.class);

	private String uploadPath;

	@Override
	public String uploadMedia(MultipartFile file) {
		String path = uploadPath + "\\" + file.getOriginalFilename();
		try {
			file.transferTo(new File(path));
			LOG.info("uploaded media {}", path);
			return path;
		} catch (IllegalStateException | IOException e) {
			LOG.error("Unable to upload file {}", path);
			return null;
		}
	}
	
	@Value("${quiz.images.upload.path}")
	public void setImagePath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	@Override
	public void moveMedia(String source, String target) {
		Path sourcePath = FileSystems.getDefault().getPath(source);
		Path targetPath = FileSystems.getDefault().getPath(target);
		try {
			Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			LOG.error("Unable to move file {} to {}", source, target);
		}
	}

}
