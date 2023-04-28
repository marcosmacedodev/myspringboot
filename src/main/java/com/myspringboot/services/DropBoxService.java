package com.myspringboot.services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.myspringboot.services.exceptions.FileException;

@Service
public class DropBoxService {
	
	@Autowired
	private DbxClientV2 dbxClientV2;
	
	public String upload(MultipartFile file) {
		try {
			String filename = file.getOriginalFilename();
			InputStream is = null;
			is = file.getInputStream();
			String contentType = file.getContentType();
			return upload(is, filename, contentType);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new FileException("Erro de: " +  e.getMessage());
		}
	}
	
	public String upload(InputStream is, String filename, String contentType) throws IOException {
		
		try {
			FileMetadata metadata = dbxClientV2.files()
					.upload("/" + filename)
					//.uploadBuilder("/" + filename)
			        .uploadAndFinish(is);
		    //GetTemporaryLinkResult gtlr = dbxClientV2.files().getTemporaryLink(metadata.getPathLower());
			return metadata.getPathLower();

		}catch (UploadErrorException e) {
			// TODO Auto-generated catch block
			throw new FileException("Erro de : " + e.getMessage());
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			throw new FileException("Erro de: " + e.getMessage());
		}
	}
	
	public String getLink(String path) {
		
		if (path == null) return null;
		
		try {
			return dbxClientV2.files().getTemporaryLink(path).getLink();
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
