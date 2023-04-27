package com.myspringboot.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.GetTemporaryLinkResult;
import com.dropbox.core.v2.files.UploadErrorException;

@Service
public class DropBoxService {
	
	@Autowired
	private DbxClientV2 dbxClientV2;
	
	public URI upload(MultipartFile file) {
		
		if (file == null) {
			throw new RuntimeException("Arquivo não encontrado");
		}
		
		String filename = file.getOriginalFilename();
		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String contentType = file.getContentType();
		
		return upload(is, filename, contentType);
	}
	
	public URI upload(InputStream is, String filename, String contentType) {
		try {
			FileMetadata metadata = dbxClientV2.files()
					.uploadBuilder("/" + filename)
			        .uploadAndFinish(is);
		    GetTemporaryLinkResult gtlr = dbxClientV2.files().getTemporaryLink(metadata.getPathLower());
			return new URI(gtlr.getLink());

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UploadErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
