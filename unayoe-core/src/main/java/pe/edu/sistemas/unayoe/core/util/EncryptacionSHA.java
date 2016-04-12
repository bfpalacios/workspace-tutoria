package pe.edu.sistemas.unayoe.core.util;

public class EncryptacionSHA {
	
	/**
     * Metodo para convertir un array de bytes a un String en Hexadecimal.
     * @param aPass data byte array
     * @return String
     */
    private static String convertirHexadecimal(final byte[] aPass) {
        final StringBuffer buffer = new StringBuffer();
        for (int iter = 0; iter < aPass.length; iter++) {
            int medioByte = (aPass[iter] >>> 4) & 0x0F;
            int medioDos = 0;
            do {
                if ((0 <= medioByte) && (medioByte <= 9)) {
                    buffer.append((char) ('0' + medioByte));
                } else {
                    buffer.append((char) ('a' + (medioByte - 10)));
                }
                medioByte = aPass[iter] & 0x0F;
            } while (medioDos++ < 1);
        }
        return buffer.toString();
    }

}
