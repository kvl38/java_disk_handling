

public class Main {
    public static void main(String[] args) {
//         Задание №1. Вывести информацию в консоль о логических дисках, именах, метке тома, размере и типе файловой системы.
        Drive_Info.Info();

//         Задание №2. Работа с файлами ( класс File, FileInfo, FileStream и другие)
        Text_file.Work_with_text();

//         Задание №3. Работа с форматом JSON
        Json_file.Work_with_json();

//        Задание №4. Работа с форматом XML
        Xml_file.Work_with_xml();

//        Задание №5. Создание zip архива, добавление туда файла, определение размера архива
        Zip.Work_with_zip();

    }
}