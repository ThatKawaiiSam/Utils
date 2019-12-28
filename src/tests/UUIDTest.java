import io.github.thatkawaiisam.utils.UUIDUtility;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class UUIDTest {

    public static void main(String[] args) {
        System.out.println("UUID Test thing");
        CompletableFuture<UUIDUtility.UUIDPair> future = UUIDUtility.getName(UUID.fromString("3a870b6c-7b93-40e7-acb4-26f566360637"));
        future.whenComplete((uuidPair, throwable) -> {
            Logger.getAnonymousLogger().info("Complete");
            System.out.println("Name: " + uuidPair.getName());
            System.out.println("UUID: " + uuidPair.getUuid());
        });
    }
}
