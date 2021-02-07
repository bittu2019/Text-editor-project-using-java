
package texteditor;


import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static javafx.scene.paint.Color.color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class TextEditor implements ActionListener,WindowListener{
    JMenuItem neww,open,saveas,save,cut,copy,paste,font,font_color,background_color;
    JTextArea textarea;
    JFrame jf,font_frame;
    File file;
    JComboBox font_family,font_size,font_style;
    JButton ok;

    TextEditor()
    {
        //hiding scrollbar(do R and D for any prolem ex: google search-jframe look
        //Creating frame
        try
        {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//When exception occur use try catch
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        jf=new JFrame("Untitled-Notepad");
        jf.setSize(700, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        //creating menubar
        JMenuBar jmenubar = new JMenuBar();
        
        //creating menu
        JMenu file = new JMenu("File");
        //creating MenuItem
        neww = new JMenuItem("New");
        neww.addActionListener(this);
        file.add(neww);
        jmenubar.add(file);
        
        open= new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);
        
        save=new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);
        
        saveas = new JMenuItem("Save AS");
        saveas.addActionListener(this);
        file.add(saveas);
        
        JMenu edit= new JMenu("Edit");
        jmenubar.add(edit);
        
        cut=new JMenuItem("Cut");
        cut.addActionListener(this);
        edit.add(cut);
        
        copy=new JMenuItem("Copy");
        copy.addActionListener(this);
        edit.add(copy);
        
        paste=new JMenuItem("paste");
        paste.addActionListener(this);
        edit.add(paste);
        
        
        JMenu format = new JMenu("Format");
        jmenubar.add(format);
        
        font = new JMenuItem("Font");
        font.addActionListener(this);
        format.add(font);
        
        font_color = new JMenuItem("Color");
        font_color.addActionListener(this);
        format.add(font_color);
        
        background_color = new JMenuItem("Background");
        background_color.addActionListener(this);
        format.add(background_color);
        
        //Creating Textarea
       textarea = new JTextArea();
       jf.add(textarea);
       jf.addWindowListener(this);
       
       //Creating Scroll bar
       //add textarea scrollpane
       //add scrollpane to jframe
      JScrollPane scrollpane = new JScrollPane(textarea);
      scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      jf.add(scrollpane);
        
        jf.setJMenuBar(jmenubar);
        jf.setVisible(true);
        
    }
    public static void main(String[] args) {
         new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==neww)
        {
            newfile();
        }
         if(e.getSource()==open)
        {
           openfile();
           
        }
         if(e.getSource()==save)
         {
             savefile();
         }
          if(e.getSource()==saveas)
        {
            saveasfile();
        }
          if(e.getSource()==cut)
          {
              textarea.cut();
          }
          if(e.getSource()==copy)
          {
              textarea.copy();
          }
          if(e.getSource()==paste)
          {
              textarea.paste();
              
          }
          if(e.getSource()==font)
          {
              openfontframe();
          }
         if(e.getSource()==ok)
          {
              setfontontextarea();
          }
         if(e.getSource()==font_color)
         {
             Color c=JColorChooser.showDialog(jf, "choose font color", Color.yellow);
             textarea.setForeground(c);//used for text
         }
         if(e.getSource()==background_color)
         {
             Color c=JColorChooser.showDialog(jf, "choose font color", Color.white);
             textarea.setBackground(c);//used for background
         }
         
    }
    void setfontontextarea()
    {
         String fontfamily=(String)font_family.getSelectedItem();
              String fontsize=(String)font_size.getSelectedItem();
              String fontstyle=(String)font_style.getSelectedItem();
              
              int style=0;
              if(fontstyle.equals("plain"))
              {
                  style=0;
              }
              else if(fontstyle.equals("bold"))
              {
                  style=1;
              }
              else if(fontstyle.equals("italic"))
              {
                  style=2;
              }
              Font fontt= new Font(fontfamily,style,Integer.parseInt(fontsize));
              textarea.setFont(fontt);
              font_frame.setVisible(false);
    }
        
    
    void openfontframe()
    {
        //creating frame for format
        
        font_frame= new JFrame("Select_font");
        font_frame.setSize(500, 500);
        font_frame.setVisible(true);
        font_frame.setLocationRelativeTo(jf);//open in center
        font_frame.setLayout(null);//to use stebound method
        
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();//used as array;
        font_family= new JComboBox(fonts);
        font_family.setBounds(50, 100, 100, 30);
        font_frame.add(font_family);
        
        String[] sizes={"12","14","16","18","20","22","24","26","30","72"};
        font_size= new JComboBox(sizes);
        font_size.setBounds(160, 100, 100, 30);
        font_frame.add(font_size);
        
        String[] styles={"plain","bold","italic"};
        font_style= new JComboBox(styles);
        font_style.setBounds(270, 100, 100, 30);
        font_frame.add(font_style);
        
        ok=new JButton("Ok");
        ok.setBounds(180, 200, 100, 50);
        ok.addActionListener(this);
        font_frame.add(ok);
    }
    void openfile()
    {
        JFileChooser fileChooser = new JFileChooser();
           int result = fileChooser.showOpenDialog(jf);
           file=fileChooser.getSelectedFile();
           
           if(result==0)
           {
               textarea.setText("");
                try
                {
                         FileInputStream fis = new FileInputStream(file);
                         jf.setTitle(file.getName());
                         int i;
                         while((i=fis.read())  !=-1)//-1 is denote end of file
                         {
                             textarea.append(String.valueOf((char)i));//converting integer to string
                         }
            
                 }
             catch(IOException ee)
                 {
                     System.out.println(ee);
                 }
           }
    }
    void savefile()
    {
        String title=jf.getTitle();
        if(title.equals("Untitled-Notepad"))
        {
            saveasfile();
        }
        else
        {
                String text=textarea.getText();
                try(FileOutputStream fos = new FileOutputStream(file))
                    {  
                       byte[] b=text.getBytes();
                       fos.write(b);
                    }
                catch(IOException ee)
                    {
                       ee.printStackTrace();
                    }
        }
    }
    void saveasfile()
    {
        JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(jf);
            file=fileChooser.getSelectedFile();
            jf.setTitle(file.getName());
            if(result==0)
            {
                String text=textarea.getText();
                try(FileOutputStream fos = new FileOutputStream(file))
                    {  
                       byte[] b=text.getBytes();
                       fos.write(b);
                    }
                catch(IOException ee)
                    {
                       ee.printStackTrace();
                    }
            }
    }
    void newfile()
    {
         String text=textarea.getText();
            if(!text.equals(""))
            {
                int i=JOptionPane.showConfirmDialog(jf, "Do you want to save file");
                if(i==0)
                {
                    saveasfile();
                    textarea.setText("");
                    jf.setTitle("Untitled-Notepad");
                }
                else if(i==1)
                {
                    textarea.setText("");
                   
                }
                else
                {
                    
                }
            }
    }

    @Override
    public void windowOpened(WindowEvent e) {
       
    }

    @Override
    public void windowClosing(WindowEvent e) {
        String text=textarea.getText();
        if(!text.equals(""))
        {
         int i=JOptionPane.showConfirmDialog(jf, "Do you want to save file");
            
                if(i==0)
                   {
                    saveasfile();
                    textarea.setText("");
                    jf.setTitle("Untitled-Notepad");
                   }
                else if(i==1)
                    {
                    textarea.setText("");
                   
                    }
                else
                {
                    
                }
        }
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
      
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
    
    
}
