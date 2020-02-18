package dao;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.UUID;

public interface Dao<Entity> {

    Entity get(int id);

    Collection<Entity> getAll() throws IOException;

    void save(Collection<Entity> entity) throws IOException;

    void add(Entity entity);

    void update(Entity entity);

    void delete(Entity entity);
}
