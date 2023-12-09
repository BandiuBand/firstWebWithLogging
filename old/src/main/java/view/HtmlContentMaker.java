package view;

import jdk.jfr.StackTrace;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.Objects;

public class HtmlContentMaker {
    private static final String parentDirectory = "e:/tomkaWeb/Dir/";
    private HtmlContentMaker()
    {}
    public static String getContent(String path){
        System.out.println("get content from - "+ path);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contentBeforeTable());
        stringBuilder.append(getTable(path));
        stringBuilder.append(contentAfterTable(path));
        return stringBuilder.toString();
    }

    private static String getTable(String path){
        StringBuilder stringBuilder = new StringBuilder();
        File[] listOfFiles = getFileList(path);
        stringBuilder.append("<table>").append("\n");

        stringBuilder.append(getRowOfBack(path));
        for (File file:listOfFiles) {
            if(file==null)
                System.out.println("file is null");
            else {
                System.out.print("file inside directory:"+path+"  name - ");
                System.out.println(file.getName());
                stringBuilder.append(makeRow(file)).append("\n");
            }
        }
        stringBuilder.append("<table/>").append("\n");
        return stringBuilder.toString();
    }

    private static String getRowOfBack(String path){

        if (path.length()<=1)
            return "";

        File dir = new File(parentDirectory+getPreviousDirectoryPath(path));

        String encodedFilePath = URLEncoder.encode(getPathOfFile(dir)+"/");
        String href = "<a href=\"dir?path=" + encodedFilePath + "\">"+" ... "+"</a>";

        return String.format("<tr><td>%s<td/><td>%s<td/><tr/>",href,"");
    }

    private static String getPreviousDirectoryPath(String pathIn){
        String path = pathIn.replaceAll("\\\\","/");
        String dirPath = null;
        if (path.endsWith("/"))
            dirPath = path.substring(0,path.length()-1);
        else dirPath = path;
        System.out.print("make row of back from directory:\"/"+ dirPath+"\"");
        String previousDirPath = null;
        if (dirPath.indexOf("/")<0)
            previousDirPath = "/";
        else
            previousDirPath= dirPath.substring(0,dirPath.lastIndexOf("/"));
        System.out.println(" to directory:\"" + previousDirPath);
        return previousDirPath;
    }



    private static String contentBeforeTable() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">" +
                "    <title>"+"Файловий Менеджер"+"</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <a href=\"/firstWeb/index\">Додому</a>\n";
    }
    private static String contentAfterTable(String path) {
        return  getFormForCreateTxt(path)+
                "</body>\n" +
                "</html>";
    }

    private static String getFormForCreateTxt(String path) {
        String nameOfFile = "fileName";
        String contentTxt = "contentTxt";
        return "<form action=\"save\" method=\"post\"accept-charset=\"UTF-8\">\n" +
                        "    <p>Им'я файлу</p>\n" +
                        "    <input type=\"text\" name=\"fileName\" size=\"50\">\n" +
                        "    <p>Введіть текст</p>\n" +
                        "    <textarea name=\"contentTxt\" rows=\"30\" cols=\"50\"></textarea>\n" +
                        "    <input type=\"hidden\" name=\"path\" value=\""+path+"\">\n" +
                        "    <br><input type=\"submit\" value=\"Зберегти в цю папку\">\n" +
                        "</form>";
    }

    private static File[] getFileList(String path){
        File[] filesList = new File[1];
        File directory = new File(parentDirectory+path);
        if (directory.exists()&&directory.isDirectory()) {
            filesList= (Objects.requireNonNull(directory.listFiles()));
        }
        else if(!directory.exists()) {
            System.out.println("isn't exist");
        }
        return filesList;
    }
    private static String makeRow(File file) {



        String name = file.getName();
        String pathOfFile = getPathOfFile(file);
        String href = getHrefToFile(file);
        String hrefOfDelete = getHrefDelete(file);//toDo
        return String.format("<tr><td>%s<td/><td>%s<td/><tr/>",href,hrefOfDelete);
    }
    private static String getPathOfFile(File file){
        File baseDir = new File(parentDirectory);
        Path base = baseDir.toPath();
        Path filePath = file.toPath();

        Path result = base.relativize(filePath);

        return result.toString();
    }
    private static String getHrefToFile(File file){

        if(file.isDirectory())
            return getHrefToDirectory(file);
        else if (isTxt(file))
            return getHrefToOpenTxt(file);
        else
            return getHrefToDownload(file);
    }

    private static String getHrefToDirectory(File file){
        String encodedFilePath = URLEncoder.encode(getPathOfFile(file)+"/");
        String result = "<a href=\"dir?path=" + encodedFilePath + "\">"+file.getName()+"</a>";
        return result;
    }

    private static String getHrefToOpenTxt(File file){
        String encodedFilePath = URLEncoder.encode(getPathOfFile(file)+"/");

        return "<a href=\"openTxt?path=" + encodedFilePath + "\">"+file.getName()+"</a>";
    }

    private static String getHrefToDownload(File file){
        String encodedFilePath = URLEncoder.encode(getPathOfFile(file));
        return "<a href=\"downloadFile?path=" + encodedFilePath + "\">"+file.getName()+"</a>";
    }

    private static String getHrefDelete(File file){
        String encodedFilePath = URLEncoder.encode(getPathOfFile(file)+"/");
        if(file.isDirectory())
            return "";
        return "<a href=\"delete?path=" + encodedFilePath + "\">"+"DELETE"+"</a>";
    }

    private static boolean isTxt(File file){

        return file.getName().endsWith(".txt");

    }
    public static String getRoot(){
        return parentDirectory;
    }
}
