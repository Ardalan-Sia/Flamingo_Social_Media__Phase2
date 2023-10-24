package ap.mini_project.server.db;
import ap.mini_project.shared.model.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class PlayerDB implements DBSet<Player>{

    private final ObjectMapper objectMapper;
    private final String PLAYERS_DIRECTORY = "src\\main\\resources\\Players";

    public PlayerDB() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Player get(String username) {
        File dir = new File(PLAYERS_DIRECTORY);
        File[] UserDirectoryListing = dir.listFiles();
        if (UserDirectoryListing != null) {
            for (File child : UserDirectoryListing) {
                if (FilenameUtils.removeExtension(child.getName()).equals(String.valueOf(username))) {
                    try {

                        Player player = objectMapper.readValue(child, new TypeReference<Player>() {
                        });
                        return player;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public LinkedList<Player> all() {
        LinkedList<Player> all = new LinkedList<>();
        File dir = new File(PLAYERS_DIRECTORY);
        File[] UserDirectoryListing = dir.listFiles();
        if (UserDirectoryListing != null) {
            for (File child : UserDirectoryListing) {
                try {

                    Player player = objectMapper.readValue(child, new TypeReference<Player>() {
                    });
                    all.add(player);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return all;
    }

    @Override
    public void save(Player player) {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(PLAYERS_DIRECTORY + "/" + player.getUsername() + ".json"), player);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void remove(Player player) {

    }

    @Override
    public void update(Player player) {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(PLAYERS_DIRECTORY + "/" + player.getUsername() + ".json"), player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
