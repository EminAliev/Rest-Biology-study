package ru.itis.biology.models;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.File;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder()
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // название файла в хранилище
    private String storageFileName;
    // название файла оригинальное
    private String originalFileName;
    // размер файла
    private Long size;
    // тип файла (MIME)
    private String type;
    // по какому URL можно получить файл
    private String url;

    @Transient
    private File file;

    @PostLoad
    public void loadFile() {
        // persistent(path) -> transient(sourceFile, fileName)
        file = new File(url);
        String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
        log.info("Load file for " + fileName);
    }

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;
}
