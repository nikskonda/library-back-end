package by.bntu.fitr.service;

import by.bntu.fitr.FileNotFoundException;
import by.bntu.fitr.FileStorageException;
import by.bntu.fitr.config.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

@Service
public class FileStorageService {

//    //    @Value("${file.upload-dir}")
//    @Value("${file.upload-dir}")
//    private String fileUploadPath;
////    private String FILE_UPLOAD_PATH = "/media/nikskonda/20B6EA8BB6EA60B0/homeProject/dp/files/uploads";

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            boolean flag = true;
            Random random = new Random();

            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            while (flag) {
                fileName = "" + Math.abs(random.nextInt()) + Math.abs(random.nextInt()) + Math.abs(random.nextInt()) + getFileExtension(fileName);
                targetLocation = this.fileStorageLocation.resolve(fileName);
                if (targetLocation.toFile().exists()) {
                    fileName = "" + Math.abs(random.nextInt()) + Math.abs(random.nextInt()) + Math.abs(random.nextInt()) + getFileExtension(fileName);
                } else {
                    flag = false;
                }
            }


            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }

    private String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
}
