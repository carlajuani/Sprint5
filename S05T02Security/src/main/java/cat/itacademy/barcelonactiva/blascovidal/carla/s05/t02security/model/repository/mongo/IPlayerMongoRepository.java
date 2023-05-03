package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.repository.mongo;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mongo.PlayerMongo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerMongoRepository extends MongoRepository<PlayerMongo, ObjectId> {
    PlayerMongo findByUsernameContaining(String username);
}
