using System.Xml;

namespace Attributes
{
  class Program
  {
    static void Main(string[] args)
    {
      var xdoc = new XmlDocument();
      xdoc.Load(args[0]);
      foreach (XmlAttribute atr in xdoc.DocumentElement.Attributes)
      {
        // enumerate attributes
      }
      var atr_zero = xdoc.DocumentElement.Attributes[0];
    }
  }
}
