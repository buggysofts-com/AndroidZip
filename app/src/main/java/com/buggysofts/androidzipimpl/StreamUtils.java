package com.buggysofts.androidzipimpl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

final class StreamUtils {

    /**
     * Reads up to <b>readLength</b> bytes of data.
     * <br>
     * If the input stream contains >= requested amount of bytes,
     * the method is guaranteed to return the specified amount of bytes.
     * Otherwise, it returns the amount of bytes available in the stream.
     **/
    public static byte[] readFully(InputStream in, int readLength, boolean closeStream) {
        // temporary output stream
        ByteArrayOutputStream outStream = new ByteArrayOutputStream(0);

        int readNum = 0;
        int totalRead = 0;
        byte[] data = new byte[Math.min(readLength, 4096)];
        try {
            while ((totalRead < readLength) && (readNum = in.read(data)) >= 0) {
                int toWrite = Math.min(readNum, readLength - totalRead);
                outStream.write(data, 0, toWrite);
                totalRead += toWrite;
            }
        } catch (Exception e) {
            e.printStackTrace();

            // something went wrong
            // assign an empty data
            data = new byte[0];
        } finally {
            // this call to close does nothing - it is just for the
            // peace of your mind that we are closing all the used streams.
            try {
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // if specified, close the input stream
            if (closeStream) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return outStream.toByteArray();
    }
}
