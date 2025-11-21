package nl.martenm.servertutorialplus.adapters;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.reflect.Type;

/**
 * A gson adapter for {@link org.bukkit.Location}.
 *
 * @author Sasuke & Heroslender
 */
public class LocationAdapter implements JsonDeserializer<Location>, JsonSerializer<Location> {

    public static final LocationAdapter INSTANCE = new LocationAdapter();

    @Override
    public Location deserialize(JsonElement jsonString, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        if (!jsonString.isJsonObject()) {
            throw new JsonParseException("Invalid JSON string");
        }

        final JsonObject obj = (JsonObject) jsonString;
        final JsonElement world = obj.get("world");
        final JsonElement x = obj.get("x");
        final JsonElement y = obj.get("y");
        final JsonElement z = obj.get("z");
        final JsonElement yaw = obj.get("yaw");
        final JsonElement pitch = obj.get("pitch");

        if (world == null || x == null || y == null || z == null) {
            throw new JsonParseException("JSON String is invalid!");
        }

        if (!world.isJsonPrimitive() || !((JsonPrimitive) world).isString()) {
            throw new JsonParseException("world name has to be a string");
        }

        if (!x.isJsonPrimitive() || !((JsonPrimitive) x).isNumber()) {
            throw new JsonParseException("x pos has to be a number");
        }

        if (!y.isJsonPrimitive() || !((JsonPrimitive) y).isNumber()) {
            throw new JsonParseException("y pos has to be a number");
        }

        if (!z.isJsonPrimitive() || !((JsonPrimitive) z).isNumber()) {
            throw new JsonParseException("z pos has to be a number");
        }

        if (yaw != null && (!yaw.isJsonPrimitive() || !((JsonPrimitive) yaw).isNumber())) {
            throw new JsonParseException("x pos has to be a number");
        }

        if (pitch != null && (!pitch.isJsonPrimitive() || !((JsonPrimitive) pitch).isNumber())) {
            throw new JsonParseException("x pos has to be a number");
        }

        World worldInstance = Bukkit.getWorld(world.getAsString());
        if (worldInstance == null) {
            throw new IllegalArgumentException("This world does not exist!");
        }

        return new Location(worldInstance, x.getAsDouble(), y.getAsDouble(), z.getAsDouble(),
                yaw != null ? yaw.getAsFloat() : 0.0F,
                pitch != null ?pitch.getAsFloat() : 0.0F);

    }

    @Override
    public JsonElement serialize(Location location, Type type, JsonSerializationContext jsonSerializationContext) {

        final JsonObject obj = new JsonObject();
        obj.addProperty("world", location.getWorld().getName());
        obj.addProperty("x", location.getX());
        obj.addProperty("y", location.getY());
        obj.addProperty("z", location.getZ());
        obj.addProperty("yaw", location.getYaw());
        obj.addProperty("pitch", location.getPitch());
        return obj;

    }

}