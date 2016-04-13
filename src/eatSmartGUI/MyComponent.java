package eatSmartGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;


class Slice {
   double value;
   Color color;
   String name;
   public Slice(double value, Color color, String name) {  
      this.value = value;
      this.color = color;
      this.name = name;
   }
}
class MyComponent extends JComponent {
	
   VMItemDaoImpl vmItemDao = new VMItemDaoImpl(); 
   HashMap<String, Integer> vmSaleQtyMap = vmItemDao.getAllVMSaleQuantity();
   ArrayList<Integer> vmSaleQtyList = new ArrayList<Integer>(vmSaleQtyMap.values());
   public ArrayList<String> vmNameList = new ArrayList<String>(vmSaleQtyMap.keySet());
   Slice[] slices = { new Slice(vmSaleQtyList.get(0), Color.BLACK, vmNameList.get(0)), 
   new Slice(vmSaleQtyList.get(1), Color.green, vmNameList.get(1)),
   new Slice(vmSaleQtyList.get(2), Color.yellow, vmNameList.get(2)), 
   new Slice(vmSaleQtyList.get(3), Color.red, vmNameList.get(3)) };
   
   MyComponent() {}
   
   public void paint(Graphics g) {
      drawPie((Graphics2D) g, getBounds(), slices);
   }
   void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
      double total = 0.0D;
    
      System.out.println(vmSaleQtyList);
      System.out.println(vmNameList);
      for (int i = 0; i < slices.length; i++) {
         total += slices[i].value;
      }
      double curValue = 0.0D;
      int startAngle = 0;
      for (int i = 0; i < slices.length; i++) {
         startAngle = (int) (curValue * 360 / total);
         int arcAngle = (int) (slices[i].value * 360 / total);
         g.setColor(slices[i].color);
         g.fillArc(area.x, area.y, area.width, area.height, 
         startAngle, arcAngle);
         curValue += slices[i].value;
      }
   }
}
