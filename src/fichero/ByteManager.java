package fichero;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

public final class ByteManager {

  public static byte[] convertToBytes(Object object) throws IOException{ 
      ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
      ObjectOutputStream oos = new ObjectOutputStream(baos); 
      oos.writeObject(object); 
      return baos.toByteArray(); 
  } 
  
  private ByteManager(){}
   
  public static Object convertToObject(byte[] bytes) throws IOException,  ClassNotFoundException
  { 
    Object object = null; 
    object = new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject(); 
    return object; 
  }
  
  public static void writeObject(RandomAccessFile file, Object object) throws IOException,  ClassNotFoundException
  {
    byte[] bytes;
    int size;
    bytes = ByteManager.convertToBytes(object);
    size = bytes.length;
    file.writeInt(size);
    file.write(bytes);
  }

  public static Object readObject(RandomAccessFile file) throws ClassNotFoundException, IOException
  {
    int size = file.readInt();
    byte[] bytes = new byte[size];
    file.read(bytes);
    return ByteManager.convertToObject(bytes);
  }

}