using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;

namespace Preserve
{
  class Program
  {
    static void Main(string[] args)
    {
      var settings = new XmlReaderSettings();
      settings.IgnoreWhitespace = false;  
      var xreader = XmlReader.Create(args[0], settings);
      var xdoc = new XmlDocument();
      xdoc.Load(xreader);
      Console.WriteLine("'{0}'", xdoc.InnerText);
    }
  }
}
