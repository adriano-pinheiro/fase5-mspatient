package br.com.fase5.hackaton.mspatient;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;
import static org.awaitility.Awaitility.await;

@SpringBootTest
@Profile("test")
class MsPatientTests {

    private static MongodExecutable mongodExecutable;
    private static String connectionString;

    @BeforeAll
    public static void setUp() throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();

        connectionString = "mongodb://localhost:27019/testdb";

        MongodConfig mongodConfig = MongodConfig.builder()
                .version(Version.Main.V6_0)
                .net(new Net("localhost", 27019, Network.localhostIsIPv6()))
                .build();

        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();

        waitForMongoDB(connectionString);
    }

    @AfterAll
    public static void tearDown() {
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }

    @Test
    void contextLoads() {
        //testes
    }

    private static void waitForMongoDB(String uri) throws IOException {
        Instant start = Instant.now();
        while (Duration.between(start, Instant.now()).getSeconds() < 10) {
            try (MongoClient mongoClient = MongoClients.create(uri)) {
                mongoClient.listDatabaseNames();
                return;
            } catch (Exception ignored) {
                await().atMost(Duration.ofSeconds(15)).until(() -> didTheThing().call());
            }
        }
        throw new IOException("MongoDB não inicializou a tempo.");
    }

    private static Callable<Boolean> didTheThing() {
        return new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return null;
            }
        };
    }
}