import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.math3.random.MersenneTwister;

/**
 * A fast and cryptographically strong implementation of RNG. The underlying implementation of RNG is
 * {@link org.apache.commons.math3.random.MersenneTwister} that uses seeds that is generated by
 * {@link java.security.SecureRandom} and hashed with SHA1 hashing. Note that java.security.SecureRandom relies on
 * entropy to generate seeds.
 */
public class RandomNumberGenerator {

    private static final int SHA1_LENGTH_IN_BYTES = 20;
    private static final int SHA1_LENGTH_IN_INT = SHA1_LENGTH_IN_BYTES / 4;
    private MersenneTwister mt;

    /**
     * Creates an instance of RandomNumberGenerator. The constructor also generates the seed for use of the underlying
     * Mersenne Twister instance.
     */
    public RandomNumberGenerator() {
        try {
            mt = new MersenneTwister(generateSeed());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public int nextInt(int min, int max) {
        return min + mt.nextInt(max);
    }

    /**
     * Generates integers as an array by combining entropy and hashing. Entropy is via java.security.SecureRandom.
     * 
     * @return an array generated seeds
     * @throws NoSuchAlgorithmException
     */
    private int[] generateSeed() throws NoSuchAlgorithmException {

        SecureRandom random = new SecureRandom();
        byte[] bytes = random.generateSeed(16);
        MessageDigest sha1Digester = MessageDigest.getInstance("SHA-1");
        byte[] sha1Bytes = sha1Digester.digest(bytes);

        try (ByteArrayInputStream in = new ByteArrayInputStream(sha1Bytes);
                DataInputStream din = new DataInputStream(in)) {
            int[] seeds = new int[SHA1_LENGTH_IN_INT];
            for (int i = 0; i < seeds.length; i++) {
                seeds[i] = din.readInt();
            }
            return seeds;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}