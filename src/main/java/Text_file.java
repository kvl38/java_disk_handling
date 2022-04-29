
import java.io.*;
import java.util.Scanner;


public class Text_file {
    public static void Work_with_text()
    {
        int end = 1000;
        while (end != 0)
        {
            System.out.println("Select the action number to work with the file!");
            System.out.println("1. Create new file\n" +
                    "2. Write text to file\n" +
                    "3. Read file in console\n" +
                    "4. Delete a file\n" +
                    "5. Finish the file");

            Scanner in = new Scanner(System.in);
            System.out.print("Enter action number: ");
            int choice = in.nextInt();


            switch (choice)
            {
                case  (1):
                    Scanner in_file_name = new Scanner(System.in);
                    System.out.print("Enter a name for the new text file, without type: ");
                    String file_name = in_file_name.nextLine() + ".txt";
                    try
                    {
                        FileWriter writer = new FileWriter(file_name);
                        writer.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("File created successfully");
                    break;


                case (2):
                    file_name = File_name();

                    Scanner text_to_file = new Scanner(System.in);
                    System.out.print("Enter text to write to file: ");
                    String text = text_to_file.nextLine();
                    try
                    {
                        FileWriter writer = new FileWriter(file_name, true);
                        writer.write(text);
                        writer.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;


                case (3):
                    file_name = File_name();

                    try {
                        FileReader reader = new FileReader(file_name);
                        BufferedReader bufferedReader = new BufferedReader(reader);

                        String line;
                        System.out.print("Text from file: ");
                        while ((line = bufferedReader.readLine()) != null) {
                            System.out.println(line);
                        }
                        reader.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;


                case (4):
                    file_name = File_name();

                    File file = new File(file_name);
                    if(file.delete())
                    {
                        System.out.println(file_name + " has been deleted");
                    }
                    else System.out.println("Error: File "+ file_name + " not found!!!");
                    break;

                case (5):
                    end = 0;
                    break;

                default:
                    System.out.print("Error: You entered a non-existent action number!!!");
                    break;
            }

            System.out.println("------------------------------------------------------------");
        }

    }
    public static String File_name()
    {
        Scanner name = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String file_name = name.nextLine() + ".txt";
        return file_name;
    }
}