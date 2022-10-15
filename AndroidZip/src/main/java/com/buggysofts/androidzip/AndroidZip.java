package com.buggysofts.androidzip;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.documentfile.provider.DocumentFile;

import com.buggysofts.streamzip.StreamZip;
import com.buggysofts.streamzip.ZipEntry;

import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AndroidZip extends StreamZip {
    public AndroidZip(@NonNull Context context, @NonNull DocumentFile documentFile) throws Exception {
        super(
            ((FileInputStream) context.getContentResolver()
                .openInputStream(documentFile.getUri()))
        );
    }

    /**
     * Get a particular entry.
     * */
    public ZipEntry getEntry(@NonNull String name){
        return super.getEntry(name);
    }

    /**
     * Get a list of all the entries available in the zip file.
     */
    @Override
    public List<ZipEntry> entries() {
        return super.entries();
    }

    /**
     * Get input stream for a particular entry.
     *
     * @throws Exception If the input stream can not be opened due to unavailability,
     *                   or if the entry is a directory entry, or the zip has been closed.
     */
    @Override
    public InputStream getInputStream(@NonNull ZipEntry entry) throws Exception {
        return super.getInputStream(entry);
    }

    /**
     * Get number of available entries in this zip.
     */
    @Override
    public int size() {
        return super.size();
    }

    /**
     * Get global comment of the zip file.
     */
    @Override
    public @Nullable String getComment() {
        return super.getComment();
    }

    /**
     * Close the zip file. After this you won't be able to call {@code getInputStream()}.
     */
    @Override
    public void close() throws IOException {
        super.close();
    }
}
