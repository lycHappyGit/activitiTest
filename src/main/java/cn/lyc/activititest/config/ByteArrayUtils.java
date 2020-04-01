package cn.lyc.activititest.config;

public class ByteArrayUtils {
    private ByteArrayUtils() {
    }

    static byte[] convertToPrimitiveArray(Byte[] objects) {
        byte[] bytes = new byte[objects.length];

        for(int i = 0; i < objects.length; ++i) {
            bytes[i] = objects[i];
        }

        return bytes;
    }

    static byte[] convertToObjectArray(byte[] bytes) {
        byte[] objects = new byte[bytes.length];

        for(int i = 0; i < bytes.length; ++i) {
            objects[i] = bytes[i];
        }

        return objects;
    }
}
