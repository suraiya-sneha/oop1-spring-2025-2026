package fileio;

import entity.Member;
import java.io.*;

public class MemberFileIO {

    private static final String FILE_NAME = "fileio/database.txt";
    private static final String TEMP_FILE = "fileio/temp.txt";

    public static void createFileIfNotExists() throws IOException {
        File file = new File(FILE_NAME);
        if(!file.exists()) file.createNewFile();
    }

    public static boolean idExists(String id){
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line = br.readLine()) != null){
                Member m = Member.fromLine(line);
                if(m != null && m.getId().equals(id)) return true;
            }
        }catch(IOException ignored){}
        return false;
    }

    public static void addMember(Member m) throws IOException {
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(
                new FileWriter(FILE_NAME, true)))){
            pw.println(m.toLine());
        }
    }

    public static boolean updateMember(Member updated) throws IOException {
        File input = new File(FILE_NAME);
        File temp = new File(TEMP_FILE);

        boolean found = false;

        try(BufferedReader br = new BufferedReader(new FileReader(input));
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {

            String line;
            while((line = br.readLine()) != null){
                Member m = Member.fromLine(line);

                if(m != null && m.getId().equals(updated.getId())){
                    bw.write(updated.toLine());
                    found = true;
                } else {
                    bw.write(line);
                }
                bw.newLine();
            }
        }

        if(found){
            input.delete();
            temp.renameTo(input);
        } else {
            temp.delete();
        }

        return found;
    }

    public static boolean deleteMember(String id) throws IOException {
        File input = new File(FILE_NAME);
        File temp = new File(TEMP_FILE);

        boolean found = false;

        try(BufferedReader br = new BufferedReader(new FileReader(input));
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp))) {

            String line;
            while((line = br.readLine()) != null){
                Member m = Member.fromLine(line);

                if(m != null && m.getId().equals(id)){
                    found = true;
                    continue;
                }

                bw.write(line);
                bw.newLine();
            }
        }

        if(found){
            input.delete();
            temp.renameTo(input);
        } else {
            temp.delete();
        }

        return found;
    }

    public static Object[][] getAllMembers(){
        int count = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            while(br.readLine() != null) count++;
        } catch(IOException ignored){}

        Object[][] data = new Object[count][4];
        int i = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line = br.readLine()) != null){
                Member m = Member.fromLine(line);
                if(m != null){
                    data[i++] = m.toRow();
                }
            }
        } catch(IOException ignored){}

        return data;
    }

    public static Object[][] searchMembers(String keyword){
        keyword = keyword.toLowerCase();

        int count = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line = br.readLine()) != null){
                Member m = Member.fromLine(line);
                if(m != null &&
                        (m.getId().contains(keyword) ||
                                m.getName().toLowerCase().contains(keyword))){
                    count++;
                }
            }
        } catch(IOException ignored){}

        Object[][] result = new Object[count][4];
        int i = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while((line = br.readLine()) != null){
                Member m = Member.fromLine(line);
                if(m != null &&
                        (m.getId().contains(keyword) ||
                                m.getName().toLowerCase().contains(keyword))){
                    result[i++] = m.toRow();
                }
            }
        } catch(IOException ignored){}

        return result;
    }
}
