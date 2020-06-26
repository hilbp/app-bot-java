package com.hilbp;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
 
@RunWith(SpringRunner.class)
public class ScreenShotUtilTest {
	
    @Test
    public void test() {
    	for(int i = 0; i < 10; i++) {
    		long start = System.currentTimeMillis();
    		this.sreenshot1(i);
    		System.out.println(System.currentTimeMillis() - start);
    	}
    }
    
   
    public void sreenshot1(int i) { 
    	String path = "E:/screenshot" + i + ".png";
    	String cmd = "adb exec-out screencap -p > " + path;
    	String [] command = {"cmd" , "/C" , cmd};
    	
    	try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public void sreenshot2(int i) { 
    	
    	String path = "E:/screenshot" + i + ".png";
    	String cmd = "adb exec-out screencap -p > " + path;
    	String [] command = {"cmd" , "/C" , cmd};
    	
    	ProcessBuilder pb = new ProcessBuilder(command);
    	try {
			Process p = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void sreenshot3(int i) { 
    	String path = "E:/screenshot" + i + ".png";
    	String cmd = "adb shell screencap -p /data/local/tmp/screen.png";
    	String cmd1 = "adb pull /data/local/tmp/screen.png " + path;
    	try {
			Runtime.getRuntime().exec(cmd);
			Runtime.getRuntime().exec(cmd1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}