using System;
using System.Xml;
namespace WhiteSpaceDoc
{
  class Program
  {
    static void Main(string[] args)
    {
      var xdoc = new XmlDocument();
      xdoc.Load(args[0]);
      var nodes = xdoc.SelectNodes("//node()");
      foreach (XmlNode node in nodes)
      {
        Console.WriteLine("{0} '{1}'", node.NodeType, node.Value);
      }
    }
  }
}
