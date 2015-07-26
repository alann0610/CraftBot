package testing;
import java.util.*;
import javax.microedition.lcdui.Graphics;
import lejos.nxt.*;
public class TextUI {

   public static void main(String[] args) throws InterruptedException {
      Graphics g = new Graphics();
      g.drawLine(0, 9, 99, 9);
      LCD.drawString("Text UI", 5, 0);
      LCD.drawString(">START", 0, 2);
      LCD.drawString(" EXIT", 0, 3);
      int selection = 1;
      Thread.sleep(500);
      while(!Button.ENTER.isPressed()) {
         if(Button.RIGHT.isPressed()) {
            if(selection == 1) {
               selection = 2;
               LCD.clear();
               g.drawLine(0, 9, 99, 9);
               LCD.drawString("Text UI", 5, 0);
               LCD.drawString(" START", 0, 2);
               LCD.drawString(">EXIT", 0, 3);
            }
            else {
               selection = 1;
               LCD.clear();
               g.drawLine(0, 9, 99, 9);
               LCD.drawString("Text UI", 5, 0);
               LCD.drawString(">START", 0, 2);
               LCD.drawString(" EXIT", 0, 3);
            }
         }
         if(Button.LEFT.isPressed()) {
            if(selection == 1) {
               selection = 2;
               LCD.clear();
               g.drawLine(0, 9, 99, 9);
               LCD.drawString("Text UI", 5, 0);
               LCD.drawString(" START", 0, 2);
               LCD.drawString(">EXIT", 0, 3);
            }
            else {
               selection = 1;
               LCD.clear();
               g.drawLine(0, 9, 99, 9);
               LCD.drawString("Text UI", 5, 0);
               LCD.drawString(">START", 0, 2);
               LCD.drawString(" EXIT", 0, 3);
            }
         }
         Thread.sleep(300);
      }
      if(selection == 2) {
         System.exit(0);
      }
      else {
      LCD.clear();
      g.drawLine(0, 9, 99, 9);
      LCD.drawString("Enter Text", 3, 0);
      String[] arr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
      String[] out = new String[15];
      int mlength = 15;
      int outTrack = 0;
      int arrTrack = 0;
      g.drawLine(45, 32, 55, 32);
      LCD.drawString("^", 8, 4);
      int pos1 = 1;
      int pos2 = 2;
      int pos3 = 25;
      int pos4 = 24;
      LCD.drawString(arr[pos4], 2, 3);
      LCD.drawString(arr[pos3], 5, 3);
      LCD.drawString(arr[arrTrack], 8, 3);
      LCD.drawString(arr[pos1], 11, 3);
      LCD.drawString(arr[pos2], 14, 3);
      LCD.drawString(mlength + " char. left", 0, 7);
      Thread.sleep(300);
      String ret = "";
      while(!Button.ESCAPE.isPressed()) {
         if(Button.RIGHT.isPressed()) {
            ret = "";
            arrTrack++;
            if(arrTrack > 25)
               arrTrack = 0;
            pos1 = arrTrack + 1;
            if(pos1 > 25)
               pos1 = 0;
            pos2 = arrTrack + 2;
            if(pos2 > 25 && arrTrack == 24)
               pos2 = 0;
            if(pos2 > 25 && arrTrack == 25)
               pos2 = 1;
            pos3 = arrTrack - 1;
            if(pos3 < 0)
               pos3 = 25;
            pos4 = arrTrack - 2;
            if(pos4 < 0 && arrTrack == 1)
               pos4 = 25;
            if(pos4 < 0 && arrTrack == 0)
               pos4 = 24;
            LCD.clear();
            LCD.drawString("Enter Text", 3, 0);
            g.drawLine(0, 9, 99, 9);
            g.drawLine(45, 32, 55, 32);
            LCD.drawString("^", 8, 4);
            LCD.drawString(arr[pos4], 2, 3);
            LCD.drawString(arr[pos3], 5, 3);
            LCD.drawString(arr[arrTrack], 8, 3);
            LCD.drawString(arr[pos1], 11, 3);
            LCD.drawString(arr[pos2], 14, 3);
            LCD.drawString(mlength + " char. left", 0, 7);
            for(int i = 0; i <= out.length-1; i++) {
               if(out[i] == null)
                  break;
               else
               ret += out[i];
            }
            LCD.drawString(ret, 0, 5);
         }
         if(Button.LEFT.isPressed()) {
            ret = "";
            arrTrack--;
            if(arrTrack < 0)
               arrTrack = 25;
            pos1 = arrTrack + 1;
            if(pos1 > 25)
               pos1 = 0;
            pos2 = arrTrack + 2;
            if(pos2 > 25 && arrTrack == 24)
               pos2 = 0;
            if(pos2 > 25 && arrTrack == 25)
               pos2 = 1;
            pos3 = arrTrack - 1;
            if(pos3 < 0)
               pos3 = 25;
            pos4 = arrTrack - 2;
            if(pos4 < 0 && arrTrack == 1)
               pos4 = 25;
            if(pos4 < 0 && arrTrack == 0)
               pos4 = 24;
            LCD.clear();
            LCD.drawString("Enter Text", 3, 0);
            g.drawLine(0, 9, 99, 9);
            g.drawLine(45, 32, 55, 32);
            LCD.drawString("^", 8, 4);
            LCD.drawString(arr[pos4], 2, 3);
            LCD.drawString(arr[pos3], 5, 3);
            LCD.drawString(arr[arrTrack], 8, 3);
            LCD.drawString(arr[pos1], 11, 3);
            LCD.drawString(arr[pos2], 14, 3);
            LCD.drawString(mlength + " char. left", 0, 7);
            for(int i = 0; i <= out.length-1; i++) {
               if(out[i] == null)
                  break;
               else
               ret += out[i];
            }
            LCD.drawString(ret, 0, 5);
         }
         if(Button.ENTER.isPressed()) {
            ret = "";
            if(mlength == 0)
               break;
            out[outTrack] = arr[arrTrack];
            mlength--;
            outTrack++;
            LCD.clear();
            LCD.drawString("Enter Text", 3, 0);
            g.drawLine(0, 9, 99, 9);
            g.drawLine(45, 32, 55, 32);
            LCD.drawString("^", 8, 4);
            LCD.drawString(arr[pos4], 2, 3);
            LCD.drawString(arr[pos3], 5, 3);
            LCD.drawString(arr[arrTrack], 8, 3);
            LCD.drawString(arr[pos1], 11, 3);
            LCD.drawString(arr[pos2], 14, 3);
            LCD.drawString(mlength + " char. left", 0, 7);
            for(int i = 0; i <= out.length-1; i++) {
               if(out[i] == null)
                  break;
               else
               ret += out[i];
            }
            LCD.drawString(ret, 0, 5);
         }
         Thread.sleep(300);
      }
      Thread.sleep(300);
      LCD.clear();
      LCD.drawString("Input", 6, 0);
      g.drawLine(0, 9, 99, 9);
      ret = "";
      for(int i = 0; i <= out.length-1; i++) {
         if(out[i] == null)
            break;
         else
         ret += out[i];
      }
      LCD.drawString(ret, 0, 3);
   Thread.sleep(5000);
      }
   }

}