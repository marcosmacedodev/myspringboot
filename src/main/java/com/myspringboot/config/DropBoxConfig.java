package com.myspringboot.config;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dropbox.core.DbxApiException;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

@Configuration
public class DropBoxConfig {
	@Value("${dropbox.access.token}")
	private String access_token;
	
	@Value("${dropbox.app.name}")
	private String app_name;
	
	@Bean
	public DbxClientV2 dbxClientV2() throws DbxApiException, DbxException, FileNotFoundException, IOException {
		DbxRequestConfig config = DbxRequestConfig.newBuilder(app_name).build();
        DbxClientV2 client = new DbxClientV2(config, access_token);
        return client;        
	}
}
