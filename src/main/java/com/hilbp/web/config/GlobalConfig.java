package com.hilbp.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.hilbp.adb.util.MyDeviceDetectionListener;

import cn.xsshome.taip.nlp.TAipNlp;
import se.vidstige.jadb.JadbConnection;

@Configuration
public class GlobalConfig {
	
	@Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
	
	@Bean
	public JadbConnection getJadbConnection( ) {
		
//		return new JadbConnection("localhost", 5037);
		return new JadbConnection();
	}
	
//	@Bean
//	public AdbShellUtil getAdbShellUtil() {
//		
//		return new AdbShellUtil();
//	}
	
	@Bean
	public MyDeviceDetectionListener getMyDeviceDetectionListener() {
		
		return new MyDeviceDetectionListener();
	}
	
	@Bean
	public TAipNlp getTAipNlp() {
		
		return new TAipNlp("2119891201", "QVsTZBOwEUl6vFhN");
	}
}
