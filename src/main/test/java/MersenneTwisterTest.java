import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.math3.random.MersenneTwister;

public class MersenneTwisterTest {
	private static final Logger LOG = LoggerFactory.getLogger("datahook");

	public static void main(String[] arg) throws IOException, DecoderException {

		File file = new File("/Users/harvey/Downloads/10m_random_seed_hex.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				byte[] bin = Hex.decodeHex(line);
				int[] seeds = new int[bin.length/4];
				try (ByteArrayInputStream in = new ByteArrayInputStream(bin);
					 DataInputStream din = new DataInputStream(in)) {
					for (int i = 0; i < seeds.length; i++) {
						seeds[i] = din.readInt();
					}
					MersenneTwister mt = new MersenneTwister(seeds);
					int i = mt.nextInt();
					LOG.info("{}", i);
				}
			}
		}
		System.out.println("done");
	}
}
