package train.daos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TrainDao {

    //This can come from properties file, including other locations.
    // Or the data can come from DB.
    private final String MAP = "map.txt";

    public List<String> getRawMapData() {
        List<String> result = new ArrayList<>();

        try {
            File file = new File(ClassLoader.getSystemClassLoader().getResource(MAP).getFile());
            String content = new String(Files.readAllBytes(file.toPath()));
            result.addAll(Arrays.asList(content.split(",")));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

}
