package ch.pforster.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = { "ch.pforster.quiz" })
public class WebAppConfig extends WebMvcConfigurerAdapter {

	private String imagePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:"+imagePath+"/");
	}
	
	@Value("${quiz.images.path}")
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
