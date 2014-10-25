package nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 *
 *
 */
public class MergeFiles {
    public static void main (String[] args) throws Exception{
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String[] files = new String [] {"nioProgram/resources/google_5000000_0.txt",
                "nioProgram/resources/google_5000000_1.txt",
                "nioProgram/resources/google_5000000_2.txt",
                "nioProgram/resources/google_5000000_3.txt",
                "nioProgram/resources/google_5000000_4.txt"};
        long start;


        start = System.currentTimeMillis();
//        mergeFilesIO(files, "nioProgram/resources/google_5000000_mergedIO.txt");
        System.out.println("IO time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        mergeFilesNIO(files, "nioProgram/resources/google_5000000_mergedNIO.txt");
        System.out.println("NIO time: " + (System.currentTimeMillis() - start));

        // wait fur user to press a key otherwise java exits because the
        // asynch thread isn't important enough to keep it running.
        try { System.in.read(); } catch (IOException e) { }
    }


    public static void mergeFilesIO(String[] files, String target) {
        File mergedFile = new File(target);
        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(mergedFile, true);
            out = new BufferedWriter(fstream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        for (String fn : files) {
            File f = new File(fn);
            System.out.println("merging: " + f.getName());
            FileInputStream fis;
            try {
                fis = new FileInputStream(f);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));

                String aLine;
                while ((aLine = in.readLine()) != null) {
                    out.write(aLine);
                    out.newLine();
                }

                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void mergeFilesNIO(String[] files, String target) throws IOException {
        File destFile = new File(target);
        FileChannel destinationChannel =  new FileOutputStream(destFile).getChannel();;

        for (String sourceFile:files) {
            System.out.println("merging: " + sourceFile);
            // need to keep track of the next position.
            AsynchronousFileChannel asynchFileChannel = AsynchronousFileChannel.open(Paths.get(sourceFile),
                    StandardOpenOption.READ);

            CompletionHandler<Integer, AsynchronousFileChannel> handler = new CompletionHandler<Integer, AsynchronousFileChannel>() {
                int pos = 0;
                ByteBuffer buffer = ByteBuffer.allocate(1000);

                @Override
                public void completed(Integer result, AsynchronousFileChannel attachment) {
                    // if result is -1 means nothing was read.
                    if (result != -1) {
                        pos += result;  // don't read the same text again.
                        // your output command.
//                        System.out.println(new String(buffer.array()));
                        try {
                            destinationChannel.write(buffer);
                        } catch (IOException e) {
                            failed(e, attachment);
                        }

                        buffer.clear();  // reset the buffer so you can read more.
                    }
                    // initiate another asynchronous read, with this.
                    attachment.read(buffer, pos, attachment, this);
                }

                @Override
                public void failed(Throwable e, AsynchronousFileChannel attachment) {
                    System.err.println("File Write Failed Exception:");
                    e.printStackTrace();
                }
            };

            // start off the asynch read.
            asynchFileChannel.read(ByteBuffer.allocate(1000), 0 , asynchFileChannel, handler);
            System.out.println();

        }
    }

    public static void copyFileNIO(File sourceFile, File destFile) throws IOException {
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }
}
