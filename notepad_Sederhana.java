import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class notepad_Sederhana extends JFrame
{
    private JPopupMenu popup = new JPopupMenu();
    private JMenuItem menuCopy = new JMenuItem("Copy");
    private JMenuItem menuCut = new JMenuItem("Cut");
    private JMenuItem menuPaste = new JMenuItem("Paste");
    private JMenuItem menuSelectAll = new JMenuItem("Select All");
    private JMenuBar menu_Baris = new JMenuBar();
    private JMenu menu_File = new JMenu("File");
    private JMenu menu_About = new JMenu("About");
    private JMenuItem mi_About = new JMenuItem("About");
    private JMenuItem mi_Save = new JMenuItem("Save");
    private JMenuItem mi_Open = new JMenuItem("Open");
    private JMenuItem mi_Exit = new JMenuItem("Exit");
    private JTextArea text_Area = new JTextArea("",0,0);
    private JScrollPane js = new JScrollPane(text_Area);
    private JFileChooser fc = new JFileChooser();
     //Konstuktor
public notepad_Sederhana()
{      
    Container konten = getContentPane();
    setTitle("Notepad");
    setSize(700,600);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); 
    konten.add(js);
    setJMenuBar(menu_Baris);
    menu_Baris.add(menu_File);
    menu_File.add(mi_Open);
    menu_File.add(mi_Save);
    menu_Baris.add(menu_About);
    menu_About.add(mi_About);
    popup.add(menuCut);
    popup.add(menuCopy);
    popup.add(menuPaste);
    popup.add(menuSelectAll);
    text_Area.add(popup);
    text_Area.addMouseListener(new MousePopupListener());
    text_Area.requestFocus();
    mi_Save.setMnemonic(KeyEvent.VK_S); //Membuat nama shortcut
    mi_Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK)); //Membuat shorcut Keyboard 

    menu_File.addSeparator(); //Menambahkan garis separator
    mi_Open.setMnemonic(KeyEvent.VK_O);
    mi_Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
    menu_File.add(mi_Exit);
       
    mi_About.setMnemonic(KeyEvent.VK_A);
    mi_About.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        
    text_Area.setFont(new Font("Arial",Font.PLAIN,14)); //Mengatur Fonts
    text_Area.setLineWrap(true);
    text_Area.setWrapStyleWord(true);
      
mi_Open.addActionListener(new ActionListener()
{
    public void actionPerformed(ActionEvent act)
    {
        int buka = fc.showOpenDialog(text_Area);
        if(buka == JFileChooser.APPROVE_OPTION)
        {
            text_Area.setText("");
            try
            {
                Scanner scan = new Scanner(new FileReader(fc.getSelectedFile().getPath())); //Membuat scanner membaca file yang dibuka
                while(scan.hasNext())
                {
                    text_Area.append(scan.nextLine());
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
    }
});
      
mi_Save.addActionListener(new ActionListener()
{
    public void actionPerformed(ActionEvent act)
    {
        int simpan = fc.showOpenDialog(text_Area);
        if(simpan == JFileChooser.APPROVE_OPTION)
        {
            try
            {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fc.getSelectedFile().getPath())); //Menulis File yang kita buka di TextArea
            bw.write(text_Area.getText());
            bw.close();
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
    }
});
      
mi_Exit.addActionListener(new ActionListener()
{
    public void actionPerformed(ActionEvent act)
    {
       dispose(); //Keluar dari aplikasi
    }
});
      
mi_About.addActionListener(new ActionListener()
{
    public void actionPerformed(ActionEvent act)
    {
        JOptionPane.showMessageDialog(null,"ngejava.com","Pesan",JOptionPane.INFORMATION_MESSAGE);
    }
}); 

menuCopy.addActionListener(new ActionListener()
{
    public void actionPerformed(ActionEvent act)
    {
        text_Area.copy();
    }
}); 

menuPaste.addActionListener(new ActionListener()
{
    public void actionPerformed(ActionEvent act)
    {
        text_Area.paste();
    }
}); 

menuCut.addActionListener(new ActionListener()
{
    public void actionPerformed(ActionEvent act)
    {
        text_Area.cut();
    }
}); 

menuSelectAll.addActionListener(new ActionListener()
{
    public void actionPerformed(ActionEvent act)
    {
        text_Area.selectAll();
    }
}); 



}
class MousePopupListener extends MouseAdapter
{
    public void mousePressed(MouseEvent e)
    {
        checkPopup(e);
    }
    public void mouseClicked(MouseEvent e)
    {
        checkPopup(e);
    }
    public void mouseReleased(MouseEvent e)
    {
        checkPopup(e);
    }
    private void checkPopup(MouseEvent e)
    {
        if (e.isPopupTrigger())
    {
        popup.show(notepad_Sederhana.this, e.getX(), e.getY());
    }
    }
}
   
    public static void main(String[] ar)
    {
        new notepad_Sederhana();
    }
}