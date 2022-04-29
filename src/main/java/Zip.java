import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static void unZipIt(String zipFile, String outputFolder) {
        byte[] buffer = new byte[1024];

        try {
            File folder = new File(outputFolder);
            if (!folder.exists()) {
                folder.mkdir();
            }

            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));

            for(ZipEntry ze = zis.getNextEntry();
                ze != null;
                ze = zis.getNextEntry())
            {
                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);
                System.out.println("file unzip : " + newFile.getAbsoluteFile());
                (new File(newFile.getParent())).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
            }

            zis.closeEntry();
            zis.close();
            System.out.println("Done");
        } catch (IOException var10) {
            var10.printStackTrace();
        }

    }

    public static void Work_with_zip() {
        int end = 1000;
        while (end != 0) {
            System.out.println("Select the action number to work with the file!");
            System.out.println("1. Create and add file\n" +
                    "2. Unzip the file and display information about it\n" +
                    "3. Delete a file\n" +
                    "4. Finish the file");

            Scanner in = new Scanner(System.in);
            System.out.print("Enter action number: ");
            int choice = in.nextInt();

            String var10000;
            switch (choice) {
                case (1):
                    System.out.print("Input zip name, without type:");
                    Scanner zipName = new Scanner(System.in);
                    String zipNam = zipName.nextLine();
                    FileDialog readZipDialog = new FileDialog((Frame) null, "Выбрать файл");
                    readZipDialog.setMode(0);
                    readZipDialog.setVisible(true);
                    var10000 = readZipDialog.getDirectory();
                    String filePathZip = var10000 + readZipDialog.getFile();
                    Path zipPath = Paths.get("./" + zipNam + ".zip");

                    try {
                        ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(String.valueOf(zipPath)));

                        try {
                            FileInputStream fis = new FileInputStream(filePathZip);

                            try {
                                ZipEntry entry1 = new ZipEntry(readZipDialog.getFile());
                                zout.putNextEntry(entry1);
                                byte[] buffer = new byte[fis.available()];
                                fis.read(buffer);
                                zout.write(buffer);
                                zout.closeEntry();
                            } catch (Throwable var16) {
                                try {
                                    fis.close();
                                } catch (Throwable var13) {
                                    var16.addSuppressed(var13);
                                }

                                throw var16;
                            }

                            fis.close();
                        } catch (Throwable var17) {
                            try {
                                zout.close();
                            } catch (Throwable var12) {
                                var17.addSuppressed(var12);
                            }

                            throw var17;
                        }

                        zout.close();
                    } catch (Exception var18) {
                        System.out.println(var18.getMessage());
                    }
                    break;

                case (2):
                    FileDialog readDialog = new FileDialog((Frame) null, "Выбрать файл");
                    readDialog.setMode(0);
                    readDialog.setVisible(true);
                    var10000 = readDialog.getDirectory();
                    String pathZip = var10000 + readDialog.getFile();
                    unZipIt(pathZip, readDialog.getDirectory());
                    break;

                case (3):
                    FileDialog removeDialog = new FileDialog((Frame) null, "Выбрать файл");
                    removeDialog.setMode(0);
                    removeDialog.setVisible(true);
                    Path rfilePath = null;

                    try
                    {
                        rfilePath = Path.of(removeDialog.getFile());
                        Files.delete(rfilePath);
                    }
                    catch (NoSuchFileException var14) {
                        System.err.format("%s: No such file or directory%n", rfilePath);
                    }
                    catch (IOException var15) {
                        System.err.println(var15);
                    }
                    break;

            case (4):
                end = 0;
                break;

                default:
                    System.out.print("Error: You entered a non-existent action number!!!");
                    break;
            }
            System.out.println("------------------------------------------------------------");
        }
    }
}
