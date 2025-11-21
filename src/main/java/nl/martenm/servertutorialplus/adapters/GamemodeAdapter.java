package nl.martenm.servertutorialplus.adapters;

import com.google.gson.*;
import org.bukkit.GameMode;

import java.lang.reflect.Type;

public class GamemodeAdapter implements JsonDeserializer<GameMode>, JsonSerializer<GameMode> {

    public static final GamemodeAdapter INSTANCE = new GamemodeAdapter();

    @Override
    public GameMode deserialize(JsonElement jsonString, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        if (!jsonString.isJsonObject()) {
            throw new JsonParseException("Invalid JSON string");
        }

        final JsonObject obj = (JsonObject) jsonString;
        final JsonElement name = obj.get("name");

        if (name == null) {
            throw new JsonParseException("JSON String is invalid!");
        }

        if (!((JsonPrimitive) name).isString()) {
            throw new JsonParseException("Gamemode name has to be a string");
        }

        return GameMode.valueOf(name.getAsString());
    }

    @Override
    public JsonElement serialize(GameMode gameMode, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonObject obj = new JsonObject();
        obj.addProperty("name", gameMode.name());
        return obj;
    }
}
