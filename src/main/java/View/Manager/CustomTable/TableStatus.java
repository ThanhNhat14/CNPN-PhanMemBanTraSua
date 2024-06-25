package View.Manager.CustomTable;

//package view.customTable;
//
////import com.mycompany.model.StatusType;
//import java.awt.Color;
//import java.awt.GradientPaint;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import javax.swing.JLabel;
//
//public class TableStatus extends JLabel{
//    
//    private StatusType type;
//    
//    public StatusType getType(){
//        return type;
//    }
//    
//    public TableStatus(){
//        setForeground(Color.WHITE);
//    }
//   
//    public void setType(StatusType type){
//        this.type = type;
//        setText(type.toString());
//        repaint();
//    }
//
//    @Override
//    protected void paintComponent(Graphics grphcs) {
//        if(type !=null){
//            Graphics2D g2 = (Graphics2D) grphcs;
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            GradientPaint g;
//            if (type == StatusType.Đã_cho_thuê){
//                g = new GradientPaint(0, 0, new Color(51, 255, 168), 0, getHeight(), new Color(167, 94, 236));
//            }else if(type == StatusType.Còn_trống){
//                g = new GradientPaint(0, 0, new Color(233, 255, 51), 0, getHeight(), new Color(123, 123, 245));
//            }else{
//                g = new GradientPaint(0, 0, new Color(241, 208, 62), 0, getHeight(), new Color(211, 184, 61));
//            }
//            g2.setPaint(g);
//            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
//            //g2.fillRect(getWidth() -20,0, getWidth(), getHeight());
//        }
//        super.paintComponent(grphcs);
//    }
//    
//    
//}
