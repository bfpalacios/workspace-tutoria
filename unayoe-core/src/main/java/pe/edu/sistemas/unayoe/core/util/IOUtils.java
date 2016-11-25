package pe.edu.sistemas.unayoe.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class IOUtils.
 */
public class IOUtils {

	/**
	 * Input stream to byte array.
	 *
	 * @param is the is
	 * @return the byte[]
	 */
	public static byte[] inputStreamToByteArray(InputStream is) {
		byte[] data = new byte[1024];
		int nread = 0;
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			nread = is.read(data, 0, data.length);
			while (nread != -1) {
				buffer.write(data, 0, nread);
				nread = is.read(data, 0, data.length);
			}
		} catch (Exception err) {
			return null;
		}
		return buffer.toByteArray();
	}

	/**
	 * Byte array to input stream.
	 *
	 * @param ba the ba
	 * @return the input stream
	 */
	public static InputStream byteArrayToInputStream(byte[] ba) {
		return new ByteArrayInputStream(ba);
	}

}
