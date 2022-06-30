import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: Sihang Xie
 * @Description: try-with-resource语句的测试
 * @Date: 2022/6/25 19:18
 * @Version: 0.0.1
 * @Modified By:
 */
public class IOTest {
    @Test
    public void testIO() {
        //1.创建File类的对象，指明读入和写出到的文件
        File srcFile = new File("hello.txt");
        File destFile = new File("hello1.txt");

        try (
                //2.创建输入流和输出流的对象
                FileReader fr = new FileReader(srcFile);
                FileWriter fw = new FileWriter(destFile)
        ) {
            //3.数据的读入和写出操作
            char[] cbuf = new char[(int) srcFile.length()];
            int len;//记录每次读入到cbuf数组中的字符的个数
            while ((len = fr.read(cbuf)) != -1) {
                //每次写出len个字符
                fw.write(cbuf, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
