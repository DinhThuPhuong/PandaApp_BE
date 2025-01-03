package com.example.PandaCoffee.service;

import com.example.PandaCoffee.model.Images;
import com.example.PandaCoffee.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageRepository imageRepository;



    public List<Images> uploadImages(List<MultipartFile> files) throws IOException {


        List<Images> uploadResults = new ArrayList<>();

        for (MultipartFile file : files) {

            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String publicId = (String) uploadResult.get("public_id");
            String url = (String) uploadResult.get("url");
            String secureUrl = (String) uploadResult.get("secure_url");


            Images image = new Images();
            image.setPublicId(publicId);
            image.setUrl(url);
            image.setSecureUrl(secureUrl);


            uploadResults.add(image);

            imageRepository.save(image);

        }

        return uploadResults;


    }


    public Images uploadImage(MultipartFile file) throws IOException {

        Images uploadResults = new Images();
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String publicId = (String) uploadResult.get("public_id");
        String url = (String) uploadResult.get("url");
        String secureUrl = (String) uploadResult.get("secure_url");

        Images image = new Images();
        image.setPublicId(publicId);
        image.setUrl(url);
        image.setSecureUrl(secureUrl);

        return imageRepository.save(image);


    }

    public Images uploadVideo(MultipartFile file) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "video"));
        String publicId = (String) uploadResult.get("public_id");
        String url = (String) uploadResult.get("url");
        String secureUrl = (String) uploadResult.get("secure_url");

        Images video = new Images();
        video.setPublicId(publicId);
        video.setUrl(url);
        video.setSecureUrl(secureUrl);

        return imageRepository.save(video);
    }
}
