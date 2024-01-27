package com.api.telisadoptproyect.api.service;

import com.api.telisadoptproyect.api.configuration.PropertiesConfig;
import com.api.telisadoptproyect.library.exception.BadRequestException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryService {
    @Autowired
    private PropertiesConfig propertiesConfig;
    public Cloudinary instanceCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", propertiesConfig.getCloudinaryCloudName(),
                "api_key", propertiesConfig.getCloudinaryApiKey(),
                "api_secret", propertiesConfig.getCloudinaryApiSecret()
        ));
    }
    public String uploadFile(MultipartFile file, String folderPath) {
        try {
            Cloudinary cloudinary = instanceCloudinary();
            cloudinary.config.secure = true;
            String publicId = UUID.randomUUID().toString();
            Map params1 = ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", true,
                    "overwrite", true,
                    "public_id", publicId,
                    "folder", folderPath
            );
            cloudinary.uploader().upload(file.getBytes(), params1);
            return publicId;
        } catch (IOException e) {
            throw new BadRequestException("The File cannot be saved");
        }
    }

    public String getUrlFile(String publicId, String folderPath) {
       try {
           Cloudinary cloudinary = instanceCloudinary();
           cloudinary.config.secure = true;
           Map params2 = ObjectUtils.asMap(
                   "public_id", publicId
           );
           return cloudinary.api().resource(folderPath + "/" + publicId, params2).get("secure_url").toString();
       }catch (Exception e){
           throw new BadRequestException("The Url cannot be obtained");
       }
    }

    public void destroyFile (String urlFile, String pathFolder){
        try {
            Cloudinary cloudinary = instanceCloudinary();
            cloudinary.config.secure = true;
            String lastCode = urlFile.substring(urlFile.lastIndexOf("/") + 1, urlFile.lastIndexOf("."));
            String publicId = pathFolder + "/" + lastCode;
            Map params3 = ObjectUtils.asMap(
                    "public_id", publicId
            );
            cloudinary.uploader().destroy(publicId, params3);
        }catch (Exception e){
            throw new BadRequestException("The File cannot be deleted");
        }
    }

}
