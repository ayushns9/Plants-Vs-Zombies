package sample;
import java.io.*;
public class save {
    static void saved(data d,String name) throws IOException {
        ObjectOutputStream output = null;
        try {

            output=new ObjectOutputStream(new FileOutputStream(name+".txt"));
            System.out.println("f");
            output.writeObject(d);
            System.out.println("dd");

        }
        finally {
            output.close();
        }
    }
    static data unsave(String name) throws IOException,ClassNotFoundException {
        ObjectInputStream input = null;
        data d=null;
        try {
            input = new ObjectInputStream (new FileInputStream(name+".txt"));
            d=(data) input.readObject();
        }
        catch (IOException e){
            System.out.println("Error");
        }
        finally {
            input.close();
            return d;
        }
    }
}

