import javax.xml.stream.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;



public class Xml_file {
    public static void Work_with_xml() {
        int end = 1000;
        while (end != 0)
        {
            System.out.println("Select the action number to work with the file!");
            System.out.println("1. Create and write a new file\n" +
                    "2. Read file in console\n" +
                    "3. Delete a file\n" +
                    "4. Finish the file");

            Scanner in = new Scanner(System.in);
            System.out.print("Enter action number: ");
            int choice = in.nextInt();


            switch (choice) {
                case (1):
                    System.out.print("Input file name, without type:");
                    Scanner fileName = new Scanner(System.in);
                    String fileNam = fileName.nextLine() + ".xml";

                    try {
                        XMLOutputFactory output = XMLOutputFactory.newInstance();
                        XMLStreamWriter writer = output.createXMLStreamWriter(new FileWriter(fileNam));

                        writer.writeStartDocument("1.0");
                        writer.writeStartElement("Students");

                        System.out.print("Enter the number of students:");
                        Scanner count = new Scanner(System.in);
                        int length = count.nextInt();


                        for (int i = 0; i < length; i++) {
                            writer.writeStartElement("Student");

                            System.out.print("Input name:");
                            Scanner name = new Scanner(System.in);
                            String s_name = name.nextLine();
                            writer.writeStartElement("name");
                            writer.writeCharacters(s_name);
                            writer.writeEndElement();

                            System.out.print("Input surname:");
                            Scanner second = new Scanner(System.in);
                            String surname = second.nextLine();
                            writer.writeStartElement("surname");
                            writer.writeCharacters(surname);
                            writer.writeEndElement();

                            System.out.print("Input study group:");
                            Scanner group = new Scanner(System.in);
                            String study_group = group.nextLine();
                            writer.writeStartElement("study_group");
                            writer.writeCharacters(study_group);
                            writer.writeEndElement();

                            writer.writeEndElement();
                        }

                        writer.writeEndElement();
                        writer.writeEndDocument();
                        writer.flush();

                    } catch (IOException | XMLStreamException var13) {
                        var13.printStackTrace();
                    }
                    break;
                case (2):
                    FileDialog readDialog = new FileDialog((Frame) null, "Выбрать файл");
                    readDialog.setMode(0);
                    readDialog.setVisible(true);
                    String var10000 = readDialog.getDirectory();
                    String filePath = var10000 + readDialog.getFile();

                    try {
                        XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(filePath, new FileInputStream(filePath));

                        while (xmlr.hasNext()) {
                            xmlr.next();
                            if (xmlr.isStartElement()) {
                                System.out.println("<" + xmlr.getLocalName() + ">");
                            } else if (xmlr.isEndElement()) {
                                System.out.println("</" + xmlr.getLocalName() + ">");
//                            } else if (xmlr.hasText() && xmlr.getText().trim().length() > 0) {
//                                System.out.println("   " + xmlr.getText());
                            }
                        }

                        break;
                    } catch (XMLStreamException | FileNotFoundException var12) {
                        var12.printStackTrace();
                        break;
                    }


                case (3):
                    FileDialog removeDialog = new FileDialog((Frame) null, "Выбрать файл");
                    removeDialog.setMode(0);
                    removeDialog.setVisible(true);
                    Path rfilePath = Path.of(removeDialog.getDirectory() + removeDialog.getFile());

                    try {
                        Files.delete(rfilePath);
                    } catch (NoSuchFileException var9) {
                        System.err.format("%s: Нет такого файла или директория%n", rfilePath);
                    } catch (DirectoryNotEmptyException var10) {
                        System.err.format("%s не пустой%n", rfilePath);
                    } catch (IOException var11) {
                        System.err.println(var11);
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
