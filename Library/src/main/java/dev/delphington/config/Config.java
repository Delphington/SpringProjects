package dev.delphington.config;

import lombok.experimental.UtilityClass;
import org.springframework.web.bind.annotation.ResponseStatus;

@UtilityClass
public  class Config {

    public static final String URL_STUDY = "jdbc:postgresql://localhost:5432/SpringCourse";
    public static final String USER = "postgres";
    public static final String PASSWORD = "IwanttoMurea";
}
