package gui;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import javax.swing.filechooser.FileFilter;

public class ExtensionFileFilter extends FileFilter
  implements FilenameFilter, Serializable
{
  protected String m_Description;
  protected String[] m_Extension;

  public ExtensionFileFilter(String extension, String description)
  {
    this.m_Extension = new String[1];
    this.m_Extension[0] = extension;
    this.m_Description = description;
  }

  public ExtensionFileFilter(String[] extensions, String description)
  {
    this.m_Extension = extensions;
    this.m_Description = description;
  }

  public String getDescription()
  {
    return this.m_Description;
  }

  public String[] getExtensions()
  {
    return (String[])(String[])this.m_Extension.clone();
  }

  public boolean accept(File file)
  {
    String name = file.getName().toLowerCase();
    if (file.isDirectory()) {
      return true;
    }
    for (int i = 0; i < this.m_Extension.length; i++) {
      if (name.endsWith(this.m_Extension[i])) {
        return true;
      }
    }
    return false;
  }

  public boolean accept(File dir, String name)
  {
    return accept(new File(dir, name));
  }
}